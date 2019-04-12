package poker.logic;

import javafx.scene.paint.Color;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import poker.cards.Card;

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

    @Test
    public void setColorBlack1() {
        Card card = new Card(1, "S");
        assertEquals(logic.setColor(card), Color.BLACK);
    }

    @Test
    public void setColorBlack2() {
        Card card = new Card(11, "C");
        assertEquals(logic.setColor(card), Color.BLACK);
    }

    @Test
    public void setColorRed1() {
        Card card = new Card(13, "H");
        assertEquals(logic.setColor(card), Color.RED);
    }

    @Test
    public void setColorRed2() {
        Card card = new Card(5, "D");
        assertEquals(logic.setColor(card), Color.RED);
    }

    @Test
    public void insertCoinWorks() {
        double winningsFirst = logic.winnings;
        logic.insertCoinClicked();
        assertTrue(logic.winnings - winningsFirst == 200);
    }

    @Test
    public void addWinningsAddsWinnings() {
        logic.winnings = 100;
        logic.latestWin = 100;
        logic.addWinnings();
        assertTrue(logic.winnings == 200);
    }

    @Test
    public void addWinningsResetsLatestwin() {
        logic.winnings = 100;
        logic.latestWin = 100;
        logic.addWinnings();
        assertTrue(logic.latestWin == 0);
    }

    @Test
    public void newRoundWorks() {
        logic.winnings = 100;
        logic.bet = 100;
        logic.newRound();
        assertTrue(logic.winnings == 0);
    }

    @Test
    public void newRoundWorks2() {
        logic.winnings = 160;
        logic.bet = 40;
        logic.newRound();
        assertTrue(logic.winnings == 120);
    }

}
