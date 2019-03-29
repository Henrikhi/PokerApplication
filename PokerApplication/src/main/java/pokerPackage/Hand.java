package pokerPackage;

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

    public void printDeck() {
        this.deck.printDeck();
    }

    public Hand(Card first, Card second, Card third, Card fourth, Card fifth) {
        this.hand = new ArrayList<>();
        hand.add(first);
        hand.add(second);
        hand.add(third);
        hand.add(fourth);
        hand.add(fifth);
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        String message = new String(builder);
        for (int i = 0; i < this.hand.size(); i++) {
            System.out.println(this.hand.get(i).toString());
            builder.append(this.hand.get(i).toString());
        }

        return message;
    }

    public void deal5() {
        for (int i = 0; i < 5; i++) {
            this.hand.add(this.deck.dealRandomCard());
        }
    }

    public void emptyHand() {
        this.hand.forEach(card -> {
            this.deck.addToDeck(card);
        });
        this.hand.clear();
    }

    public void replace(int cardNumber) {
        Card oldCard = this.hand.get(cardNumber);
        this.hand.remove(cardNumber);
        Card newCard = this.deck.dealRandomCard();
        this.deck.addToDeck(oldCard);
        this.hand.add(cardNumber, newCard);
    }

    public Card getCard(int cardNumber) {
        return this.hand.get(cardNumber);
    }

    public void discard(Card card) {
        this.hand.remove(card);
        this.deck.addToDeck(card);
    }

    public int checkHand() { //return the value of the hand. Returns an integer
        //winnings for the round = bet * returned integer.
        this.first = this.hand.get(0);
        this.second = this.hand.get(1);
        this.third = this.hand.get(2);
        this.fourth = this.hand.get(3);
        this.fifth = this.hand.get(4);

        if (straightFlush()) {
            return 8;
        }

        if (fourOfAKind()) {
            return 7;
        }

        if (fullHouse()) {
            return 6;
        }

        if (flush()) {
            return 5;
        }

        if (straight()) {
            return 4;
        }

        if (threeOfAKind()) {
            return 3;
        }

        if (twoPairs()) {
            return 2;
        }

        if (pair()) { //only win if pair is high : 10, J, Q, K or A!
            return 1;
        }

        //no wins
        return 0;
    }

    private boolean straightFlush() {
        return flush() && straight();
    }

    private boolean fourOfAKind() {
        int[] sameValues = new int[14];
        sameValues[first.getValue()]++;
        sameValues[second.getValue()]++;
        sameValues[third.getValue()]++;
        sameValues[fourth.getValue()]++;
        sameValues[fifth.getValue()]++;
        int maxOfSameValues = 0;
        for (int i = 0; i < sameValues.length; i++) {
            maxOfSameValues = Math.max(maxOfSameValues, sameValues[i]);
        }
        return maxOfSameValues == 4;
    }

    private boolean fullHouse() {
        int[] sameValues = new int[14];
        sameValues[first.getValue()]++;
        sameValues[second.getValue()]++;
        sameValues[third.getValue()]++;
        sameValues[fourth.getValue()]++;
        sameValues[fifth.getValue()]++;
        boolean containsPair = false;
        boolean containsThree = false;
        for (int i = 0; i < sameValues.length; i++) {
            if (sameValues[i] == 2) {
                containsPair = true;
            }
            if (sameValues[i] == 3) {
                containsThree = true;
            }
        }
        return containsPair && containsThree;
    }

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

    private boolean straight() {
        //for examle: A, 2, 3, 4, 5 and 3, 4, 5, 6, 7 and 10, J, Q, K, A are straights.
        //however: J, Q, K, A, 2 is not.
        //If A is in hand, A has to be the first or the fifth card in a row.
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

    private boolean threeOfAKind() {
        int[] sameValues = new int[14];
        sameValues[first.getValue()]++;
        sameValues[second.getValue()]++;
        sameValues[third.getValue()]++;
        sameValues[fourth.getValue()]++;
        sameValues[fifth.getValue()]++;
        int maxOfSameValues = 0;
        for (int i = 0; i < sameValues.length; i++) {
            maxOfSameValues = Math.max(maxOfSameValues, sameValues[i]);
        }
        return maxOfSameValues == 3;
    }

    private boolean twoPairs() {
        int[] sameValues = new int[14];
        sameValues[first.getValue()]++;
        sameValues[second.getValue()]++;
        sameValues[third.getValue()]++;
        sameValues[fourth.getValue()]++;
        sameValues[fifth.getValue()]++;
        int pairsFound = 0;
        for (int i = 0; i < sameValues.length; i++) {
            if (sameValues[i] == 2) {
                pairsFound++;
            }
        }
        return pairsFound == 2;
    }

    private boolean pair() {
        //only win if the pair is high: 10, J, Q, K or A.
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
