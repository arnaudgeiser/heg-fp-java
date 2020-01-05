package ch.hearc.ig.exercice2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Exercice2BSolution {

  static List<Person> peoples = List
      .of(Person.of(1, "Arnaud", 29),
          Person.of(2, "Xavier", 22),
          Person.of(3, "Robert", 50),
          Person.of(4, "Damien", 37),
          Person.of(5, "Jerome", 90),
          Person.of(6, "Dominique", 40));

  static List<Movie> movies = List
      .of(Movie.of("Lord of the Rings", "Peter Jackson",
          List.of(Comment.of(1, 5),
              Comment.of(2, 7),
              Comment.of(3, 4),
              Comment.of(4, 9))),
          Movie.of("The Empire strikes back", "Steve Spielberg",
              List.of(Comment.of(1, 2),
                  Comment.of(2, 8),
                  Comment.of(3, 10),
                  Comment.of(4, 7))));

  static class Movie {

    final String title;
    final String author;
    final List<Comment> comments;

    public Movie(String title, String author,
        List<Comment> comments) {
      this.title = title;
      this.author = author;
      this.comments = comments;
    }

    private static Movie of(String title, String author,
        List<Comment> comments) {
      return new Movie(title, author, comments);
    }
  }

  static class Comment {

    final long peopleId;
    final long grade;

    private Comment(long peopleId, long grade) {
      this.peopleId = peopleId;
      this.grade = grade;
    }

    private static Comment of(long peopleId, long grade) {
      return new Comment(peopleId, grade);
    }
  }

  static class CommentByMovie {

    final Movie movie;
    final String name;
    final long grade;

    CommentByMovie(Movie movie, String name, long grade) {
      this.movie = movie;
      this.name = name;
      this.grade = grade;
    }

    static CommentByMovie of(Movie movie, String name, long grade) {
      return new CommentByMovie(movie, name, grade);
    }
  }

  static class Person {

    final long id;
    final String name;
    final int age;

    private Person(long id, String name, int age) {
      this.id = id;
      this.name = name;
      this.age = age;
    }

    private static Person of(int id, String nom, int age) {
      return new Person(id, nom, age);
    }
  }

  static <T> List<T> flatMap(List<List<T>> list) {
    return list.stream()
        .flatMap(Collection::stream)
        .collect(Collectors.toList());
  }

  /**
   * Applies the function to each element of the list
   *
   * @param list List of elements
   * @param function Function
   * @return List of elements of type R
   */
  static <T, R> List<R> map(List<T> list, Function<T, R> function) {
    List<R> returnList = new ArrayList<>();
    for (T element : list) {
      returnList.add(function.apply(element));
    }
    return returnList;
  }

  /**
   * Filters the elements of the list keeping only those who match a given predicate
   *
   * @param list List of elements
   * @param predicate Predicate
   * @return List of elements
   */
  static <T> List<T> filter(List<T> list, Predicate<T> predicate) {
    List<T> returnList = new ArrayList<>();
    for (T element : list) {
      if (predicate.test(element)) {
        returnList.add(element);
      }
    }
    return returnList;
  }

  static Person findByPersonId(long id) {
    return filter(peoples, p -> p.id == id).get(0);
  }

  static List<Person> peopleYoungerThan30() {
    return filter(peoples, p -> p.age < 30);
  }


  /**
   * Return the people acronyms Example :["Ar","Xa","Da"]
   */
  static List<String> peopleAcronyms() {
    return map(peoples, p -> p.name.substring(0, 2));
  }

  /**
   * Return the people acronyms Example :["Do","Da"]
   */
  static List<String> peopleAcronymsUppercaseStartWithD() {
    return map(filter(peoples, p -> p.name.startsWith("D")),
        p -> p.name.substring(0, 2).toUpperCase());
  }

  static List<CommentByMovie> commentByMovies() {
    return flatMap(
        map(movies,
            m -> map(m.comments,
                c -> new CommentByMovie(m, findByPersonId(c.peopleId).name, c.grade))));
  }
}
