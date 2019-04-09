package poker.logic;

import java.util.ArrayList;
import javafx.scene.control.Button;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class GameLogicsTest {

    GameLogics logic;

    @Before
    public void setUp() {
        this.logic = new GameLogics();
    }

    @Test
    public void handIsEmptyInTheBeginning() {
        assertTrue(logic.hand.getHand().isEmpty());
    }

    @Test
    public void betIsCorrectInTheBeginning() {
        assertTrue(this.logic.bet == 20);
    }

    @Test
    public void changeBetWorks0() {
        logic.changeBet();
        assertTrue(logic.bet == 40);
    }

    @Test
    public void changeBetWorks1() {
        logic.changeBet();
        logic.changeBet();
        assertTrue(logic.bet == 60);
    }

    @Test
    public void changeBetWorks2() {
        logic.changeBet();
        logic.changeBet();
        logic.changeBet();
        assertTrue(logic.bet == 80);
    }

    @Test
    public void changeBetWorks3() {
        logic.changeBet();
        logic.changeBet();
        logic.changeBet();
        logic.changeBet();
        assertTrue(logic.bet == 100);
    }

    @Test
    public void changeBetWorks4() {
        logic.changeBet();
        logic.changeBet();
        logic.changeBet();
        logic.changeBet();
        logic.changeBet();
        assertTrue(logic.bet == 20);
    }

    @Test
    public void firstDealDoneIsFalseInTheBeginning() {
        assertFalse(logic.firstDealDone);
    }

}
