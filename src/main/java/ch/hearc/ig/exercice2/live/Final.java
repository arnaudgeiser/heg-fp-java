package ch.hearc.ig.exercice2.live;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Final {

  private static Function<Integer, Function<Integer, Boolean>> isDivisibleBy = n -> x -> n % x == 0;
  private static Function<Integer, Function<Integer, Integer>> multiplyBy = n -> x -> x * n;
  private static Function<Integer, Boolean> isEven = isDivisibleBy.apply(2);

  private static <T, R> List<R> iter(List<T> numbers, Function<T, R> f, Function<T, Boolean> p) {
    List<R> tmp = new ArrayList<>();
    for (T number : numbers) {
      if (p.apply(number)) {
        tmp.add(f.apply(number));
      }
    }
    return tmp;
  }

  private static <T, R> List<R> map(List<T> list, Function<T, R> f) {
    return iter(list, f, __ -> true);
  }

  private static <T> List<T> filter(List<T> list, Function<T, Boolean> p) {
    return iter(list, x -> x, p);
  }

  private static Function<List<Integer>, List<Integer>> divisbleBy2 = list -> filter(list, isEven);
  private static Function<List<Integer>, List<Integer>> triple = list -> map(list,
      multiplyBy.apply(3));
}
