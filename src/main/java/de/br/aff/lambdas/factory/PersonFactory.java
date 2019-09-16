package de.br.aff.lambdas.factory;

import de.br.aff.lambdas.domain.Person;

@FunctionalInterface
public interface PersonFactory<P extends Person> {
  P create(String firstName, String lastName, int age);
}
