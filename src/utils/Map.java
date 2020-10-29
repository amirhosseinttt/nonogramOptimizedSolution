package utils;

import java.util.ArrayList;

public class Map {
    private Cell[][] table;
    private int[][] conditions;
    private int dimension;



    /*   y:  0  1  2  3  4  5  6  7  8  9
     *      -------------------------------
     * x:0  |  |  |  |  |  |  |  |  |  |  |
     *      -------------------------------
     * x:1  |  |  |  |  |  |  |  |  |  |  |
     *      -------------------------------
     * x:2  |  |  |  |  |  |  |  |  |  |  |
     *      -------------------------------
     * x:3  |  |  |  |  |  |  |  |  |  |  |
     *      -------------------------------
     * x:4  |  |  |  |  |  |  |  |  |  |  |
     *      -------------------------------
     *
     *
     * this is a regular visualization of (x,y) table
     * and of-course to prevent future misunderstandings ;)
     *
     * */


    public Map(int dimension, int[][] conditions) {
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
        for (Cell[] rows : this.table) {
            for (Cell cell : rows) {
                System.out.print(cell);
            }
            System.out.println();
        }
    }

    public int[] getRowCondition(int x) {
        return conditions[x].length > 0 ? conditions[x] : null;
    }

    public int[] getColCondition(int y) {
        return conditions[dimension + y].length > 0 ? conditions[dimension + y] : null;
    }

    public boolean isAcceptable() {
        // this method looks at the table and conditions and returns true if conditions are satisfied.
        // Notice (Important)---> difference with isComplete() is that this function returns true even for an all empty..
        // table because an empty table does not violate any condition

        //check rowCondition
        for (int i = 0; i < dimension; i++) {
            if (!checkAcceptableLine(this.table[i], conditions[i])) {
                return false;
            }
        }

        //check colCondition
        for (int i = 0; i < dimension; i++) {
            if (!checkAcceptableLine(getColumn(this.table, i), conditions[i])) {
                return false;
            }
        }

        return false;
    }

    private boolean checkAcceptableLine(Cell[] line, int[] condition) {
        Integer[] consecutiveBlackCells = getConsecutiveBlackCells(line);
        if (condition.length < consecutiveBlackCells.length) {
            return false;
        }
        for (int i = 0; i < consecutiveBlackCells.length; i++) {
            if (consecutiveBlackCells[i] > condition[i]) {
                return false;
            }
        }
        return true;
    }

    private Integer[] getConsecutiveBlackCells(Cell[] line) {
        ArrayList<Integer> consecutiveBlackCells = new ArrayList<>();
        for (Cell cell : line) {
            int n = 0;
            while (cell.isBlacked()) {
                n++;
            }
            if (n > 0) {
                consecutiveBlackCells.add(n);
            }
        }
        return (Integer[]) consecutiveBlackCells.toArray();
    }


    private Cell[] getColumn(Cell[][] array, int index) {
        Cell[] column = new Cell[dimension];
        for (int i = 0; i < column.length; i++) {
            column[i] = array[i][index];
        }
        return column;
    }


    public boolean isComplete() {
        // this method returns true if and only if the overal number of blacked cells are equal to overal sum of
        // all constrains(conditions).
        // alert
        int count=0;
        for (Cell[] rows : this.table) {
            for (Cell cell : rows) {
                if(cell.isBlacked()){
                    count++;
                }
            }
        }

        for (int[] rows : conditions) {
            for (int cell : rows) {
                count-=cell;
            }
        }

        return count==0;
    }

    public boolean isFinal() {
        return this.isAcceptable() && this.isComplete();
    }


}
