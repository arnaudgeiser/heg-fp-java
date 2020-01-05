package ch.hearc.ig.exercice2;

import static ch.hearc.ig.exercice2.Exercice2A.*;
import static ch.hearc.ig.exercice2.Exercice2B.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class Exercice2Test {

  @Test
  @Order(1)
  @DisplayName("First class functions : You can store a function in a variable")
  void firstClassFunctions_YouCanStoreAFunctionInAVariable() {
    var result = doubleNumber.apply(5);
    assertThat(result).isEqualTo(10);
  }

  @Test
  @Order(2)
  @DisplayName("Higher order functions : You can create a function from a function")
  void hiherOrderFunction_YouCanCreateAFunctionFromAFunction() {
    var result = add5.apply(2);
    assertThat(result).isEqualTo(7);
  }

  @Test
  @Order(3)
  @DisplayName("I can pass a list and a function to apply a transformation via the map() function")
  void testMap() {
    var result = map(List.of(1, 2, 3, 4), x -> x * 2);
    assertThat(result).isEqualTo(List.of(2, 4, 6, 8));
  }

  @Test
  @Order(4)
  @DisplayName("I can pass a list and a predicate to filter out items via the filter() function")
  void testFilter() {
    var result = filter(List.of(1, 2, 3, 4), x -> x % 2 == 0);
    assertThat(result).isEqualTo(List.of(2, 4));
  }

  @Test
  @Order(5)
  @DisplayName("I can create a function that close over a variable in its environnement, that's called a closure!")
  void testFindPersonById() {
    var result = findByPersonId(1);
    assertThat(result.name).isEqualTo("Arnaud");
  }

  @Test
  @Order(6)
  @DisplayName("I can get people younger than 30")
  void testPeopleYoungerThan30() {
    var result = peopleYoungerThan30();
    assertThat(result.size()).isEqualTo(2);
  }

  @Test
  @Order(7)
  @DisplayName("I can get two letters acronyms")
  void testPeopleAcronyms() {
    var result = peopleAcronyms();
    assertThat(result.size()).isEqualTo(6);
    result.forEach(r -> assertThat(r.length()).isEqualTo(2));
  }

  @Test
  @Order(8)
  @DisplayName("I can get two letters acronyms in uppercase that starts with D")
  void testPeopleAcronymsUppercaseStartWithD() {
    var result = peopleAcronymsUppercaseStartWithD();
    assertThat(result.size()).isEqualTo(2);
    result.forEach(r -> {
      assertAll(() -> assertThat(r.length()).isEqualTo(2),
          () -> assertThat(r.charAt(0)).isEqualTo('D'),
          () -> assertThat(r.charAt(1)).isUpperCase());
    });
  }

  @Test
  @Order(9)
  @DisplayName("I can get all comments for each movie and people")
  void testCommentByMovies() {
    var result = commentByMovies();
    assertThat(result.size()).isEqualTo(8);
  }
}