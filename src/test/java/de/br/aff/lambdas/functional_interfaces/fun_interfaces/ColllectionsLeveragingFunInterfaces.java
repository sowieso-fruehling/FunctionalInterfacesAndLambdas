package de.br.aff.lambdas.functional_interfaces.fun_interfaces;

import de.br.aff.lambdas.domain.Person;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import org.junit.Before;
import org.junit.Test;

public class ColllectionsLeveragingFunInterfaces {

  private List<Person> people;

  @Before //always creating new list
  public void init() {
    people = new ArrayList<>();

    for (int i = 0; i < 10; i++) {
      people.add(new Person(String.valueOf(i), String.valueOf(i), i));
    }
  }

  @Test
  public void thatForEachIteratesThroughWholeList() {
    people.forEach(System.out::println); //Passing Consumer interface reference
  }

  @Test
  public void thatRemoveIfRemovesElementsFromList() {
    people.removeIf(p -> p.getAge() < 5); // Passing Predicate interface reference

    assert people.size() == 5;
  }


  @Test
  public void thatReplaceAllRemovesElementsFromList() {
    people.replaceAll(p -> { // Passing UnaryOperator interface reference
      p.setAge(18);
      return p;
    });

    assert people.get(new Random().nextInt(10)).getAge() == 18;
  }

  @Test
  public void thatWeCanSortCollectionUsingComparator() {

    people.get(0).setAge(2);
    people.sort(Comparator.comparing(Person::getAge).thenComparing(Person::getLastName));

    assert people.get(0).getAge() == 1;
    assert people.get(1).getAge() == 2;
    assert people.get(1).getLastName().equals("0");
  }
}
