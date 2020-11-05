package utils;

public class Cell {

    private boolean isSet=false;
    private boolean isBlacked;
    private int x;
    private int y;
    private boolean canBeBlack;
    private boolean canBeWhite;

    public boolean isSet() {
        return isSet;
    }

    public void setIsSet(boolean set) {
        isSet = set;
    }

    private Cell up;
    private Cell down;
    private Cell left;
    private Cell right;


    public Cell(int x, int y) {
        this.isBlacked = false;
        this.x = x;
        this.y = y;
    }


    public boolean isBlacked() {
        return isBlacked;
    }

    public void setBlacked(boolean blacked) {
        isBlacked = blacked;
    }

    public void setDown(Cell down) {
        this.down = down;
    }

    public void setLeft(Cell left) {
        this.left = left;
    }

    public void setRight(Cell right) {
        this.right = right;
    }

    public void setUp(Cell up) {
        this.up = up;
    }

    public Cell getDown() {
        return down;
    }

    public Cell getLeft() {
        return left;
    }

    public Cell getRight() {
        return right;
    }

    public Cell getUp() {
        return up;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean canBeBlack() {
        return canBeBlack;
    }

    public void setCanBeBlack(boolean canBeBlack) {
        this.canBeBlack = canBeBlack;
    }

    public boolean canBeWhite() {
        return canBeWhite;
    }

    public void setCanBeWhite(boolean canBeWhite) {
        this.canBeWhite = canBeWhite;
    }

    @Override
    public String toString() {
        return isBlacked ? "*":".";
    }
}
