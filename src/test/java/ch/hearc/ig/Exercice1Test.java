package ch.hearc.ig;

import static ch.hearc.ig.Exercice1.endMessage;
import static ch.hearc.ig.Exercice1.game;
import static ch.hearc.ig.Exercice1.getCard;
import static ch.hearc.ig.Exercice1.getRandomCard;
import static ch.hearc.ig.Exercice1.nextState;
import static ch.hearc.ig.Exercice1.player1Strategy;
import static ch.hearc.ig.Exercice1.player2Strategy;
import static ch.hearc.ig.Exercice1.shuffle;
import static ch.hearc.ig.Exercice1.turnMessage;
import static ch.hearc.ig.Exercice1.without;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import ch.hearc.ig.Exercice1.State;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.ArgumentCaptor;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class Exercice1Test {

  private ConsoleWriter consoleWriter;

  @BeforeEach
  void beforeAll() {
    consoleWriter = spy(new ConsoleWriter());
    Exercice1.writer = consoleWriter;
  }

  @Test
  @Order(1)
  @DisplayName("Shuffle should preserve array size")
  void shuffle_ShouldPreserveArraySize() {
    List<Integer> array = Arrays.asList(1, 2, 3, 4, 5);
    shuffle(array);
    assertThat(array.size()).isEqualTo(5);
  }

  @Test
  @Order(2)
  @DisplayName("Shuffle should preserve all elements in the original array")
  void shuffle_ShouldPreserveallElementsInTheOriginalArray() {
    List<Integer> array = Arrays.asList(1, 2, 3, 4, 5);
    shuffle(array);

    assertAll(
        () -> assertThat(array).contains(1),
        () -> assertThat(array).contains(2),
        () -> assertThat(array).contains(3),
        () -> assertThat(array).contains(4),
        () -> assertThat(array).contains(5));
  }

  @Test
  @Order(3)
  @DisplayName("Shuffle should not alter original array")
  void shuffle_ShouldNotAlterOriginalArray() {
    List<Integer> array = Arrays.asList(1, 2, 3, 4, 5);
    List<Integer> afterShuffle = shuffle(array);
    assertAll(
        () -> assertThat(array).isNotSameAs(afterShuffle),
        () -> assertThat(array).containsSequence(1, 2, 3, 4, 5));
  }

  @Test
  @Order(4)
  @DisplayName("Get random card should preserve original array")
  void getRandomCard_ShouldPreserveOriginalArray() {
    List<Integer> array = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
    getRandomCard(array);
    assertThat(array.size()).isEqualTo(5);
  }

  @Test
  @Order(5)
  @DisplayName("Get card should preserve original array")
  void getCard_ShouldPreserveOriginalArray() {
    List<Integer> array = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
    int card = getCard(array, 2);
    assertAll(() -> assertThat(array.size()).isEqualTo(5),
        () -> assertThat(card).isEqualTo(2));
  }

  @Test
  @Order(6)
  @DisplayName("Player1Strategy should not do side effects")
  void player1Strategy_ShouldNotDoSideEffects() {
    List<Integer> array = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
    player1Strategy(array);
    verify(consoleWriter, times(0)).print(anyString());
  }

  @Test
  @Order(7)
  @DisplayName("Player2Strategy should not do side effects")
  void player2Strategy_ShouldNotDoSideEffects() {
    List<Integer> array = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
    player2Strategy(array, 2);
    verify(consoleWriter, times(0)).print(anyString());
  }

  @Test
  @Order(8)
  @DisplayName("Without should correctly remove element in array")
  void without_ShouldCorrectlyRemoveElementInArrray() {
    List<Integer> newDeck = without(Arrays.asList(1, 2, 3, 4, 5), 4);
    assertThat(newDeck).isEqualTo(Arrays.asList(1, 2, 3, 5));
  }

  @Test
  @Order(9)
  @DisplayName("Without should not alter original array")
  void without_ShouldNotAlterOriginalArray() {
    List<Integer> original = Arrays.asList(1, 2, 3, 4, 5);
    without(original, 4);
    assertThat(original).isEqualTo(Arrays.asList(1, 2, 3, 4, 5));
  }

  @Test
  @Order(10)
  @DisplayName("Endmessage should correctly print result if player 1 wins")
  void endMessage_ShouldCorrectlyPrintResultIfPlayer1Wins() {
    assertThat(endMessage(1, 0)).isEqualTo("Score: 1 - 0\nPlayer 1 wins!");
  }

  @Test
  @Order(11)
  @DisplayName("Endmessage should correctly print result if player 2 wins")
  void endMessage_ShouldCorrectlyPrintResultIfPlayer2Wins() {
    assertThat(endMessage(0, 1)).isEqualTo("Score: 0 - 1\nPlayer 2 wins!");
  }

  @Test
  @Order(12)
  @DisplayName("Endmessage should correctly print result if nobody wins")
  void endMessage_ShouldCorrectlyPrintResultIfNobodyWins() {
    assertThat(endMessage(0, 0)).isEqualTo("Score: 0 - 0\nNo winner.");
  }

  @Test
  @Order(13)
  @DisplayName("NextState should remain consistant")
  void nextState_ShouldRemainConsistant() {
    var state = new State(List.of(1, 2, 3, 4, 5),
        List.of(1, 2, 3, 4, 5),
        List.of(1, 2, 3, 4, 5),
        null,
        null,
        null,
        2,
        4);

    for (int i = 0; i < 10; i++) {
      var nextState = nextState(state);
      if (nextState.player1Card > nextState.player2Card) {
        assertThat(nextState.player1Score).isEqualTo(state.player1Score + nextState.bounty);
        assertThat(nextState.player2Score).isEqualTo(state.player2Score);
      } else if
      (nextState.player2Card > nextState.player1Card) {
        assertThat(nextState.player2Score).isEqualTo(state.player2Score + nextState.bounty);
        assertThat(nextState.player1Score).isEqualTo(state.player1Score);
      } else {
        assertThat(nextState.player2Score).isEqualTo(state.player2Score);
        assertThat(nextState.player2Score).isEqualTo(state.player2Score);
      }
    }
  }

  @Test
  @Order(14)
  @DisplayName("NextState should remove cards from all three decks")
  void nextState_ShouldRemoveCardsFromAllThreeDecks() {
    var state = new State(List.of(1, 2, 3, 4, 5),
        List.of(1, 2, 3, 4, 5),
        List.of(1, 2, 3, 4, 5),
        null,
        null,
        null,
        2,
        4);

    State newState = nextState(state);
    assertAll(
        () -> assertThat(newState.bountyDeck.size()).isEqualTo(4),
        () -> assertThat(newState.player1Deck.size()).isEqualTo(4),
        () -> assertThat(newState.player2Deck.size()).isEqualTo(4),
        () -> assertThat(newState.bountyDeck).doesNotContain(newState.bounty),
        () -> assertThat(newState.player1Deck).doesNotContain(newState.player1Card),
        () -> assertThat(newState.player2Deck).doesNotContain(newState.player2Card));
  }

  @Test
  @Order(15)
  @DisplayName("TurnMessage should output correct value")
  void turnMessage_ShouldOuputCorrectValue() {
    var state = new State(List.of(1, 2, 3, 4, 5),
        List.of(1, 2, 3, 4, 5),
        List.of(1, 2, 3, 4, 5),
        4,
        4,
        3,
        2,
        4);

    assertThat(turnMessage(state))
        .isEqualTo("*** New Turn! ***\nPlayer 1 plays 4\nPlayer 2 plays 3\n");
  }

  @Test
  @Order(16)
  void Game_ShouldCall5TimesNextState() {
    ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
    doNothing().when(consoleWriter).print(captor.capture());
    game();

    String output = String.join("", captor.getAllValues());

    assertAll(
        () -> assertThat(countOccurences("\\*\\*\\* New Turn! \\*\\*\\*", output)).isEqualTo(5),
        () -> assertThat(countOccurences("Player 1 plays [0-9]", output)).isEqualTo(5),
        () -> assertThat(countOccurences("Player 2 plays [0-9]", output)).isEqualTo(5),
        () -> assertThat(countOccurences("Score: [0-9] - [0-9]", output)).isEqualTo(1),
        () -> assertThat(countOccurences("Player [1-2] wins!|No winner.", output)).isEqualTo(1));

    System.out.println(output);

  }

  private int countOccurences(String pattern, String texte) {
    int count = 0;
    Matcher matcher = Pattern.compile(pattern).matcher(texte);
    while (matcher.find()) {
      count++;
    }
    return count;
  }
}
