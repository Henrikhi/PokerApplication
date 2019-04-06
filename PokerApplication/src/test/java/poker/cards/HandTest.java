
package poker.cards;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class HandTest {
    Hand hand;
    
    @Before
    public void setUp() {
        hand = new Hand();
    }
    
    @Test
    public void handIsEmpty() {
        assertTrue(hand.getHand().isEmpty());
    }
    
    
}
