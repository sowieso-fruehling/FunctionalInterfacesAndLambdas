package de.br.aff.lambdas.service;

import de.br.aff.lambdas.domain.Person;
import org.junit.Test;

public class PersonServiceTests {

  @Test
  public void thatAgeHasHighestPriority() {
    Person person1 = new Person("a", "a", 1);
    Person person2 = new Person("b", "b", 2);
    assert new PersonService().goesFirst(person1, person2).equals(person2);
  }

  @Test
  public void thatIfTheyHaveSameAgeTheyWillBeComparedBasedOnLastName() {
    Person person1 = new Person("c", "a", 2);
    Person person2 = new Person("d", "b", 2);
    assert new PersonService().goesFirst(person1, person2).equals(person2);
  }

  @Test
  public void thatIfEverythingElseIsTheSameComparisonWillBeDoneBasedOnFirstName() {
    Person person1 = new Person("d", "e", 2);
    Person person2 = new Person("d", "a", 2);
    assert new PersonService().goesFirst(person1, person2).equals(person1);
  }

}
