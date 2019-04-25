package poker.cards;

import java.util.ArrayList;
import java.util.Comparator;

public class Hand {

    private ArrayList<Card> hand;
    private DeckOfCards deck;
    private Card first;
    private Card second;
    private Card third;
    private Card fourth;
    private Card fifth;

    public Hand() {
        this.hand = new ArrayList<>();
        this.deck = new DeckOfCards();
    }

    public Hand(Card first, Card second, Card third, Card fourth, Card fifth) {
        this.hand = new ArrayList<>();
        this.hand.add(first);
        this.hand.add(second);
        this.hand.add(third);
        this.hand.add(fourth);
        this.hand.add(fifth);
        this.deck = new DeckOfCards();
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    /**
     * Method "deals" 5 random cards into the ArrayList "hand" from Deck-object.
     */
    public void deal5() {
        for (int i = 0; i < 5; i++) {
            this.hand.add(this.deck.dealRandomCard());
        }
        if (this.hand.size() != 5) {
            System.out.println("something is wrong with the size of the hand!");
        }
    }

    /**
     * Method empties the hand, which is ArrayList of Card-objects. All of the
     * cards in hand-list are put to deck-list.
     */
    public void emptyHand() {
        this.hand.forEach(card -> {
            this.deck.addToDeck(card);
        });
        this.hand.clear();
    }

    /**
     * Method replaces the old card in given index in hand with a new random
     * card, and shuffles the old card back into the deck.
     *
     * @param cardIndex the index of the card to be replaced.
     */
    public void replace(int cardIndex) {
        if (cardIndex >= 0 && cardIndex < 5) {
            Card oldCard = this.hand.get(cardIndex);
            Card newCard = this.deck.dealRandomCard();
            this.hand.remove(cardIndex);
            this.deck.addToDeck(oldCard);
            this.hand.add(cardIndex, newCard);
        } else {
            System.out.println("Card index out of bounds 1");
        }
    }

    /**
     * Method returns the card in the corresponding index in the hand.
     *
     * @param cardIndex Index of the card in hand
     * @return The card of the corresponding index. If the card does not exist,
     * returns null
     */
    public Card getCard(int cardIndex) {
        if (cardIndex >= 0 && cardIndex < 5) {
            return this.hand.get(cardIndex);
        } else {
            System.out.println("Card index out of bounds 2");
            return null;
        }
    }

    /**
     * Checks the value of the hand and return the correct multiplier of the
     * bet. For example, royal flush is worth 25 times the bet, and one pair is
     * worth 1 times the bet.
     *
     * @return the value of the hand in Integer. For example, royal flush is 25,
     * pair is 1. If the hand is worthless, returns 0.
     */
    public int checkHand() { //return the value of the hand. Returns an integer
        //winnings for the round = bet * returned integer.
        this.first = this.hand.get(0);
        this.second = this.hand.get(1);
        this.third = this.hand.get(2);
        this.fourth = this.hand.get(3);
        this.fifth = this.hand.get(4);

        int[] sameValues = new int[14];
        sameValues[first.getValue()]++;
        sameValues[second.getValue()]++;
        sameValues[third.getValue()]++;
        sameValues[fourth.getValue()]++;
        sameValues[fifth.getValue()]++;
        int pairsFound = 0;
        boolean threeOfAKind = false;
        boolean fourOfAKind = false;

        for (int i = 0; i < sameValues.length; i++) {
            if (sameValues[i] == 2) {
                pairsFound++;
            }
            if (sameValues[i] == 3) {
                threeOfAKind = true;
            }
            if (sameValues[i] == 4) {
                fourOfAKind = true;
            }
        }

        if (straightFlush()) {
            return 25;
        }

        if (fourOfAKind) {
            return 12;
        }

        if (threeOfAKind && pairsFound == 1) { //full house
            return 7;
        }

        if (flush()) {
            return 4;
        }

        if (straight()) {
            return 3;
        }

        if (threeOfAKind) {
            return 2;
        }

        if (pairsFound == 2) { //two pairs
            return 2;
        }

        if (pair()) { //only win if pair is high : 10, J, Q, K or A!
            return 1;
        }

        //no wins
        return 0;
    }

    /**
     * Checks if the hand is straigh flush. Calls for sub methods straight() and
     * flush().
     *
     * @return true, if straight() and flush() return true. Else false false
     */
    private boolean straightFlush() {
        return flush() && straight();
    }

    /**
     * Checks if hand is flush. In other words, checks if all cards are the same
     * suit.
     *
     * @return true, if all cards are the same suit. Else false
     */
    private boolean flush() {
        String firstSuit = this.first.getSuit();
        String secondSuit = this.second.getSuit();
        String thirdSuit = this.third.getSuit();
        String fourthSuit = this.fourth.getSuit();
        String fifthSuit = this.fifth.getSuit();

        if (firstSuit.equals(secondSuit)
                && secondSuit.equals(thirdSuit)
                && thirdSuit.equals(fourthSuit)
                && fourthSuit.equals(fifthSuit)
                && fifthSuit.equals(firstSuit)) {
            return true;
        }

        return false;
    }

    /**
     * Checks if the hand is straight: The five cards are in order. For example,
     * A, 2, 3, 4, 5 and 3,4,5,6,7 are straights. Also 10, J, Q, K, A is
     * straight, but K,A,2,3,4 is not. If A is in hand, it has to be the lowest
     * or the highest card.
     *
     * @return true, if hand is straight. Else false
     */
    private boolean straight() {
        ArrayList<Integer> values = new ArrayList<>();
        this.hand.forEach(card -> {
            values.add(card.getValue());
        });
        values.sort(Comparator.naturalOrder());
        if ((values.get(0) + 1) == values.get(1)
                && values.get(1) + 1 == values.get(2)
                && values.get(2) + 1 == values.get(3)
                && values.get(3) + 1 == values.get(4)) {
            return true;
        }
        if (values.get(0) == 1) { //if the lowest card is A, and the second is not 2,
            //the rest might be 10, J, Q and K.
            if (values.get(1) == 10
                    && values.get(2) == 11
                    && values.get(3) == 12
                    && values.get(4) == 13) {
                return true;
            }
        }
        return false;
    }

    /**
     * Method checks if hand has a high pair : pair of tens, jacks, queens,
     * kings or aces.
     *
     * @return true if hand has a high pair. Else false
     */
    private boolean pair() {
        int tens = 0;
        int jacks = 0;
        int queens = 0;
        int kings = 0;
        int aces = 0;

        for (int i = 0; i < this.hand.size(); i++) {
            int value = this.hand.get(i).getValue();
            if (value == 10) {
                tens++;
            } else if (value == 11) {
                jacks++;
            } else if (value == 12) {
                queens++;
            } else if (value == 13) {
                kings++;
            } else if (value == 1) {
                aces++;
            }
        }

        if (tens == 2 || jacks == 2 || queens == 2 || kings == 2 || aces == 2) {
            return true;
        }

        return false;
    }

}
