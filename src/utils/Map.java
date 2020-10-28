package utils;
import java.util.ArrayList;

public class Map {
    private Cell[][] table;
    private ArrayList<Integer>[] conditions;
    private int dimension;




    /*   x:  0  1  2  3  4  5  6  7  8  9
     *      -------------------------------
     * y:0  |  |  |  |  |  |  |  |  |  |  |
     *      -------------------------------
     * y:1  |  |  |  |  |  |  |  |  |  |  |
     *      -------------------------------
     * y:2  |  |  |  |  |  |  |  |  |  |  |
     *      -------------------------------
     * y:3  |  |  |  |  |  |  |  |  |  |  |
     *      -------------------------------
     * y:4  |  |  |  |  |  |  |  |  |  |  |
     *      -------------------------------
     *
     *
     * this is a regular visualization of (x,y) table
     * and of-course to prevent future misunderstandings ;)
     *
     * */


    public Map(int dimension, ArrayList<Integer>[] conditions) {
        // creating the table and filling cells arguments are the main task of this constructor
        this.conditions = conditions;
        this.dimension = dimension;
        this.table = new Cell[dimension][dimension];
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                this.table[i][j] = new Cell(i, j);
            }
        }

    }

    private void initCell(int x, int y) {
        // we should fill cells neighbours in here...

        Cell cell = this.table[x][y];

        if (x > 0) {
            cell.setLeft(this.table[x - 1][y]);
        }
        if (y > 0) {
            cell.setUp(this.table[x][y - 1]);
        }
        if (x < this.dimension - 1) {
            cell.setRight(this.table[x + 1][y]);
        }
        if (x < this.dimension - 1) {
            cell.setDown(this.table[x][y + 1]);
        }

        this.table[x][y] = cell;
    }

    public void print() {
        // this is a function in which we visualize the table in an user friendly way... :)

    }

    public ArrayList<Integer> getCondition(int x, int y) {
        // one of entries is always -1 and the other one is something between 0 and the dimension variable

        return null;
    }

    public boolean isAcceptable(){
        // this method looks at the table and conditions and returns true if conditions are satisfied.
        // Notice (Important)---> difference with isComplete() is that this function returns true even for an all empty..
        // table because an empty table does not violate any condition

        return false;
    }

    public boolean isComplete(){
        // this method returns true if and only if the overal number of blacked cells are equal to overal sum of
        // all constrains(conditions).

        return false;
    }

    public boolean isFinal(){
        return this.isAcceptable() && this.isComplete();
    }


}
