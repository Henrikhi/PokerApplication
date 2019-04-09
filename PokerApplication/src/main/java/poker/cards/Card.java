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
        return value + " " + suit;
    }

    public int getValue() {
        return value;
    }

    public String getSuit() {
        return suit;
    }

    public boolean equals(Card another) {
        return this.value == another.value && this.suit.equals(another.suit);
    }

}
