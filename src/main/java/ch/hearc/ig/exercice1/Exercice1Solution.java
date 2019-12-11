package ch.hearc.ig.exercice1;

import static java.util.stream.Collectors.toUnmodifiableList;

import ch.hearc.ig.util.ConsoleWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class Exercice1Solution {

  /*************************************************
   * Do not touch that !
   ************************************************/

  static class State {

    final List<Integer> bountyDeck;
    final List<Integer> player1Deck;
    final List<Integer> player2Deck;
    final Integer bounty;
    final Integer player1Card;
    final Integer player2Card;
    final Integer player1Score;
    final Integer player2Score;

    State(List<Integer> bountyDeck, List<Integer> player1Deck,
        List<Integer> player2Deck, Integer bounty, Integer player1Card, Integer player2Card,
        Integer player1Score, Integer player2Score) {
      this.bountyDeck = Collections.unmodifiableList(bountyDeck);
      this.player1Deck = Collections.unmodifiableList(player1Deck);
      this.player2Deck = Collections.unmodifiableList(player2Deck);
      this.bounty = bounty;
      this.player1Card = player1Card;
      this.player2Card = player2Card;
      this.player1Score = player1Score;
      this.player2Score = player2Score;
    }
  }

  static ConsoleWriter writer;

  private static void print(String texte) {
    writer.print(texte);
  }

  /*************************************************
   * Do not touch that !
   ************************************************/

  static void game() {
    var state = new State(Arrays.asList(1, 2, 3, 4, 5),
        Arrays.asList(1, 2, 3, 4, 5),
        Arrays.asList(1, 2, 3, 4, 5),
        null, null, null,
        0, 0);

    while (state.bountyDeck.size() > 0) {
      state = nextState(state);
      print(turnMessage(state));
    }
    print(endMessage(state.player1Score, state.player2Score));
  }


  static int player1Strategy(List<Integer> deck) {
    return getRandomCard(deck);
  }

  static int player2Strategy(List<Integer> deck, int card) {
    return getCard(deck, card);
  }

  static int getCard(List<Integer> deck, int card) {
    int index = deck.indexOf(card);
    return deck.get(index);
  }

  static List<Integer> shuffle(List<Integer> deck) {
    List<Integer> newDeck = new ArrayList<>(deck);
    Collections.shuffle(newDeck);
    return newDeck;
  }

  static int getRandomCard(List<Integer> deck) {
    List<Integer> newDeck = shuffle(deck);
    return newDeck.remove(0);
  }

  static List<Integer> without(List<Integer> deck, int card) {
    return deck.stream().filter(d -> d != card).collect(toUnmodifiableList());
  }

  static String endMessage(int player1Score, int player2Score) {
    if (player1Score > player2Score) {
      return "Score: " + player1Score + " - " + player2Score + "\nPlayer 1 wins!";
    } else if (player2Score > player1Score) {
      return "Score: " + player1Score + " - " + player2Score + "\nPlayer 2 wins!";
    }
    return "Score: " + player1Score + " - " + player2Score + "\nNo winner.";
  }

  static String turnMessage(State state) {
    return "*** New Turn! ***\nPlayer 1 plays " + state.player1Card + "\nPlayer 2 plays "
        + state.player2Card + "\n";
  }

  static State nextState(State state) {
    int bountyCard = getRandomCard(state.bountyDeck);
    int player1Card = player1Strategy(state.player1Deck);
    int player2Card = player2Strategy(state.player2Deck, bountyCard);
    var newBountyDeck = without(state.bountyDeck, bountyCard);
    var newPlayer1Deck = without(state.player1Deck, player1Card);
    var newPlayer2Deck = without(state.player2Deck, player2Card);
    var newPlayer1Score =
        player1Card > player2Card ? state.player1Score + bountyCard : state.player1Score;
    var newPlayer2Score =
        player2Card > player1Card ? state.player2Score + bountyCard : state.player2Score;
    return new State(newBountyDeck, newPlayer1Deck, newPlayer2Deck, bountyCard, player1Card,
        player2Card, newPlayer1Score,
        newPlayer2Score);
  }
}
