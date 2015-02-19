import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jack on 10/16/2014.
 */
public class Card {
    private int number;
    private String suit;
    private static int numberOfCards = 0;

    public Card(int number, String suit) {
        this.number = number;
        this.suit = suit;
        numberOfCards++;
    }

    public int getNumber() {
        return this.number;
    }
    public String getSuit() {
        return this.suit;
    }

    public static int getNumberOfCards() {
        return Card.numberOfCards;
    }

    public static List<Card> getDeck() {
        List<Card> deck = new ArrayList<Card>();
        String[] suits = {"Heart", "Diamond", "Spade", "Club"};
        for (int i = 1; i < 14; i++) {
            for (int x = 0; x < 4; x++) {
                deck.add(makeCard(i, suits[x]));
            }
        }
        return deck;
    }

    private static Card makeCard(int i, String suit){
        return new Card(i, suit);
    }

    public String toString() {
        return getNumber() + " " + getSuit();
    }

    public static void main(String[] args) {
        List<Card> myDeck = Card.getDeck();
        for (Card c :  myDeck) {
            System.out.println(c.toString());
        }
        System.out.println(Card.getNumberOfCards() + " cards have been made.");
    }

}
