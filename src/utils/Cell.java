package utils;

public class Cell {

    private boolean isSet=false;
    private boolean isBlacked;
    private int x;
    private int y;
    private boolean canBeBlack = true;
    private boolean canBeWhite = true;


    public void setIsSet(boolean set) {
        isSet = set;
    }

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

    public int getDomainLength(){
        if(canBeBlack && canBeWhite){
            return 2;
        }else if(canBeWhite || canBeBlack){
            return 1;
        }
        return 0;
    }

    public boolean isSet() {
        return isSet;
    }

    public void color(){
        if(canBeBlack){
            isBlacked=true;
        }
        isSet=true;
    }

    public void setSet(boolean isSet) {
        this.isSet = isSet;
    }

    @Override
    public String toString() {
        if (!isSet()){
            return "?";
        }
        return isBlacked ? "*":".";
    }
}
