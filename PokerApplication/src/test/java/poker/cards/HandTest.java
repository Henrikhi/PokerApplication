package poker.cards;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class HandTest {

    Hand hand;
    Hand hand2;

    @Before
    public void setUp() {
        hand = new Hand();
        hand2 = new Hand(new Card(1, "S"),
                new Card(1, "H"),
                new Card(3, "C"),
                new Card(13, "S"),
                new Card(11, "D"));
    }

    @Test
    public void handIsEmpty() {
        assertTrue(hand.getHand().isEmpty());
    }

    @Test
    public void deal5deals5() {
        hand.deal5();
        assertEquals(hand.getHand().size(), 5);
    }

    @Test
    public void emptyHandMethodWorks() {
        hand.deal5();
        hand.emptyHand();
        assertTrue(hand.getHand().isEmpty());
    }

    @Test
    public void replaceWorks() {
        Card c0 = hand2.getCard(0);
        Card c1 = hand2.getCard(1);
        Card c2 = hand2.getCard(2);
        Card c3 = hand2.getCard(3);
        Card c4 = hand2.getCard(4);

        hand2.replace(2);

        Card n0 = hand2.getCard(0);
        Card n1 = hand2.getCard(1);
        Card n2 = hand2.getCard(2);
        Card n3 = hand2.getCard(3);
        Card n4 = hand2.getCard(4);
        

        assertTrue(c0.equals(n0)
                && c1.equals(n1)
                && c3.equals(n3)
                && c4.equals(n4)
                && !c2.equals(n2)
        );
    }

    @Test
    public void getCardWorks0() {
        Card card1 = new Card(1, "S");
        Card card2 = hand2.getCard(0);
        assertTrue(card1.equals(card2));
    }

    @Test
    public void getCardWorks4() {
        Card card1 = new Card(11, "D");
        Card card2 = hand2.getCard(4);
        assertTrue(card1.equals(card2));
    }

    @Test
    public void checkStraightFlush() {
        Card card0 = new Card(13, "S");
        Card card1 = new Card(10, "S");
        Card card2 = new Card(11, "S");
        Card card3 = new Card(12, "S");
        Card card4 = new Card(1, "S");

        Hand hand = new Hand(card0, card1, card2, card3, card4);
        assertEquals(hand.checkHand(), 25); //the value for straightFlush was 25
    }

    @Test
    public void checkStraightFlush2() {
        Card card0 = new Card(3, "S");
        Card card1 = new Card(2, "S");
        Card card2 = new Card(4, "S");
        Card card3 = new Card(5, "S");
        Card card4 = new Card(1, "S");

        Hand hand = new Hand(card0, card1, card2, card3, card4);
        assertEquals(hand.checkHand(), 25); //the value for straightFlush was 25
    }

    @Test
    public void checkStraightFlush3() {
        Card card0 = new Card(13, "S");
        Card card1 = new Card(2, "S");
        Card card2 = new Card(11, "S");
        Card card3 = new Card(12, "S");
        Card card4 = new Card(1, "S");

        Hand hand = new Hand(card0, card1, card2, card3, card4);
        assertNotEquals(hand.checkHand(), 25); //the value for straightFlush was 25
    }

    @Test
    public void checkFourOfAKind() {
        Card card0 = new Card(13, "S");
        Card card1 = new Card(13, "H");
        Card card2 = new Card(13, "D");
        Card card3 = new Card(13, "C");
        Card card4 = new Card(1, "S");

        Hand hand = new Hand(card0, card1, card2, card3, card4);
        assertEquals(hand.checkHand(), 12);
    }

    @Test
    public void checkFullHouse() {
        Card card0 = new Card(13, "S");
        Card card1 = new Card(13, "H");
        Card card2 = new Card(13, "D");
        Card card3 = new Card(1, "C");
        Card card4 = new Card(1, "S");

        Hand hand = new Hand(card0, card1, card2, card3, card4);
        assertEquals(hand.checkHand(), 7);
    }

    @Test
    public void checkFlush() {
        Card card0 = new Card(13, "H");
        Card card1 = new Card(12, "H");
        Card card2 = new Card(11, "H");
        Card card3 = new Card(10, "H");
        Card card4 = new Card(8, "H");

        Hand hand = new Hand(card0, card1, card2, card3, card4);
        assertEquals(hand.checkHand(), 4);
    }

    @Test
    public void checkStraight() {
        Card card0 = new Card(13, "S");
        Card card1 = new Card(12, "H");
        Card card2 = new Card(11, "D");
        Card card3 = new Card(10, "C");
        Card card4 = new Card(9, "S");

        Hand hand = new Hand(card0, card1, card2, card3, card4);
        assertEquals(hand.checkHand(), 3);
    }

    @Test
    public void checkStraight2() {
        Card card0 = new Card(13, "S");
        Card card1 = new Card(12, "H");
        Card card2 = new Card(11, "D");
        Card card3 = new Card(1, "C");
        Card card4 = new Card(2, "S");

        Hand hand = new Hand(card0, card1, card2, card3, card4);
        assertNotEquals(hand.checkHand(), 3);
    }

    @Test
    public void checkStraight3() {
        Card card0 = new Card(13, "S");
        Card card1 = new Card(12, "H");
        Card card2 = new Card(11, "D");
        Card card3 = new Card(10, "C");
        Card card4 = new Card(1, "S");

        Hand hand = new Hand(card0, card1, card2, card3, card4);
        assertEquals(hand.checkHand(), 3);
    }

    @Test
    public void checkStraight4() {
        Card card0 = new Card(3, "S");
        Card card1 = new Card(4, "H");
        Card card2 = new Card(2, "D");
        Card card3 = new Card(5, "C");
        Card card4 = new Card(1, "S");

        Hand hand = new Hand(card0, card1, card2, card3, card4);
        assertEquals(hand.checkHand(), 3);
    }

    @Test
    public void checkThreeOfAKind() {
        Card card0 = new Card(13, "S");
        Card card1 = new Card(12, "H");
        Card card2 = new Card(12, "D");
        Card card3 = new Card(12, "C");
        Card card4 = new Card(9, "S");

        Hand hand = new Hand(card0, card1, card2, card3, card4);
        assertEquals(hand.checkHand(), 2);
    }

    @Test
    public void checkTwoPairs() {
        Card card0 = new Card(13, "S");
        Card card1 = new Card(12, "H");
        Card card2 = new Card(12, "D");
        Card card3 = new Card(1, "C");
        Card card4 = new Card(13, "D");

        Hand hand = new Hand(card0, card1, card2, card3, card4);
        assertEquals(hand.checkHand(), 2);
    }

    @Test
    public void checkHighPair() {
        Card card0 = new Card(10, "S");
        Card card1 = new Card(12, "H");
        Card card2 = new Card(11, "D");
        Card card3 = new Card(10, "C");
        Card card4 = new Card(9, "S");

        Hand hand = new Hand(card0, card1, card2, card3, card4);
        assertEquals(hand.checkHand(), 1);
    }

    @Test
    public void checkHighPair2() {
        Card card0 = new Card(13, "S");
        Card card1 = new Card(12, "H");
        Card card2 = new Card(11, "D");
        Card card3 = new Card(9, "C");
        Card card4 = new Card(9, "S");

        Hand hand = new Hand(card0, card1, card2, card3, card4);
        assertNotEquals(hand.checkHand(), 1);
    }

    @Test
    public void checkNoWin() {
        Card card0 = new Card(13, "S");
        Card card1 = new Card(12, "H");
        Card card2 = new Card(11, "D");
        Card card3 = new Card(10, "C");
        Card card4 = new Card(8, "S");

        Hand hand = new Hand(card0, card1, card2, card3, card4);
        assertEquals(hand.checkHand(), 0);
    }
}
