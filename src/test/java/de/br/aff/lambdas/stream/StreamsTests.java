package de.br.aff.lambdas.stream;

import de.br.aff.lambdas.domain.Person;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.Before;
import org.junit.Test;

public class StreamsTests {

  private List<Person> people;

  @Before //always creating new list
  public void init() {
    people = new ArrayList<>();

    for (int i = 0; i < 10; i++) {
      people.add(new Person(String.valueOf(i), String.valueOf(i), i));
    }
  }

  @Test
  public void thatFilterFiltersElementBasedOnPredicate() {
    List<Person> filteredPeople = people.stream()
        .filter(a -> a.getAge() > 5)
        .collect(Collectors.toList());

    assert 4 == filteredPeople.size();
    assert 6 == filteredPeople.get(0).getAge();

  }

  @Test
  public void thatSortedSortsBasedOnComparator() {
    people.get(0).setAge(2);

    List<Person> filteredPeople = people.stream()
        .sorted(Comparator.comparing(Person::getAge))
        .collect(Collectors.toList());

    assert 2 == filteredPeople.get(1).getAge();
    assert 2 == filteredPeople.get(2).getAge();
    assert 1 == filteredPeople.get(0).getAge();
    assert 9 == filteredPeople.get(9).getAge();

    // sorted only manipulate stream not the collection => original list keeps an order
    assert 2 == people.get(0).getAge();

  }

  @Test
  public void thatMapTransformsObjectsToAnotherOnes() {
    List<Integer> extractedAges = people.stream()
        .map(Person::getAge)
        .collect(Collectors.toList());

    assert 10 == extractedAges.size();
    assert 0 == extractedAges.get(0);

  }

  @Test
  public void thatAtLeastOnePersonIsFiveYearsOld() {
    boolean atLeastOneExists = people.stream()
        .anyMatch(person -> person.getAge() == 5);

    assert atLeastOneExists;
  }

  @Test
  public void thatAllPeopleAreYoungerThenTenYears() {
    boolean allMatch = people.stream()
        .allMatch(person -> person.getAge() < 10);

    assert allMatch;
  }

  @Test
  public void thatNobodyIsOlderThenTenYears() {
    boolean noneMatch = people.stream()
        .noneMatch(person -> person.getAge() > 10);

    assert noneMatch;
  }

  @Test
  public void thatCountCountsNumberOfElements() {
    long count = people.stream()
        .count();

    assert 10 == count;

  }

  @Test
  public void thatReduceAppliesGivenFunctionOnTheAllElements() {

    int sum = people.stream()
        .reduce(0,
            // Identity – an element that is the initial value of the reduction operation and the default result if the stream is empty
            (intermediate, p2) -> intermediate + p2.getAge(),
            // Accumulator – a function that takes two parameters: a partial result of the reduction operation and the next element of the stream
            Integer::sum); // Combiner – a function used to combine the partial results of the reduction operation when the reduction is parallelized (when we use parallel streams), or when there's a mismatch between the types of the accumulator arguments, in this case int and Person (intermediate, p2)

    assert 45 == sum;

    // If combiner is not necessary, we can omit it
    sum = people.stream()
        .map(Person::getAge)
        .reduce(0, Integer::sum);

    assert 45 == sum;

  }

  @Test
  public void parallelStreams() {
    /*
    Operations on sequential streams are performed on a single thread while operations on parallel streams are performed concurrent on multiple threads and thus way faster but only on big amount of elements, otherwise it's overkill.
    All operations existing on sequential streams can be performed on parallel streams

    We create sequential streams with stream() and parallel ones with parallelStream()

    When we use parallel streams, we should make sure that aggregate operations executed on the streams are:

    associative: the result is not affected by the order of the operands
    non-interfering: the operation doesn't affect the data source
    stateless and deterministic: the operation doesn't have state and produces the same output for a given input
    */
  }
}
