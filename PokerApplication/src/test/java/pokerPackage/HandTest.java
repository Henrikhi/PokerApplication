
package pokerPackage;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
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
