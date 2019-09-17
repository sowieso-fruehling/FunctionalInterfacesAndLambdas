package de.br.aff.lambdas.domain;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Person {

  private String lastName;
  private String firstName;
  private int age;
  private List<Person> friends;


  public Person(String last, String first, int i) {
    lastName = last;
    firstName = first;
    age = i;
  }
}
