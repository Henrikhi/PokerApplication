package poker.cards;

public class Card {

    private int value;
    private String suit;

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

}
