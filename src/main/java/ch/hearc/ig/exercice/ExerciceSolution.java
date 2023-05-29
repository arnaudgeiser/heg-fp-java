package ch.hearc.ig.exercice;

import static ch.hearc.ig.exercice.Exercice.peoples;
import static ch.hearc.ig.exercice.Exercice.movies;
import static ch.hearc.ig.exercice.Exercice.flatten;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class ExerciceSolution {

  /** Stores _doubleNumber inside doubleNumber */
  static Function<Integer, Integer> doubleNumber = Exercice::_doubleNumber;

  /** Creates an add5 function from the doubleNumber function (by calling it) */
  static Function<Integer, Integer> add5 = number -> Exercice._add(5, number);

  /**
   * Applies the function to each element of the list
   *
   * @param list List of elements
   * @param function Function
   * @return List of elements of type R
   */
  static <T, R> List<R> map(List<T> list, Function<T, R> function) {
    List<R> result = new ArrayList<>();
    for(T element : list) {
      result.add(function.apply(element));
    }
    return result;
  }

  /**
   * Filters the elements of the list keeping only those who match a given predicate
   *
   * @param list List of elements
   * @param function Predicate
   * @return List of elements
   */
  static <T> List<T> filter(List<T> list, Function<T, Boolean> function) {
    List<T> result = new ArrayList<>();
    for(T element : list) {
      if(function.apply(element)) {
        result.add(element);
      }
    }
    return result;
  }

  static Exercice.Person findByPersonId(long id) {
    return filter(peoples, person -> person.id() == id).get(0);
  }

  static List<Exercice.Person> peopleYoungerThan30() {
    return filter(peoples, person -> person.age() < 30);
  }


  /**
   * Return the people acronyms Example :["Ar","Xa","Da"]
   */
  static List<String> peopleAcronyms() {
    return map(peoples, person -> person.name().substring(0, 2));
  }

  /**
   * Return the people acronyms Example :["Do","Da"]
   */
  static List<String> peopleAcronymsUppercaseStartWithD() {
    return filter(map(peoples, person -> person.name().substring(0, 2).toUpperCase()), name -> name.startsWith("D"));
  }

  static List<Exercice.CommentByMovie> commentByMovies() {
    return flatten(
        map(movies, movie ->
            map(movie.comments(), comment -> new Exercice.CommentByMovie(movie, movie.title(), comment.grade()))));
  }
}
