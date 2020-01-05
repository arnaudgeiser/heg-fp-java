package ch.hearc.ig.exercice2.live;

import java.util.ArrayList;
import java.util.List;

public class Base {

  static List<Integer> divisibleBy2(List<Integer> numbers) {
    List<Integer> tmp = new ArrayList<>();
    for (Integer number : numbers) {
      if (number % 2 == 0) {
        tmp.add(number);
      }
    }
    return tmp;
  }

  static List<Integer> triple(List<Integer> numbers) {
    List<Integer> tmp = new ArrayList<>();
    for (Integer number : numbers) {
      tmp.add(number * 3);
    }
    return tmp;
  }
}
