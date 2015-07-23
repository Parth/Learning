/**
 * Author: parthmehrotra
 */
public class Card {
    private String suit;
    private int value;

    public Card(String suit, int value) {
        this.suit = suit;
        this.value = value;
    }

    public String toString() {
        String name;
        switch (value) {
            case 1:
                name = "Ace";
                break;
            case 11:
                name = "Jack";
                break;
            case 12:
                name = "Queen";
                break;
            case 13:
                name = "King";
                break;
            default:
                name = "" + value;
        }
        return name + " of " + suit;
    }

    public int getValue() {
        return value;
    }

    public String getSuit() {
        return suit;
    }

    public static Card getCardFromNumber(int i) {
        int value;
        if (i % 13 != 0) {
            value = i % 13;
        } else {
            value = 13;
        }

        String suit;
        switch ((i - 1) / 13) {
            case 0:
                suit = "Clubs";
                break;
            case 1:
                suit = "Diamonds";
                break;
            case 2:
                suit = "Hearts";
                break;
            case 3:
                suit = "Spades";
                break;
            default:
                suit = "Wait, what?";
                break;
        }
        return new Card(suit, value);
    }
}
