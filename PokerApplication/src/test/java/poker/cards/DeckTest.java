package poker.cards;

import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class DeckTest {

    DeckOfCards deck;

    @Before
    public void setUp() {
        deck = new DeckOfCards();
    }

    @Test
    public void deckHas52Cards() {
        assertEquals(deck.howManyCardsLeft(), 52);
    }

    @Test
    public void deckDealsDifferentCards() {
        ArrayList<Card> cards = new ArrayList<>();
        for (int i = 0; i < 52; i++) {
            Card card = deck.dealRandomCard();
            if (!cards.contains(card)) {
                cards.add(card);
            }
        }
        assertEquals(cards.size(), 52);
    }

    @Test
    public void dealingReducesAmountOfCards1() {
        deck.dealRandomCard();
        assertEquals(deck.howManyCardsLeft(), 51);
    }

    @Test
    public void dealingReducesAmountOfCards5() {
        for (int i = 0; i < 5; i++) {
            deck.dealRandomCard();
        }
        assertEquals(deck.howManyCardsLeft(), 47);
    }

    @Test
    public void dealingReducesAmountOfCards52() {
        for (int i = 0; i < 52; i++) {
            deck.dealRandomCard();
        }
        assertEquals(deck.howManyCardsLeft(), 0);
    }

    @Test
    public void dealingReducesAmountOfCards100() {
        for (int i = 0; i < 100; i++) {
            deck.dealRandomCard();
        }
        assertEquals(deck.howManyCardsLeft(), 0);
    }

    @Test
    public void deckContainsAceOfSpade() {
        boolean found = false;
        Card aceOfSpade = new Card(1, "S");
        for (int i = 0; i < 52; i++) {
            Card card = deck.dealRandomCard();
            if (card.toString().equals(aceOfSpade.toString())) {
                found = true;
                break;
            }
        }
        assertTrue(found);
    }

    @Test
    public void deckContainsCorrectCards() {
        int[] found = new int[52];
        int suitNumber = 0;
        for (int i = 0; i < 52; i++) {
            Card card = deck.dealRandomCard();
            String suit = card.getSuit();
            if (suit.equals("S")) {
                suitNumber = 0;
            } else if (suit.equals("H")) {
                suitNumber = 1;
            } else if (suit.equals("D")) {
                suitNumber = 2;
            } else if (suit.equals("C")) {
                suitNumber = 3;
            }
            int position = 13 * suitNumber + card.getValue() - 1;
            found[position]++;
        }
        int max = found[0];
        int min = found[0];
        for (int i = 0; i < 52; i++) {
            max = Math.max(max, found[i]);
            min = Math.min(min, found[i]);
        }
        assertTrue(max == 1 && min == 1);
    }
    
    
    @Test
    public void addingIncreasesCardAmount5() {
        ArrayList<Card> cards = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            cards.add(deck.dealRandomCard());
        }
        for (int i = 0; i < 5; i++) {
            deck.addToDeck(cards.get(i));
        }
        assertEquals(deck.howManyCardsLeft(), 47);
    }

}
