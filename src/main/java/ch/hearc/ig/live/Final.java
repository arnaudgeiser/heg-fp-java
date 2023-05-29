package ch.hearc.ig.live;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

public class Final {

  private static final Function<Integer, Function<Integer, Boolean>> isDivisibleBy = n -> x -> n % x == 0;
  private static final Function<Integer, Function<Integer, Integer>> multiplyBy = n -> x -> x * n;
  private static final Function<Integer, Boolean> isEven = isDivisibleBy.apply(2);

  private static <T, R> List<R> map(List<T> list, Function<T, R> f) {
    List<R> result = new ArrayList<>();
    for(T element : list) {
      result.add(f.apply(element));
    }
    return result;
  }

  private static <T> List<T> filter(List<T> list, Function<T, Boolean> f) {
    List<T> result = new ArrayList<>();
    for(T element : list) {
      if(f.apply(element)) {
        result.add(element);
      }
    }
    return result;
  }

  public static void main(String[] args) {
    var list = List.of(1,2,3,4,5);
    var multipleBy5 = multiplyBy.apply(5);              // First-class function (you can store a function on a variable)
    System.out.println(map(list, x -> x * 2));         // Higher-order function with an anonymous function (== lambda function)
    System.out.println(map(list, multipleBy5));        // Higher-order function with a named function (multiplayBy5)
    System.out.println(filter(list, x -> x % 2 == 0)); // Higher-order function with an anonymous function (== lambda function)
    System.out.println(filter(list, isEven));          // Higher-order function with a named function (isEven)
  }

  // Below are the recursive versions of map and filter (called map2 and filter2 to make them compiled)

  public static <T> T head(List<T> numbers) {
    return numbers.get(0);
  }

  public static <T> List<T> tail(List<T> numbers) {
    return numbers.stream().skip(1).collect(toList());
  }

  public static <T> List<T> concat(T number, List<T> numbers) {
    return Stream.concat(Stream.of(number), numbers.stream()).collect(toList());
  }

  private static <T, R> List<R> map2(List<T> list, Function<T, R> f) {
    return list.isEmpty() ? emptyList() : concat(f.apply(head(list)), map2(tail(list), f));
  }

  private static <T> List<T> filter2(List<T> list, Function<T, Boolean> f) {
    return list.isEmpty() ? emptyList() :
        f.apply(head(list)) ? concat(head(list), filter2(tail(list), f)) : filter2(tail(list), f);
  }

}
