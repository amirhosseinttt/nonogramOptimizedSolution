package utils;

public class Cell {


    private boolean isBlacked;
    private int x;
    private int y;


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
}
