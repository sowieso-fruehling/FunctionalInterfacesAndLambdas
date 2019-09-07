package de.br.aff.lambdas.functional_interfaces.fun_interfaces;

import de.br.aff.lambdas.domain.Person;
import java.util.HashMap;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;

public class MapLeveragingFunInterfaces {

  private Map<String, Person> people;

  @Before //always creating new list
  public void init() {

    people = new HashMap<>();

    for (int i = 0; i < 10; i++) {
      people.put(String.valueOf(i), new Person(String.valueOf(i), String.valueOf(i), i));
    }
  }

  @Test
  public void thatForEachIteratesThroughWholeMap() {
    people.forEach((k, v) -> System.out.println(
        "k = " + k + " and v.age = " + v.getAge())); //Passing BiConsumer interface reference
  }
}
