package poker.cards;

import java.util.Random;

public class Card {

    private int value;
    private String suit;
    private Random random = new Random();

    /**
     * Creates a Card object with corresponding value and suit.
     *
     * @param value the value of the card, from 1 to 13 as in A to K
     * @param suit the suit as in club = "C", spade = "S", heart = "H", diamond
     * = "D"
     */
    public Card(int value, String suit) {
        if ((value >= 1 && value <= 13)
                && (suit.equals("S") || suit.equals("C")
                || suit.equals("H") || suit.equals("D"))) {
            this.value = value;
            this.suit = suit;
        } else {
            this.value = 0;
            this.suit = "null";
        }
    }

    /**
     * Creates a Card object with random value and suit.
     */
    public Card() {
        this.value = 1 + random.nextInt(13);
        String randomsuits = "HSCD";
        int number = random.nextInt(4);
        String chosenSuit = randomsuits.substring(number, number + 1);
        this.suit = chosenSuit;
    }

    @Override
    public String toString() {
        return getValueString() + " " + getSuitString();
    }

    public int getValue() {
        return value;
    }

    public String getSuit() {
        return suit;
    }

    /**
     * Method turns the value of the card into corresponding string. For
     * example, 4 -> "4", and 1 -> "A", 11 -> "J" etc.
     *
     * @return the value of the cards from 2-10 in String, but 1 -> "A", 11 ->
     * "J" etc.
     */
    public String getValueString() {
        if (this.value == 1) {
            return "A";
        } else if (this.value == 11) {
            return "J";
        } else if (this.value == 12) {
            return "Q";
        } else if (this.value == 13) {
            return "K";
        } else {
            return "" + this.value;
        }
    }

    /**
     * Method turns suits into unicode characters for graphich suits.
     *
     * @return the unicode of the suit.
     */
    public Character getSuitString() {
        if (this.suit.equals("C")) {
            return '\u2663';
        } else if (this.suit.equals("S")) {
            return '\u2660';
        } else if (this.suit.equals("H")) {
            return '\u2665';
        } else {
            return '\u2666';
        }
    }

    public boolean equals(Card another) {
        return this.value == another.value && this.suit.equals(another.suit);
    }

    /**
     * Method checks if card is a black seven, because black sevens have special
     * properties in doubling.
     *
     * @return true if card is a black seven. Black ofcourse means spade or
     * clubs.
     */
    public boolean isBlackSeven() {
        if (this.value == 7) {
            if (this.suit.equals("S") || this.suit.equals("C")) {
                return true;
            }
        }
        return false;
    }

    /**
     * Method checks if card is a red seven, because red sevens have special
     * properties in doubling.
     *
     * @return true if card is a red seven. Red ofcourse means heards or
     * diamonds.
     */
    public boolean isRedSeven() {
        if (this.value == 7) {
            if (this.suit.equals("H") || this.suit.equals("D")) {
                return true;
            }
        }
        return false;
    }

    /**
     * Method checks if card is considered a high card, which is 8-K.
     *
     * @return true if card is high card, else false.
     */
    public boolean isHighCard() {
        return this.value > 7;
    }

    /**
     * Method checks if card is considered a low card, which is A-6.
     *
     * @return true if card is low card, else false
     */
    public boolean isLowCard() {
        return this.value < 7;
    }
}
