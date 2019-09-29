public class CardGroup{
    private Card c1;
    private Card c2;
    private Card c3;

    public CardGroup(Card c1, Card c2, Card c3){
        this.c1 = c1;
        this.c2 = c2;
        this.c3 = c3;
    }

    /**
     * @return the c1
     */
    public Card getC1() {
        return c1;
    }
    
    /**
     * @return the c2
     */
    public Card getC2() {
        return c2;
    }

    /**
     * @return the c3
     */
    public Card getC3() {
        return c3;
    }

    public int numOfSameNumber(){
        int res = 1;
        if(c1.getNumber() == c2.getNumber()){
            ++res;
        }else if(c1.getNumber() == c3.getNumber()){
            ++res;
        }
        if(c2.getNumber() == c3.getNumber()){
            ++res;
        }
        return res;
    }

    public boolean ofSameSuit(){
        return c1.getSuit() == c2.getSuit() && c1.getSuit() == c3.getSuit();
    }

    public int compare(CardGroup c){
        if (this.ofSameSuit() && !c.ofSameSuit()) {
            return 1;
        } else if (this.ofSameSuit() && c.ofSameSuit()) {
            return 0;
        } else if (!this.ofSameSuit() && c.ofSameSuit()) {
            return -1;
        }

        return this.numOfSameNumber() - c.numOfSameNumber();
    }
}