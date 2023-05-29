package ch.hearc.ig.exercice;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Exercice {

  /* DO NOT ANYTHING ANYTHING FROM THERE .... */

  static List<Person> peoples = List
      .of(new Person(1, "Arnaud", 29),
          new Person(2, "Xavier", 22),
          new Person(3, "Robert", 50),
          new Person(4, "Damien", 37),
          new Person(5, "Jerome", 90),
          new Person(6, "Dominique", 40));

  static List<Movie> movies = List
      .of(new Movie("Lord of the Rings", "Peter Jackson",
          List.of(new Comment(1, 5),
              new Comment(2, 7),
              new Comment(3, 4),
              new Comment(4, 9))),
          new Movie("The Empire strikes back", "Steve Spielberg",
              List.of(new Comment(1, 2),
                  new Comment(2, 8),
                  new Comment(3, 10),
                  new Comment(4, 7))));

  record Movie(String title, String author, List<Comment> comments) {}
  record Comment(long peopleId, long grade) {}
  record CommentByMovie(Movie movie, String title, long grade) {}
  record Person(long id, String name, int age) {}

  static <T> List<T> flatten(List<List<T>> list) {
    return list.stream()
        .flatMap(Collection::stream)
        .collect(Collectors.toList());
  }

  public static int _doubleNumber(int x) {
    return x * 2;
  }

  public static int _add(int x, int y) {
    return x + y;
  }

  /* UNTIL THERE ... */

  /** Stores _doubleNumber inside doubleNumber */
  static Function<Integer, Integer> doubleNumber;

  /** Creates an add5 function from the doubleNumber function (by calling it) */
  static Function<Integer, Integer> add5;

  /**
   * Applies the function to each element of the list
   *
   * @param list List of elements
   * @param function Function
   * @return List of elements of type R
   */
  static <T, R> List<R> map(List<T> list, Function<T, R> function) {
    return Collections.emptyList();
  }

  /**
   * Filters the elements of the list keeping only those who match a given predicate
   *
   * @param list List of elements
   * @param predicate Predicate
   * @return List of elements
   */
  static <T> List<T> filter(List<T> list, Function<T, Boolean> predicate) {
    return Collections.emptyList();
  }

  static Person findByPersonId(long id) {
    return null;
  }

  static List<Person> peopleYoungerThan30() {
    return Collections.emptyList();
  }


  /**
   * Return the people acronyms Example :["Ar","Xa","Da"]
   */
  static List<String> peopleAcronyms() {
    return Collections.emptyList();
  }

  /**
   * Return the people acronyms Example :["Do","Da"]
   */
  static List<String> peopleAcronymsUppercaseStartWithD() {
    return Collections.emptyList();
  }

  static List<CommentByMovie> commentByMovies() {
    return Collections.emptyList();
  }
}
