package ch.hearc.ig.exercice1;

import static java.util.stream.Collectors.toUnmodifiableList;

import ch.hearc.ig.util.ConsoleWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Exercice1 {

  /*************************************************
   * Do not touch that ! -- START
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

  static ConsoleWriter writer = new ConsoleWriter();

  private static void print(String texte) {
    writer.print(texte);
  }

  static List<Integer> without(List<Integer> deck, int card) {
    return deck.stream().filter(d -> d != card).collect(toUnmodifiableList());
  }

  public static void main(String[] args) {
    game();
  }

  /*************************************************
   * Do not touch that ! -- END
   ************************************************/

  static void game() {
    var bountyDeck = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
    var player1Deck = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
    var player2Deck = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));

    var player1Score = 0;
    var player2Score = 0;

    while (bountyDeck.size() > 0) {
      print("*** New turn ***");
      var bounty = getRandomCard(bountyDeck);
      print("Bounty is " + bounty);
      var player1Card = player1Strategy(player1Deck);
      var player2Card = player2Strategy(player2Deck, bounty);
      if (player1Card > player2Card) {
        player1Score += bounty;
      }
      if (player2Card > player1Card) {
        player2Score += bounty;
      }
      print("");
    }

    print("Score: " + player1Score + " - " + player2Score);
    if (player1Score > player2Score) {
      print("Player 1 wins");
    } else if (player2Score > player1Score) {
      print("Player 2 wins");
    } else {
      print("No winner");
    }

  }


  static int player1Strategy(List<Integer> deck) {
    var card = getRandomCard(deck);
    print("Player 1 plays " + card);
    return card;
  }

  static int player2Strategy(List<Integer> deck, int card) {
    var newCard = getCard(deck, card);
    print("Player 2 plays " + newCard);
    return newCard;
  }

  static int getCard(List<Integer> deck, int card) {
    int index = deck.indexOf(card);
    return deck.remove(index);
  }

  static List<Integer> shuffle(List<Integer> deck) {
    Collections.shuffle(deck);
    return deck;
  }

  static int getRandomCard(List<Integer> deck) {
    shuffle(deck);
    return deck.remove(0);
  }

  static String turnMessage(State state) {
    return "";
  }

  static String endMessage(int player1Score, int player2Score) {
    return "";
  }

  static State nextState(State state) {
    return new State(state.bountyDeck,
        state.player1Deck,
        state.player2Deck,
        state.bounty,
        state.player1Card,
        state.player2Card,
        state.player1Score,
        state.player2Score);
  }

}
