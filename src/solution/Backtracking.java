package solution;

import org.jetbrains.annotations.NotNull;

import utils.*;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Backtracking {

    static boolean isSolved = false;
    public static int a = 0;

    public boolean solve(@NotNull Map map) throws CloneNotSupportedException {
        a++;
        if (isSolved) {
            return false;
        }


        if (map.isComplete()) {
            if (map.isAcceptable()) {

                System.out.println("Founded!!");

                isSolved = true;
                return true;
            }
            return false;
        }

        Cell selectedCell = MRV(map);
        if (selectedCell == null) {
            return false;
        }


        int choice = LCV(selectedCell, (Map) map.clone());
        boolean answer;

        Map m = null;
        switch (choice) {

            case 1:
                selectedCell.setIsSet(true);
                selectedCell.setBlacked(true);
                m = forwardChecking(selectedCell, (Map) map.clone());
                if (m == null) {
                    return false;
                }
                return solve((Map) m.clone());
            case 2:
                selectedCell.setIsSet(true);
                selectedCell.setBlacked(false);
                m = forwardChecking(selectedCell, (Map) map.clone());
                if (m == null) {
                    return false;
                }
                return solve((Map) m.clone());
            case 312:
                selectedCell.setIsSet(true);
                selectedCell.setBlacked(true);
                m = forwardChecking(selectedCell, (Map) map.clone());
                if (m != null) {
                    answer = solve((Map) m);
                    if (answer) return true;
                }
                selectedCell.setIsSet(true);
                selectedCell.setBlacked(false);
                m = forwardChecking(selectedCell, (Map) map.clone());
                if (m == null) {
                    return false;
                }
                answer = solve((Map) m);
                if (answer) {
                    return true;
                } else {
                    selectedCell.setIsSet(false);
                    return false;
                }
            case 321:
                selectedCell.setIsSet(true);
                selectedCell.setBlacked(false);
                m = forwardChecking(selectedCell, (Map) map.clone());
                if (m != null) {
                    answer = solve((Map) m);
                    if (answer) return true;
                }
                selectedCell.setIsSet(true);
                selectedCell.setBlacked(true);
                m = forwardChecking(selectedCell, (Map) map.clone());
                if (m == null) {
                    return false;
                }
                answer = solve((Map) m);
                if (answer) {
                    return true;
                } else {
                    selectedCell.setIsSet(false);
                    return false;
                }
            default:
                return false;
        }

    }

    private Cell MRV(Map map) {


//        for (Cell[] row : map.getTable()) {
//            for (Cell cell : row) {
//                if (cell.getDomainLength() == 1 && !cell.isSet()) {
//                    return cell;
//                }
//            }
//        }

//        for (Cell[] row : map.getTable()) {
//            for (Cell cell : row) {
//                if (cell.getDomainLength() == 2 && !cell.isSet()) {
//                    return cell;
//                }
//            }
//        }
        for (Cell[] row : map.getTable()) {
            for (Cell cell : row) {
                if (cell.getDomainLength() == 1 && !cell.isSet()) {
                    return cell;
                }
            }
        }

        for (int conditionCount = map.getDimension(); conditionCount >= map.getDimension() / 2; conditionCount--) {
            for (int i = 0; i < map.getDimension(); i++) {
                if (map.differencesPaintedAndRemainingCell(i) == conditionCount) {
                    Cell[] row = map.getTable()[i];
                    for (int j = 0; j < row.length; j++) {
                        if (!row[j].isSet()) {
                            return row[j];
                        }
                    }
                }
            }
        }


        int bestRow = 0;
        while (bestRow < map.getDimension() && map.isSetAllCellInRow(bestRow)) {
            bestRow++;
        }
        if (bestRow >= map.getDimension()) {
            return null;
        }
        for (int i = 0; i < map.getDimension(); i++) {
            int diff = map.differencesPaintedAndRemainingCell(i);
            if (diff < map.differencesPaintedAndRemainingCell(bestRow) && diff > 0) {
                bestRow = i;
            }
        }

        Cell[] selectedRow = map.getTable()[bestRow];
        int bestCell = map.getDimension() + 1;
        for (int i = 0; i < map.getDimension(); i++) {
            int diff = map.differencesPaintedAndRemainingCellInCol(i);
            if (!selectedRow[i].isSet() && diff < bestCell && diff != 0) {
                bestCell = i;
            }
        }
        if (bestCell < map.getDimension()) {
            return selectedRow[bestCell];
        }


        for (Cell[] row : map.getTable()) {
            for (Cell cell : row) {
                if (cell.getDomainLength() == 2 && !cell.isSet()) {
                    return cell;
                }
            }
        }

        return null;
        // for (int i = 0; i<)

//        for (int conditionCount = map.getDimension(); conditionCount >= map.getDimension() / 2; conditionCount--) {
//            for (int i = 0; i < map.getDimension(); i++) {
//                if (map.differencesPaintedAndRemainingCell(i) == conditionCount && !map.isRowCompleted(i)) {
//                    Cell[] row = map.getTable()[i];
//                    for (int j = 0; j < row.length; j++) {
//                        if (!row[j].isSet()) {
//                            return row[j];
//                        }
//                    }
//                }
//            }
//        }
//
//
//        int bestRow = 0;
////        while (bestRow < map.getDimension() && map.differencesPaintedAndRemainingCell(bestRow) == 0 && !map.isRowCompleted(bestRow)) {
////            bestRow++;
////        }
////        if(bestRow>=map.getDimension()){
////            return null;
////        }
//        for (int i = 0; i < map.getDimension(); i++) {
//            int diff = map.differencesPaintedAndRemainingCell(i);
//            if (diff < map.differencesPaintedAndRemainingCell(bestRow) && diff > 0) {
//                bestRow = i;
//            }
//        }
//
//        Cell[] selectedRow = map.getTable()[bestRow];
//        int bestCell = map.getDimension() + 1;
//        for (int i = 0; i < map.getDimension(); i++) {
//            int diff = map.differencesPaintedAndRemainingCellInCol(i);
//            if (!selectedRow[i].isSet() && diff < bestCell && diff != 0) {
//                bestCell = i;
//            }
//        }
//        if (bestCell < map.getDimension()) {
//            return selectedRow[bestCell];
//        }
//
//        return null;
    }

    private int LCV(Cell cell, Map map) {
        // returns 1 for black decision and 2 for white and 3 is for both...
//        if (!isSolved){
//            return 1;
//        }

        if (cell.canBeBlack() && !cell.canBeWhite()) {
            return 1;
        } else if (!cell.canBeBlack() && cell.canBeWhite()) {
            return 2;
        } else if (!cell.canBeWhite() && !cell.canBeBlack()) {
            System.err.println("something went wrong. it shouldn't be like this!");
            return -1;
        } else {
            Map map1 = null, map2 = null;
            try {
                map1 = (Map) map.clone(); // for black
                map2 = (Map) map.clone(); // for white
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }

            assert map1 != null;
            Cell temp = map1.getCell(cell.getX(), cell.getY());
            temp.setIsSet(true);
            temp.setBlacked(true);
            map1 = LCVDomainResolver(temp, map1);

            int domainCount1 = 0;

            Cell[][] table = map1.getTable();

            for (int i = 0; i < table.length; i++) {
                Cell tmp1 = table[temp.getX()][i];
                if (!tmp1.isSet()) {
                    if (tmp1.canBeBlack()) domainCount1++;
                    if (tmp1.canBeWhite()) domainCount1++;
                }
            }

            for (int i = 0; i < table.length; i++) {
                Cell tmp1 = table[i][temp.getY()];
                if (!tmp1.isSet()) {
                    if (tmp1.canBeBlack()) domainCount1++;
                    if (tmp1.canBeWhite()) domainCount1++;
                }
            }


            assert map2 != null;
            temp = map2.getCell(cell.getX(), cell.getY());
            temp.setIsSet(true);
            temp.setBlacked(true);
            map2 = LCVDomainResolver(temp, map2);

            int domainCount2 = 0;

            table = map2.getTable();

            for (int i = 0; i < table.length; i++) {
                Cell tmp2 = table[temp.getX()][i];
                if (!tmp2.isSet()) {
                    if (tmp2.canBeBlack()) domainCount1++;
                    if (tmp2.canBeWhite()) domainCount1++;
                }
            }
            for (int i = 0; i < table.length; i++) {
                Cell tmp2 = table[i][temp.getY()];
                if (!tmp2.isSet()) {
                    if (tmp2.canBeBlack()) domainCount1++;
                    if (tmp2.canBeWhite()) domainCount1++;
                }
            }


            if (domainCount1 < domainCount2) return 321;
            else return 312;


        }
    }

    private Map LCVDomainResolver(Cell cell, Map map) {
//        System.out.println("forward started");
//        map.print();
        b++;
        int[] rowConditions = map.getRowCondition(cell.getX());
        int[] colConditions = map.getColCondition(cell.getY());
        ArrayList<Integer> rowConArray = new ArrayList<Integer>();
        if (rowConditions != null) {
            for (int con : rowConditions) {
                rowConArray.add(con);
            }
        }

        ArrayList<Integer> colConArray = new ArrayList<Integer>();
        if (colConditions != null) {
            for (int con : colConditions) {
                colConArray.add(con);
            }
        }

        Cell[][] table = map.getTable();
        Cell[] column = map.getColumn(table, cell.getY()); //table[cell.getX()];
        Cell[] row = map.getRow(cell.getX());

        Cell[] normRow = null, normCol = null;
        try {


            normRow = normalizeForRecursiveFunction(utils.copyOfCellArray(row));

            normCol = normalizeForRecursiveFunction(utils.copyOfCellArray(column));
        } catch (Exception e) {
            map.print();
            e.printStackTrace();
        }


        for (Cell tmpCell : row) {
            if (!tmpCell.isSet()) {
                if (tmpCell.canBeBlack() && tmpCell.canBeWhite()) {
                    tmpCell.setCanBeWhite(false);
                    tmpCell.setCanBeBlack(false);
                }
            }
        }
        for (Cell tmpCell : column) {
            if (!tmpCell.isSet()) {
                if (tmpCell.canBeBlack() && tmpCell.canBeWhite()) {
                    tmpCell.setCanBeWhite(false);
                    tmpCell.setCanBeBlack(false);
                }
            }
        }

//        System.out.println("before recursive");
//        map.print();


        recursiveFunction(rowConArray, normRow, 0, row, map.countOfCellShouldBeBlackInRow(cell.getX()));
        recursiveFunction(colConArray, normCol, 0, column, map.countOfCellShouldBeBlackInCol(cell.getY()));


//        System.out.println("returned map------------->>>>>>>>>");
//        map.print();

        return map;
    }


    public static int b = 0;

    private Map forwardChecking(Cell cell, Map map) {
//        System.out.println("forward started");
//        map.print();
        b++;
        int[] rowConditions = map.getRowCondition(cell.getX());
        int[] colConditions = map.getColCondition(cell.getY());
        ArrayList<Integer> rowConArray = new ArrayList<Integer>();
        if (rowConditions != null) {
            for (int con : rowConditions) {
                rowConArray.add(con);
            }
        }

        ArrayList<Integer> colConArray = new ArrayList<Integer>();
        if (colConditions != null) {
            for (int con : colConditions) {
                colConArray.add(con);
            }
        }

        Cell[][] table = map.getTable();
        Cell[] column = map.getColumn(table, cell.getY()); //table[cell.getX()];
        Cell[] row = map.getRow(cell.getX());

        Cell[] normRow = null, normCol = null;
        try {


            normRow = normalizeForRecursiveFunction(utils.copyOfCellArray(row));

            normCol = normalizeForRecursiveFunction(utils.copyOfCellArray(column));
        } catch (Exception e) {
            map.print();
            e.printStackTrace();
        }


        for (Cell tmpCell : row) {
            if (!tmpCell.isSet()) {
                if (tmpCell.canBeBlack() && tmpCell.canBeWhite()) {
                    tmpCell.setCanBeWhite(false);
                    tmpCell.setCanBeBlack(false);
                }
            }
        }
        for (Cell tmpCell : column) {
            if (!tmpCell.isSet()) {
                if (tmpCell.canBeBlack() && tmpCell.canBeWhite()) {
                    tmpCell.setCanBeWhite(false);
                    tmpCell.setCanBeBlack(false);
                }
            }
        }

//        System.out.println("before recursive");
//        map.print();


        if (!recursiveFunction(rowConArray, normRow, 0, row, map.countOfCellShouldBeBlackInRow(cell.getX())) ||
                !recursiveFunction(colConArray, normCol, 0, column, map.countOfCellShouldBeBlackInCol(cell.getY()))) {

            return null;

        }
//        System.out.println("returned map------------->>>>>>>>>");
//        map.print();

        return map;
    }


    private Cell[] normalizeForRecursiveFunction(Cell[] cells) throws Exception {
        for (Cell tmpCell : cells) {
            if (!tmpCell.isSet()) {

                if (tmpCell.canBeBlack() && tmpCell.canBeWhite()) {
                    tmpCell.setCanBeWhite(false);
                    tmpCell.setCanBeBlack(false);
                } else if (tmpCell.canBeBlack()) {
                    tmpCell.setIsSet(true);
                    tmpCell.setBlacked(true);
                } else if (tmpCell.canBeWhite()) {
                    tmpCell.setIsSet(true);
                    tmpCell.setBlacked(false);
                } else throw new Exception("this is wrong! it shouldn't be like this...");
            }
        }
        return cells;
    }

    private boolean recursiveFunction(ArrayList<Integer> remainingConditions, Cell[] row, int start, Cell[] originalRow, int blackSupposedCount) {
        // remember to make all 2 choice domains none before calling this function.


        if (remainingConditions.isEmpty()) {
            int count = 0;
            for (Cell cell : row) {
                if (cell.isSet() && cell.isBlacked()) count++;
            }

            assert row.length == originalRow.length;
            if (count == blackSupposedCount) {
                for (int i = 0; i < row.length; i++) {
                    if (row[i].isBlacked() && row[i].isSet()) {
                        originalRow[i].setCanBeBlack(true);
                    } else {
                        originalRow[i].setCanBeWhite(true);
                    }
                }
                return true;
            } else {
                return false;
            }
        }


        int thisCondition = remainingConditions.remove(0);

        int count = 0;

        for (int adad : remainingConditions) {
            count += adad + 1;
        }
        count += thisCondition;


        boolean sw = false;

        Task:
        for (int i = start; i <= row.length - count; i++) {
            Cell[] temp = utils.copyOfCellArray(row);
//            for (int k = start; k < i; k++) {
//                temp[k].setIsSet(true);
//                temp[k].setBlacked(false);
//            }
            if (i > start && row[i - 1].isSet() && row[i - 1].isBlacked()) break;
            else {

                for (int j = i; j < i + thisCondition; j++) {

                    if (temp[j].isSet() && !temp[j].isBlacked()) {
                        continue Task;
                    }

                    temp[j].setIsSet(true);
                    temp[j].setBlacked(true);
                }
                if (recursiveFunction(utils.copyOfIntArrayList(remainingConditions), utils.copyOfCellArray(temp), i + thisCondition + 1, originalRow, blackSupposedCount)) {
                    sw = true;
                }
            }


        }

        return sw;
    }

}
