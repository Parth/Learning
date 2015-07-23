import java.io.FileNotFoundException;

/**
 * Author: parthmehrotra
 */
public class ApplicationClass {
    public static void main(String args[]) throws FileNotFoundException {
        DeckOfCards deckOfCards = new DeckOfCards();
        System.out.println("Deck:\t\t\t\t"+deckOfCards);
        System.out.println();
        deckOfCards.deal();
    }
}
