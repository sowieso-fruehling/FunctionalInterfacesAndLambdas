package de.br.aff.lambdas.functional_interfaces.fun_interfaces;

import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import org.junit.Test;

public class JavasFunInterfacesTests {

  //BinaryOperator specialization of BiFunction where all parameters are of the same type
  @Test
  public void thatBinaryOperatorTakesTwoArgumentsOfTheSameTypeAndReturnsThatType() {

    BinaryOperator<Integer> biOpSums = (a1, a2) -> a1 + a2;
    assert biOpSums.apply(1, 2) == 3;

    BinaryOperator<Integer> moreVerboseBiOpSums = (a1, a2) -> Integer.sum(a1, a2);
    assert moreVerboseBiOpSums.apply(1, 2) == 3;

    BinaryOperator<Integer> biOpLambdaSums = Integer::sum; //same as (a1,a2)->Integer.sum(a1,a2)
    assert biOpLambdaSums.apply(1, 2) == 3;

    BinaryOperator<String> biOpConcatenator = (a1, a2) -> a1 + a2;
    assert biOpConcatenator.apply("it", "concatenates").equalsIgnoreCase("itconcatenates");
  }

  @Test
  public void thatConsumerDoesSomeWorkWithoutReturningValue() {
    //doesn't return any value
    Consumer<String> consumer = System.out::println;
    consumer.accept("String to be printed");
  }

  @Test
  public void thatFunctionInterfaceTakesArgumentOfOneTypeAndTransformsItToAnotherOrTheSame() {

    Function<Integer, String> funAnotherReturnType = a -> a.toString();

    assert funAnotherReturnType.apply(3).equalsIgnoreCase("3");

    Function<Integer, Integer> funTheSameReturnType = a -> a * a;

    assert funTheSameReturnType.apply(3) == 3 * 3;

    //identity is static function of Function interface that returns a reference to Function that always returns its input argument
    assert Function.<Integer>identity().apply(3) == 3;

  }
}
