package de.br.aff.lambdas.service;

import de.br.aff.lambdas.domain.Person;

class PersonService {

  public Person goesFirst(Person first, Person second) {

    /*
    //this wouldn't compile
    Function<Person, Integer> cmpPersonAge = Person::getAge;
    MyComparator<Person> cmp = ComparatorWithFunInterfaces.comparing(cmpPersonAge);

    //this compiles because int from 'int getAge()' gets boxed into Integer which is implementing Comparable
    MyComparator<Person> ageCmp = MyComparator.comparing(Person::getAge);
    */

    // so we can append more conditions for comparing
    MyComparator<Person> cmp = MyComparator
        .comparing(Person::getAge)
        .thenCompare(Person::getLastName)
        .thenCompare(Person::getFirstName);

    return cmp.compare(first, second) >= 0 ? first : second;
  }

}
