package ch.hearc.ig;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Exercice1 {

  public static void game() {
    var bountyDeck = Arrays.asList(1, 2, 3, 4, 5);
    var player1Deck = Arrays.asList(1, 2, 3, 4, 5);
    var player2Deck = Arrays.asList(1, 2, 3, 4, 5);

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

  static void print(String texte) {
    System.out.print(texte);
  }

}
