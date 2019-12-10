package ch.hearc.ig;

import static ch.hearc.ig.Exercice1.shuffle;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import ch.hearc.ig.Exercice1;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Exercice1Test {

  @Test
  @DisplayName("Shuffle should preserve array size")
  void shuffle_ShouldPreserveArraySize() {
    List<Integer> array = Arrays.asList(1, 2, 3, 4, 5);
    shuffle(array);
    assertThat(array.size()).isEqualTo(5);
  }

  @Test
  @DisplayName("Shuffle should preserve all elements in the original array")
  void shuffle_ShouldPreserveallElementsInTheOriginalArray() {
    List<Integer> array = Arrays.asList(1, 2, 3, 4, 5);
    shuffle(array);
    assertThat(array).contains(1);
    assertThat(array).contains(2);
    assertThat(array).contains(3);
    assertThat(array).contains(4);
    assertThat(array).contains(5);
  }

  @Test
  @DisplayName("Shuffle should not alter original array")
  void shuffle_ShouldNotAlterOriginalArray() {
    List<Integer> array = Arrays.asList(1, 2, 3, 4, 5);
    List<Integer> afterShuffle = shuffle(array);
    assertAll(
        () -> assertThat(array).isNotEqualTo(afterShuffle),
        () -> assertThat(array).containsSequence(1, 2, 3, 4, 5));
  }

}
