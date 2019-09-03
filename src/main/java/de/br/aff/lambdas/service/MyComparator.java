package de.br.aff.lambdas.service;

import java.util.function.Function;

@FunctionalInterface
public interface MyComparator<T> {

  int compare(T p1, T p2);

  //Function<Person,Comparable> - we use Comparable because Integer, Double, String they all inherit Comparable
  static <U> MyComparator<U> comparing(Function<U, Comparable> comparingCondition) {
    return (a1, a2) -> comparingCondition.apply(a1).compareTo(comparingCondition.apply(a2));
  }

  default MyComparator<T> thenCompare(MyComparator<T> secondMyComparator) {
    //it will first try to compare based on the first comparator and if they are the same, it will continue with the second
    return (p1, p2) -> compare(p1, p2) == 0 ? secondMyComparator.compare(p1, p2) : compare(p1, p2);
  }

  // we use this method so we can provide only lambda expressions as arguments for thenCompare method
  default MyComparator<T> thenCompare(Function<T, Comparable> f) {
    return thenCompare(comparing(f));
  }
}
