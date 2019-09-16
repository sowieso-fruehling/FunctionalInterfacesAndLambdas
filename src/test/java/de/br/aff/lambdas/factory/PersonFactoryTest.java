package de.br.aff.lambdas.factory;

import de.br.aff.lambdas.domain.Person;
import org.junit.Test;

public class PersonFactoryTest {

  @Test
  public void thatPersonIsCreated() {
    PersonFactory<Person> p = Person::new; // this is how we're creating a reference to Person constructor. For this PersonFactory has to be functional interface

    Person person = p.create("John", "Doe", 27); // compiler automatically matches the right constructor based on signature of create method ( if create didn't have any arguments it would use default constructor)

    assert person != null;
    assert person.getAge() == 27;

  }

}
