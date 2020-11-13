package utils;

import java.util.ArrayList;

public class Map implements Cloneable{
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

    public Map(int dimension, int[][] conditions,Cell[][] table) {
        // creating the table and filling cells arguments are the main task of this constructor
        this.conditions = conditions;
        this.dimension = dimension;
        this.table = table;
    }

//    private void initCell(int x, int y) {
//        // we should fill cells neighbours in here...
//
//        Cell cell = this.table[x][y];
//
//        if (x > 0) {
//            cell.setLeft(this.table[x - 1][y]);
//        }
//        if (y > 0) {
//            cell.setUp(this.table[x][y - 1]);
//        }
//        if (x < this.dimension - 1) {
//            cell.setRight(this.table[x + 1][y]);
//        }
//        if (x < this.dimension - 1) {
//            cell.setDown(this.table[x][y + 1]);
//        }
//
//        this.table[x][y] = cell;
//    }

    public void print() {
        // this is a function in which we visualize the table in an user friendly way... :)
        System.out.println("***********************************************************************");
        System.out.println("-----main map-----");
        for (Cell[] rows : this.table) {
            for (Cell cell : rows) {
                System.out.print(cell);
            }
            System.out.println();
        }
        System.out.println("------cell domain------");
        for (Cell[] rows : this.table) {
            for (Cell cell : rows) {
                System.out.print(cell.getDomainLength());
            }
            System.out.println();
        }
        System.out.println("----- is Black (0:false ...) ----");
        for (Cell[] rows : this.table) {
            for (Cell cell : rows) {
                System.out.print(cell.isBlacked() ? "1":"0");
            }
            System.out.println();
        }

        System.out.println("----- is set (0:false ...) ----");
        for (Cell[] rows : this.table) {
            for (Cell cell : rows) {
                System.out.print(cell.isSet() ? "1":"0");
            }
            System.out.println();
        }
    }

    public Cell[][] getTable() {
        return table;
    }

    public Cell getCell(int x, int y){
        return this.table[x][y];
    }

    public void setTable(Cell[][] table) {
        this.table = table;
    }

    public int[] getRowCondition(int x) {
        return conditions[x].length > 0 ? conditions[x] : null;
    }

    public int[] getColCondition(int y) {
        return conditions[dimension + y].length > 0 ? conditions[dimension + y] : null;
    }

    public Cell[] getRow(int row){
        return table[row];
    }

    public int countOfColoredCellInRow(int row){
        int count =0;
        for (int i =0; i<this.dimension; i++ ){
            if(table[row][i].isBlacked()){
                count++;
            }
        }
        return count;
    }

    public int countOfCellShouldBeBlackInRow(int row){
        int[] conditions = getRowCondition(row);
        int sum =0;
        if (conditions==null){
            return sum;
        }
        for(int condition : conditions){
            sum+=condition;
        }
        return sum;
    }

    public int differencesPaintedAndRemainingCell(int row){
        return countOfCellShouldBeBlackInRow(row)-countOfColoredCellInRow( row);
    }

    public boolean isRowCompleted(int row){
        return differencesPaintedAndRemainingCell(row)==0;
    }

    public int countOfColoredCellInCol(int col){
        int count =0;
        for (int i =0; i<this.dimension; i++ ){
            if(table[i][col].isBlacked()){
                count++;
            }
        }
        return count;
    }

    public int countOfCellShouldBeBlackInCol(int col){
        int[] conditions = getColCondition(col);
        int sum =0;
        if(conditions==null){
            return sum;
        }
        for(int condition : conditions){
            sum+=condition;
        }
        return sum;
    }

    public int differencesPaintedAndRemainingCellInCol(int col){
        return countOfCellShouldBeBlackInCol(col)-countOfColoredCellInCol( col);
    }

    public boolean isColCompleted(int col){
        return differencesPaintedAndRemainingCellInCol(col)==0;
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
            if (!checkAcceptableLine(getColumn(this.table, i), conditions[i+dimension])) {
                return false;
            }
        }

        return true;
    }



    private boolean checkAcceptableLine(Cell[] line, int[] condition) {
        int[] consecutiveBlackCells = getContinuesBlackCells(line);
        if (condition.length != consecutiveBlackCells.length) {
            return false;
        }
        for (int i = 0; i < consecutiveBlackCells.length; i++) {
            if (consecutiveBlackCells[i] > condition[i]) {
                return false;
            }
        }
        return true;
    }

    private int[] getContinuesBlackCells(Cell[] line) {
        ArrayList<Integer> consecutiveBlackCells = new ArrayList<>();
        int n=0;
        for (int i=0 ;  i<line.length;i++){
            if(line[i].isBlacked()){
                n++;
                continue;
            }else{
                if (n > 0) {
                    consecutiveBlackCells.add(n);
                }
                n=0;
            }
        }
        if (n>0){
            consecutiveBlackCells.add(n);
        }
//        for (Cell cell : line) {
//            int n = 0;
//            while (cell.isBlacked()) {
//                n++;
//            }
//            if (n > 0) {
//                consecutiveBlackCells.add(n);
//            }
//            n=0;
//        }
        int[] integers = new int[consecutiveBlackCells.size()];
        for (int i = 0;i<integers.length;i++){
            integers[i]=consecutiveBlackCells.get(i);
        }
        return integers;
    }


    public Cell[] getColumn(Cell[][] array, int index) {
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
//        int count=0;
//        for (Cell[] rows : this.table) {
//            for (Cell cell : rows) {
//                if(cell.isBlacked()){
//                    count++;
//                }
//            }
//        }
//
//        for (int[] rows : conditions) {
//            for (int cell : rows) {
//                count-=cell;
//            }
//        }

        for (Cell[] rows : this.table) {
            for (Cell cell : rows) {
                if(!cell.isSet()){
                    return false;
                }
            }
        }


        for (int i=0 ; i<getDimension(); i++){
            if(differencesPaintedAndRemainingCell(i)!=0){
                return false;
            }
        }

        for (int i = 0 ; i<getDimension(); i++){
            if(differencesPaintedAndRemainingCellInCol(i)!=0){
                return false;
            }
        }

        return true;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Cell[][] cells = new Cell[dimension][dimension];
        for(int i =0 ; i<dimension;i++){
            for(int j =0 ; j<dimension;j++){
                cells[i][j]= new Cell(this.table[i][j].getX(),this.table[i][j].getY());
                cells[i][j].setCanBeWhite(this.table[i][j].canBeWhite());
                cells[i][j].setCanBeBlack(this.table[i][j].canBeBlack());
                cells[i][j].setBlacked(this.table[i][j].isBlacked());
                cells[i][j].setIsSet(this.table[i][j].isSet());
            }
        }

        int[][] rules = new int[dimension*2][];
        for (int i =0 ; i < dimension*2 ; i++){
            int n = this.conditions[i].length;
            rules[i]= new int[n];
            for (int j =0; j< n ; j++){
                 rules[i][j]= this.conditions[i][j];
            }
        }

        Map map = new Map(dimension,conditions,cells);
        return map;
    }

    public boolean isFinal() {
        return this.isAcceptable() && this.isComplete();
    }

    public int[][] getConditions() {
        return conditions;
    }

    public void setConditions(int[][] conditions) {
        this.conditions = conditions;
    }

    public int getDimension() {
        return dimension;
    }

    public void setDimension(int dimension) {
        this.dimension = dimension;
    }
}
