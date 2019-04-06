
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
    
    
}
