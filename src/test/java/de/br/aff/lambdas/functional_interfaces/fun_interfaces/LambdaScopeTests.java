package de.br.aff.lambdas.functional_interfaces.fun_interfaces;

import org.junit.Test;
import org.springframework.core.convert.converter.Converter;

public class LambdaScopeTests {

  static int staticVariable;
  int globalVariable;

  @Test
  public void simpleLambdaTest() {
    Converter<Integer, String> converter = a -> a.toString();

    assert converter.convert(5).equals("5");
  }

  @Test
  public void thatLocalVariablesUsedInLambdaExpressionHaveToBeEffectivelyFinal() {

    int effectivelyFinal = 5;

    final int trulyFinal = 5;
    Converter<Integer, String> converter = a -> Integer.valueOf(effectivelyFinal + trulyFinal + a)
        .toString();

    // effectivelyFinal=5; if we try to do this, compiler will complain

    assert converter.convert(5).equals("15");
  }

  @Test
  public void thatGlobalAndStaticVariablesCanBeUsedInLambdaExpressionsWithoutLimitations() {

    Converter<Integer, String> converter = a -> Integer.valueOf(globalVariable + staticVariable + a)
        .toString();

    // we may change them whenever we want
    globalVariable = 1;
    staticVariable = 1;

    // but we have to pay attention that it's not important which value did they have when we defined abstract method
    // but that actual value used will be the one they had when we called that method
    assert converter.convert(5).equals("7");

    globalVariable = 5;
    staticVariable = 5;
    assert converter.convert(5).equals("15");
  }
}
