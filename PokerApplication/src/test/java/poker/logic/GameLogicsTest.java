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
        assertEquals(logic.getColor(card), Color.BLACK);
    }

    @Test
    public void setColorBlack2() {
        Card card = new Card(11, "C");
        assertEquals(logic.getColor(card), Color.BLACK);
    }

    @Test
    public void setColorRed1() {
        Card card = new Card(13, "H");
        assertEquals(logic.getColor(card), Color.RED);
    }

    @Test
    public void setColorRed2() {
        Card card = new Card(5, "D");
        assertEquals(logic.getColor(card), Color.RED);
    }

    @Test
    public void insertCoinWorks() {
        double winningsFirst = logic.player.getWinnings();
        logic.insertCoinClicked();
        assertTrue(logic.player.getWinnings() - winningsFirst == 200);
    }

    @Test
    public void addWinningsAddsWinnings() {
        logic.player.setWinnings(100);
        logic.latestWin = 100;
        logic.addWinnings();
        assertTrue(logic.player.getWinnings() == 200);
    }

    @Test
    public void addWinningsResetsLatestwin() {
        logic.player.setWinnings(100);
        logic.latestWin = 100;
        logic.addWinnings();
        assertTrue(logic.latestWin == 0);
    }

    @Test
    public void newRoundWorks() {
        logic.player.setWinnings(100);
        logic.bet = 100;
        logic.newRound();
        assertTrue(logic.player.getWinnings() == 0);
    }

    @Test
    public void newRoundWorks2() {
        logic.player.setWinnings(160);
        logic.bet = 40;
        logic.newRound();
        assertTrue(logic.player.getWinnings() == 120);
    }

    @Test
    public void newDoublingCardWorks() {
        Card oldCard = new Card(1, "S");
        logic.doublingCard = oldCard;
        logic.newDoublingCard();
        assertFalse(oldCard.equals(logic.doublingCard));
    }

    @Test
    public void highClickedWorks0() {
        logic.doublingCard = new Card(7, "S");
        assertTrue(logic.highClicked() == 0);
    }

    @Test
    public void highClickedWorks1() {
        logic.doublingCard = new Card(1, "H");
        assertTrue(logic.highClicked() == 0);
    }

    @Test
    public void highClickedWorks2() {
        logic.doublingCard = new Card(7, "H");
        assertTrue(logic.highClicked() == 1);
    }

    @Test
    public void highClickedWorks3() {
        logic.doublingCard = new Card(8, "S");
        assertTrue(logic.highClicked() == 2);
    }

    @Test
    public void highClickedWorks4() {
        logic.doublingCard = new Card(13, "D");
        assertTrue(logic.highClicked() == 2);
    }
    
    @Test
    public void highClickedWorks5() {
        logic.doublingCard = new Card(6, "D");
        assertTrue(logic.highClicked() == 0);
    }

    @Test
    public void lowClickedWorks0() {
        logic.doublingCard = new Card(1, "C");
        assertTrue(logic.lowClicked() == 2);
    }

    @Test
    public void lowClickedWorks1() {
        logic.doublingCard = new Card(6, "C");
        assertTrue(logic.lowClicked() == 2);
    }
    
    @Test
    public void lowClickedWorks2() {
        logic.doublingCard = new Card(7, "C");
        assertTrue(logic.lowClicked() == 0);
    }
    
    @Test
    public void lowClickedWorks3() {
        logic.doublingCard = new Card(7, "D");
        assertTrue(logic.lowClicked() == 1);
    }
    
    @Test
    public void lowClickedWorks4() {
        logic.doublingCard = new Card(10, "D");
        assertTrue(logic.lowClicked() == 0);
    }
    
    @Test
    public void lowClickedWorks5() {
        logic.doublingCard = new Card(13, "C");
        assertTrue(logic.lowClicked() == 0);
    }





}
