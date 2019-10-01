import java.util.ArrayList;
import java.util.Random;

public class Deck{
    private ArrayList<Card> cards;
    Random random;

    public Deck(){
        this.cards = new ArrayList<Card>();
        this.random = new Random();

        this.generateDeck();
    }

    private void generateDeck(){
        String[] suit = {"Heart", "Spade", "Diamond", "Club"};
        for (int i = 1; i <= 13; ++i){
            for (int j = 0; j < 4; ++j){
                cards.add(new Card(i, suit[j]));
            }
        }
    }

    public Card getRandomCard(){
        int index  = this.random.nextInt(this.cards.size());
        return this.cards.remove(index);
    }

    public CardGroup createRandomCardGroup(){
        return new CardGroup(this.getRandomCard(), this.getRandomCard(), this.getRandomCard());
    }

    public static void main(String[] args) {
        Deck deck = new Deck();
        CardGroup cg1 = deck.createRandomCardGroup();
        CardGroup cg2 = deck.createRandomCardGroup();
        CardGroup cg3 = deck.createRandomCardGroup();

        if (cg1.compare(cg2) >= 0 && cg1.compare(cg3) >= 0){
            System.out.println("cg1: " + cg1);
        }
        if (cg2.compare(cg1) >= 0 && cg2.compare(cg3) >= 0){
            System.out.println("cg2: " + cg2);
        }
        if (cg3.compare(cg1) >= 0 && cg3.compare(cg2) >= 0){
            System.out.println("cg3: " + cg3);
        }
    }
}