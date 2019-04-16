package poker.logic;

import java.util.ArrayList;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import poker.cards.Card;
import poker.cards.Hand;

public class GameLogics {

    public boolean firstDealDone;
    public double winnings;
    public double bet;
    public boolean[] lockedCards;
    public Button[] cardButtons;
    Hand hand;
    public double latestWin;
    public Card doublingCard;

    public GameLogics() {
        this.firstDealDone = false;
        this.winnings = 000;
        this.bet = 20;
        this.lockedCards = new boolean[5];
        cardButtons = new Button[5];
        this.hand = new Hand();
        this.latestWin = 0;
        this.doublingCard = new Card();
    }

    public void playNewRound() {
        hand.emptyHand();
        hand.deal5();
        ArrayList<Card> handData = hand.getHand();
        for (int i = 0; i < 5; i++) {
            Card card = handData.get(i);
            cardButtons[i].setText(card.toString());
            cardButtons[i].setTextFill(setColor(card));
        }
        firstDealDone = true;
        //now the 5 cards are visible and the player can choose
        //which cards to lock.

    }

    public double playContinueRound() {
        firstDealDone = false;
        for (int i = 0; i < 5; i++) {
            if (!lockedCards[i]) {
                hand.replace(i);
                Card newCard = hand.getCard(i);
                cardButtons[i].setText(newCard.toString());
                cardButtons[i].setTextFill(setColor(newCard));
            }
            lockedCards[i] = false;
            unlockCard(i);
        }

        this.latestWin = hand.checkHand() * this.bet;
        return latestWin;
    }

    public void cardClicked(int i) {
        if (lockedCards[i] == false && firstDealDone) {
            lockCard(i);
        } else {
            unlockCard(i);
        }
    }

    public void lockCard(int i) {
        lockedCards[i] = true;
        this.cardButtons[i].setScaleX(0.8);
        this.cardButtons[i].setScaleY(0.8);
    }

    public void unlockCard(int i) {
        lockedCards[i] = false;
        this.cardButtons[i].setScaleX(1);
        this.cardButtons[i].setScaleY(1);
    }

    public void changeBet() {
        if (bet == 20) {
            bet = 40;
        } else if (bet == 40) {
            bet = 60;
        } else if (bet == 60) {
            bet = 80;
        } else if (bet == 80) {
            bet = 100;
        } else {
            bet = 20;
        }
    }

    public Paint setColor(Card card) {
        if (card.getSuit().equals("S") || card.getSuit().equals("C")) {
            return Color.BLACK;
        } else {
            return Color.RED;
        }
    }

    public void insertCoinClicked() {
        this.winnings += 200;
    }

    public void newRound() {
        this.winnings -= this.bet;
    }

    public void addWinnings() {
        this.winnings += this.latestWin;
        this.latestWin = 0;
    }

    public void newDoublingCard() {
        Card old = this.doublingCard;
        Card newCard = new Card();
        while (old.equals(newCard)) {   //I want that the next card is not
            newCard = new Card();       //same as the previous card, because
        }                               //this makes tests so much easier
        this.doublingCard = newCard;
    }

    public int highClicked() {
        Card card = this.doublingCard;
        if (card.isBlackSeven()) {
            this.latestWin = 0;
            return 0;
        }
        if (card.isRedSeven()) {
            return 1;
        }
        if (card.isHighCard()) {
            this.latestWin *= 2;
            return 2;
        }
        this.latestWin = 0;
        return 0;
    }

    public int lowClicked() {
        Card card = this.doublingCard;
        if (card.isBlackSeven()) {
            this.latestWin = 0;
            return 0;
        }
        if (card.isRedSeven()) {
            return 1;
        }
        if (card.isLowCard()) {
            this.latestWin *= 2;
            return 2;
        }
        this.latestWin = 0;
        return 0;
    }

}
