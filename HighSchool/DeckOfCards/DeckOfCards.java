import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Author: parthmehrotra
 */
public class DeckOfCards {
    private LinkedList<Card> deck;

    public DeckOfCards() {
        deck = new LinkedList<Card>();
        for (int i = 1; i <= 52; i++) {
            deck.add(Card.getCardFromNumber(i));
        }
    }

    public void deal() throws FileNotFoundException {
        LinkedList<Card> hands[] = (LinkedList<Card>[]) new LinkedList[4];

        hands[0] = new LinkedList<Card>();
        hands[1] = new LinkedList<Card>();
        hands[2] = new LinkedList<Card>();
        hands[3] = new LinkedList<Card>();

        Scanner scan = new Scanner(new File("Random.dat"));

        for (int i = 0; i < 13; i++) {
            for (int g = 0; g < 4; g++) {
                hands[g].add(deck.remove(Integer.parseInt(scan.nextLine()) - 1));
            }
        }

        System.out.println();
        for (int i = 0; i < 4; i++) {
            System.out.println("Hand for player "+(i+1)+":\t"+hands[i]);
        }
    }

    public String toString() {
        return deck.toString();
    }
}
