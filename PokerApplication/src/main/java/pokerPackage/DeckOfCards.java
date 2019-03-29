package pokerPackage;

import java.util.ArrayList;
import java.util.Random;

public class DeckOfCards {

    private ArrayList<Card> deck;
    private Random random;

    public DeckOfCards() {
        this.deck = new ArrayList<>();
        this.random = new Random();

        String suite = CardSuits.CLUB;
        for (int i = 1; i <= 13; i++) {
            deck.add(new Card(i, suite));
        }
        suite = CardSuits.SPADE;
        for (int i = 1; i <= 13; i++) {
            deck.add(new Card(i, suite));
        }
        suite = CardSuits.HEART;
        for (int i = 1; i <= 13; i++) {
            deck.add(new Card(i, suite));
        }
        suite = CardSuits.DIAMOND;
        for (int i = 1; i <= 13; i++) {
            deck.add(new Card(i, suite));
        }
        
    }

    public void addToDeck(Card newCard) {
        this.deck.add(newCard);
    }

    public Card dealRandomCard() {
        int cardsLeft = this.deck.size();
        int chosen = this.random.nextInt(cardsLeft - 1);
        Card card = deck.get(chosen);
        deck.remove(card);
        return card;
    }

}
