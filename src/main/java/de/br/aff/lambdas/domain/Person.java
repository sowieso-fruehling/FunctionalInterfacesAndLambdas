package de.br.aff.lambdas.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
public class Person {

  private String lastName;
  private String firstName;
  private int age;
}
