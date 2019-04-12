package poker.cards;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class CardTest {

    Card card1;
    Card card2;

    @Before
    public void setUp() {
        card1 = new Card(1, "S");
        card2 = new Card(13, "H");
    }

    @Test
    public void cardValueIsCorrect1() {
        assertEquals(card1.getValue(), 1);
    }

    @Test
    public void cardSuitIsCorrectS() {
        assertEquals(card1.getSuit(), "S");
    }

    @Test
    public void cardValuesIsCorrect13() {
        assertEquals(card2.getValue(), 13);
    }

    @Test
    public void cardSuitIsCorrectH() {
        assertEquals(card2.getSuit(), "H");
    }

    @Test
    public void cardValuesIsIncorrect1() {
        Card card = new Card(-1, "S");
        String value = "" + card.getValue();
        assertEquals(value, "0");
    }

    @Test
    public void twoEqualCardsAreEqual() {
        Card card1 = new Card(1, "S");
        Card card2 = new Card(1, "S");
        assertTrue(card1.equals(card2));
    }

    @Test
    public void isBlackSevenWorks() {
        Card card1 = new Card(7, "S");
        Card card2 = new Card(7, "C");
        Card card3 = new Card(7, "H");
        Card card4 = new Card(8, "S");
        assertTrue(card1.isBlackSeven() && card2.isBlackSeven()
                && !card3.isBlackSeven() && !card4.isBlackSeven());
    }

    @Test
    public void isRedSevenWorks() {
        Card card1 = new Card(7, "H");
        Card card2 = new Card(7, "D");
        Card card3 = new Card(7, "S");
        Card card4 = new Card(8, "D");
        assertTrue(card1.isRedSeven() && card2.isRedSeven()
                && !card3.isRedSeven() && !card4.isRedSeven());
    }

    @Test
    public void isHighCardWorks() {
        Card card1 = new Card(8, "H");
        Card card2 = new Card(13, "H");
        Card card3 = new Card(1, "H");
        Card card4 = new Card(7, "H");
        Card card5 = new Card(5, "H");
        assertTrue(card1.isHighCard() && card2.isHighCard()
                && !card3.isHighCard() && !card4.isHighCard() && !card5.isHighCard());
    }
    
    @Test
    public void isLowCardWorks() {
        Card card1 = new Card(6, "H");
        Card card2 = new Card(1, "H");
        Card card3 = new Card(13, "H");
        Card card4 = new Card(7, "H");
        Card card5 = new Card(11, "H");
        assertTrue(card1.isLowCard() && card2.isLowCard()
                && !card3.isLowCard() && !card4.isLowCard() && !card5.isLowCard());
    }

}
