package astart;

public class DiamondPosition {

    static public final int size = 50;
    private int x;
    private int y;
    private int F;
    private int G;
    private int H;
    private DiamondPosition previousFK;

    public DiamondPosition() {
    }

    public DiamondPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public DiamondPosition(int x, int y, DiamondPosition fk) {
        this.x = x;
        this.y = y;
        this.previousFK = fk;
    }

    public DiamondPosition(MyPanel myPanel) {
        this.x = myPanel.getX() / MyPanel.size;
        this.y = myPanel.getY() / MyPanel.size;
    }

    public int getF() {
        return F;
    }

    public void setF(int f) {
        F = f;
    }

    public int getG() {
        return G;
    }

    public void setG(int g) {
        G = g;
    }

    public int getH() {
        return H;
    }

    public void setH(int h) {
        H = h;
    }

    public DiamondPosition getPreviousFK() {
        return previousFK;
    }

    public void setPreviousFK(DiamondPosition previousFK) {
        this.previousFK = previousFK;
    }

    @Override
    public boolean equals(Object obj) {
        if (((DiamondPosition) obj).getX() == this.x && ((DiamondPosition) obj).getY() == this.y) {
            return true;
        } else {
            return false;
        }
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
