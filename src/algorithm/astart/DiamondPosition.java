package algorithm.astart;

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

    DiamondPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    DiamondPosition(int x, int y, DiamondPosition fk) {
        this.x = x;
        this.y = y;
        this.previousFK = fk;
    }

    DiamondPosition(MyPanel myPanel) {
        this.x = myPanel.getX() / MyPanel.SIZE;
        this.y = myPanel.getY() / MyPanel.SIZE;
    }

    public int getF() {
        return F;
    }

    void setF(int f) {
        F = f;
    }

    int getG() {
        return G;
    }

    void setG(int g) {
        G = g;
    }

    int getH() {
        return H;
    }

    void setH(int h) {
        H = h;
    }

    DiamondPosition getPreviousFK() {
        return previousFK;
    }

    void setPreviousFK(DiamondPosition previousFK) {
        this.previousFK = previousFK;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof DiamondPosition)) {
            return false;
        }
        return ((DiamondPosition) obj).getX() == this.x && ((DiamondPosition) obj).getY() == this.y;
    }

    int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
