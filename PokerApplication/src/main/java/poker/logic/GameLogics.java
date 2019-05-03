package poker.logic;

import java.util.ArrayList;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import poker.cards.Card;
import poker.cards.Hand;
import poker.database.Database;
import poker.database.User;

public class GameLogics {

    public boolean firstDealDone;
    public double bet;
    public boolean[] lockedCards;
    public Button[] cardButtons;
    Hand hand;
    public double latestWin;
    public Card doublingCard;
    public Database database;
    public User player;

    public GameLogics() {
        this.firstDealDone = false;
        this.bet = 20;
        this.lockedCards = new boolean[5];
        cardButtons = new Button[5];
        this.hand = new Hand();
        this.latestWin = 0;
        this.doublingCard = new Card();
        this.player = new User();

        try {
            this.database = new Database("jdbc:sqlite:database.db");
        } catch (Exception ex) {
            System.out.println("Error");
        }
        database.init();

    }

    /**
     * Method for logics of a new round. This means that hand is emptied and new
     * hand is dealt, and every Button of Card in ui has correct graphics.
     */
    public void playNewRound() {
        hand.emptyHand();
        hand.deal5();
        ArrayList<Card> handData = hand.getHand();
        for (int i = 0; i < 5; i++) {
            Card card = handData.get(i);
            cardButtons[i].setText(card.toString());
            cardButtons[i].setTextFill(getColor(card));
        }
        firstDealDone = true;
        //now the 5 cards are visible and the player can choose
        //which cards to lock.
    }

    /**
     * Method for logics of a second round of play. Replaces all of the unlocked
     * cards and checks what the hand is worth. Returns the winnings of the
     * round, which is the value of the hand times the bet.
     *
     * @return winnings in Double format.
     */
    public double playContinueRound() {
        firstDealDone = false;
        for (int i = 0; i < 5; i++) {
            if (!lockedCards[i]) {
                hand.replace(i);
                Card newCard = hand.getCard(i);
                cardButtons[i].setText(newCard.toString());
                cardButtons[i].setTextFill(getColor(newCard));
            }
            lockedCards[i] = false;
            unlockCard(i);
        }

        this.latestWin = hand.checkHand() * this.bet;
        return latestWin;
    }

    /**
     * Method for logics behind clicked cards. If first deal has been done,
     * every card can be clicked to lock or unlock the card. If the click is
     * allowed, this method calls for submethods lockCard[i] or unlockCard[i] to
     * change the graphics of the card corresponding to if the card is locked or
     * unlocked.
     *
     * @param i the index of the card clicked.
     */
    public void cardClicked(int i) {
        if (lockedCards[i] == false && firstDealDone) {
            lockCard(i);
        } else {
            unlockCard(i);
        }
    }

    /**
     * Method locks the card in given index. This means that the graphics of the
     * card are set to 0.8 times of its normal size.
     *
     * @param i the index of the card
     */
    public void lockCard(int i) {
        lockedCards[i] = true;
        this.cardButtons[i].setScaleX(0.8);
        this.cardButtons[i].setScaleY(0.8);
    }

    /**
     * Method unlocks the card in given index, restoring the size of the card
     * back to normal.
     *
     * @param i the index of the card
     */
    public void unlockCard(int i) {
        lockedCards[i] = false;
        this.cardButtons[i].setScaleX(1);
        this.cardButtons[i].setScaleY(1);
    }

    /**
     * Method for changing the bet.
     */
    public void changeBet() {
        if (bet == 20) {
            bet = 40;
        } else if (bet == 40) {
            bet = 60;
        } else if (bet == 60) {
            bet = 80;
        } else if (bet == 80) {
            bet = 100;
        } else if (bet == 100) {
            bet = 200;
        } else {
            bet = 20;
        }
    }

    /**
     * Method for checking and returning the correct color of the given card.
     *
     * @param card Given card
     * @return color.BLACK or color.RED, depending on the suit of the card.
     */
    public Paint getColor(Card card) {
        if (card.getSuit().equals("S") || card.getSuit().equals("C")) {
            return Color.BLACK;
        } else {
            return Color.RED;
        }
    }

    /**
     * Method for logics behind insertCoin-button in ui. If the button is
     * clicked, the winnings and moneyInserted -values of the active User are
     * raised by 200 (which means 2€).
     */
    public void insertCoinClicked() {
        this.player.setWinnings(this.player.getWinnings() + 200);
        this.player.setMoneyInserted(this.player.getMoneyInserted() + 200);
    }

    /**
     * When a new round starts, the winnings of the player are lowered by the
     * value of the bet.
     */
    public void newRound() {
        this.player.setWinnings(this.player.getWinnings() - this.bet);
    }

    /**
     * This method adds the current latestWin to the player's winnings, and
     * updates the latestWin to 0.
     */
    public void addWinnings() {
        this.player.setWinnings(this.player.getWinnings() + this.latestWin);
        this.latestWin = 0;
    }

    /**
     * Creates a random card which will be the next card when doubling.
     */
    public void newDoublingCard() {
        Card old = this.doublingCard;
        Card newCard = new Card();
        while (old.equals(newCard)) {   //I want that the next card is not
            newCard = new Card();       //same as the previous card, because
        }                               //this makes tests so much easier
        this.doublingCard = newCard;
    }

    /**
     * Method checks if doubling was successful or not.
     *
     * @param lowWasClicked true if "low" was clicked. False if "high" was
     * clicked.
     * @return
     */
    public int doublingCardClicked(boolean lowWasClicked) {
        Card card = this.doublingCard;
        if (card.isBlackSeven()) {
            this.latestWin = 0;
            return -1;
        }
        if (card.isRedSeven()) {
            return 1;
        }
        if (lowWasClicked) {
            if (card.isLowCard()) {
                this.latestWin *= 2;
                return 2;
            }
        } else {
            if (card.isHighCard()) {
                this.latestWin *= 2;
                return 2;
            }
        }
        return 0;
    }

    /**
     * Method checks if given user exists in the database. This is done in the
     * Class Database, so this method calls for the userExists(User user) method
     * in Class Database.
     *
     * @param user Given user
     * @return true if user exists in database, else false
     */
    public boolean userExists(User user) {
        return this.database.userExists(user);
    }

    /**
     * Method creates a new user in the database. This is done in Class
     * Database, so this method calls for the createUser(User user) method in
     * Class Database.
     *
     * @param user Given user
     */
    public void createUser(User user) {
        this.database.createUser(user);
    }

    /**
     * Method checks if the username and password are correct.
     *
     * @param user given user
     * @return true if username and password are correct, else false
     */
    public boolean logInOK(User user) {
        this.player = this.database.logIn(user);
        if (this.player == null) {
            this.player = new User();
            return false;
        }
        this.bet = 20;
        for (int i = 0; i < 5; i++) {
            cardButtons[i].setText("");
            cardButtons[i].setTextFill(Color.BLACK);
        }
        return true;
    }

    /**
     * Method logs out the user: updates the user data in database with
     * updatePlayer() and changes this.player into new User().
     */
    public void logOutPlayer() {
        updatePlayer();
        this.player = new User();
    }

    /**
     * Method updates the user data in the database with
     * database.updateUser(this.player).
     */
    public void updatePlayer() {
        this.database.updateUser(this.player);
    }

}
