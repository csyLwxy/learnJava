package astart;

public class FangKuaiPosition {

    static public final int size = 50;
    private int x;
    private int y;
    private int F;
    private int G;
    private int H;
    private FangKuaiPosition previousFK;
    
    public FangKuaiPosition() {
    }

    public FangKuaiPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public FangKuaiPosition(int x, int y, FangKuaiPosition fk) {
        this.x = x;
        this.y = y;
        this.previousFK = fk;
    }

    public FangKuaiPosition(MyPanel myPpanel) {
        this.x = myPpanel.getX() / MyPanel.size;
        this.y = myPpanel.getY() / MyPanel.size;
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

    public FangKuaiPosition getPreviousFK() {
        return previousFK;
    }

    public void setPreviousFK(FangKuaiPosition previousFK) {
        this.previousFK = previousFK;
    }

    @Override
    public boolean equals(Object obj) {
        if (((FangKuaiPosition) obj).getX() == this.x && ((FangKuaiPosition) obj).getY() == this.y) {
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
