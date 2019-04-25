package poker.cards;

import java.util.ArrayList;
import java.util.Random;

public class DeckOfCards {

    private ArrayList<Card> deck;
    private Random random;

    public DeckOfCards() {
        this.deck = new ArrayList<>();
        this.random = new Random();

        String suite = "C"; //CLUB
        for (int i = 1; i <= 13; i++) {
            deck.add(new Card(i, suite)); //Card values are 1-13
        }
        suite = "S"; //SPADE
        for (int i = 1; i <= 13; i++) {
            deck.add(new Card(i, suite));
        }
        suite = "H"; //HEART
        for (int i = 1; i <= 13; i++) {
            deck.add(new Card(i, suite));
        }
        suite = "D"; //DIAMOND
        for (int i = 1; i <= 13; i++) {
            deck.add(new Card(i, suite));
        }

    }

    /**
     * Method adds a given card into deck, which is ArrayList of Card-object.
     *
     * @param newCard the card to be put in deck
     */
    public void addToDeck(Card newCard) {
        this.deck.add(newCard);
    }

    /**
     * Checks how many cards are there in the deck.
     *
     * @return number of cards in deck
     */
    public int howManyCardsLeft() {
        return this.deck.size();
    }

    /**
     * Method deals a random card from the deck: 1. Check if there are cards in
     * deck 2. If there are, remove and return a random card from deck. 3. If
     * there are no cards in deck, return null.
     *
     * @return random card if can, else null
     */
    public Card dealRandomCard() {
        int cardsLeft = this.deck.size();
        int chosen;
        if (cardsLeft == 0) {
            return null;
        } else if (cardsLeft == 1) {
            chosen = 0;
        } else {
            chosen = this.random.nextInt(cardsLeft - 1);
        }
        Card card = deck.get(chosen);
        deck.remove(card);
        return card;
    }

}
