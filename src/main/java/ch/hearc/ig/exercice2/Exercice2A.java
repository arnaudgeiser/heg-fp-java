package ch.hearc.ig.exercice2;

import java.util.function.Function;

public class Exercice2A {

  static Function<Integer, Integer> doubleNumber;
  static Function<Integer, Integer> add5;

  private static int _doubleNumber(int x) {
    return x * 2;
  }

  private static int _add(int x, int y) {
    return x + y;
  }

}
