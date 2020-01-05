package ch.hearc.ig.exercice2;

import java.util.function.Function;

public class Exercice2ASolution {

  static Function<Integer, Integer> doubleNumber = Exercice2ASolution::_doubleNumber;
  static Function<Integer, Integer> add5 = x -> Exercice2ASolution._add(x, 5);

  private static int _doubleNumber(int x) {
    return x * 2;
  }

  private static int _add(int x, int y) {
    return x + y;
  }

}
