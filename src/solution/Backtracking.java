package solution;

import org.jetbrains.annotations.NotNull;

import utils.Cell;
import utils.Map;


import java.util.ArrayList;

public class Backtracking {

    static boolean isSolved = false;
    public static int a = 0;

    public boolean solve(@NotNull Map map) throws CloneNotSupportedException {
        System.out.println("-------------");

        a++;
        if (isSolved) {
            return false;
        }


        if (map.isComplete()) {
            if (map.isAcceptable()) {
                System.out.println("Founded!!");
                map.print();
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
//               selectedCell.setIsSet(true);
//               selectedCell.setBlacked(true);
//               solve((Map)map.clone());
//               selectedCell.setIsSet(true);
//               selectedCell.setBlacked(false);
//               return solve((Map)map.clone());
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
                map.print();
                m = forwardChecking(selectedCell, (Map) map.clone());
                m.print();
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

        for (Cell[] row : map.getTable()) {
            for (Cell cell : row) {
                if (cell.getDomainLength() == 1 && !cell.isSet()) {
                    return cell;
                }
            }
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
            System.out.println("just black");
            return 1;
        } else if (!cell.canBeBlack() && cell.canBeWhite()) {
            System.out.println("just white");
            return 2;
        } else if (!cell.canBeWhite() && !cell.canBeBlack()) {
            System.err.println("something went wrong. it shouldn't be like this!");
            return -1;
        } else {
            return 312;
//            Map map1 = null, map2 = null;
//            try {
//                map1 = (Map) map.clone(); // for black
//                map2 = (Map) map.clone(); // for white
//            } catch (CloneNotSupportedException e) {
//                e.printStackTrace();
//            }
//
//            assert map1 != null;
//            Cell temp = map1.getCell(cell.getX(), cell.getY());
//            temp.setIsSet(true);
//            temp.setBlacked(true);
//            map1 = forwardChecking(temp, map1);
//
//            int domainCount1 = 0;
//
//            Cell[][] table = map1.getTable();
//
//            for (int i = 0; i < table.length; i++) {
//                Cell tmp1 = table[temp.getX()][i];
//                if (!tmp1.isSet()) {
//                    if (tmp1.canBeBlack()) domainCount1++;
//                    if (tmp1.canBeWhite()) domainCount1++;
//                }
//            }
//
//            for (int i = 0; i < table.length; i++) {
//                Cell tmp1 = table[i][temp.getY()];
//                if (!tmp1.isSet()) {
//                    if (tmp1.canBeBlack()) domainCount1++;
//                    if (tmp1.canBeWhite()) domainCount1++;
//                }
//            }
//
//
//            assert map2 != null;
//            temp = map2.getCell(cell.getX(), cell.getY());
//            temp.setIsSet(true);
//            temp.setBlacked(true);
//            map2 = forwardChecking(temp, map2);
//
//            int domainCount2 = 0;
//
//            table = map2.getTable();
//
//            for (int i = 0; i < table.length; i++) {
//                Cell tmp2 = table[temp.getX()][i];
//                if (!tmp2.isSet()) {
//                    if (tmp2.canBeBlack()) domainCount1++;
//                    if (tmp2.canBeWhite()) domainCount1++;
//                }
//            }
//            for (int i = 0; i < table.length; i++) {
//                Cell tmp2 = table[i][temp.getY()];
//                if (!tmp2.isSet()) {
//                    if (tmp2.canBeBlack()) domainCount1++;
//                    if (tmp2.canBeWhite()) domainCount1++;
//                }
//            }
//
//
//            if (domainCount1 < domainCount2) return 321;
//            else return 312;


        }
    }

    public static int b = 0;

    private Map forwardChecking(Cell cell, Map map) {
        map.print();
        b++;
        int[] rowConditions = map.getRowCondition(cell.getX());
        ArrayList<Integer> rowConArray = new ArrayList<Integer>();
        if (rowConditions != null) {
            for (int con : rowConditions) {
                rowConArray.add(con);
            }
        }

        int[] colConditions = map.getColCondition(cell.getY());
        ArrayList<Integer> colConArray = new ArrayList<Integer>();
        if (colConditions != null) {
            for (int con : colConditions) {
                colConArray.add(con);
            }

        }

        Cell[][] table = map.getTable();
        Cell[] column = map.getColumn(table, cell.getY()); //table[cell.getX()];
        Cell[] row = map.getRow(cell.getX());
//        for (int i = 0; i < column.length; i++) {
//            row[i] = table[i][cell.getY()];
//        }

//        for (Cell tmpCell : column) {
//            if (!tmpCell.isSet() && tmpCell.canBeBlack() && tmpCell.canBeWhite()) {
//                tmpCell.setCanBeWhite(false);
//                tmpCell.setCanBeBlack(false);
//            }
//        }


        Cell[] normRow = null, normCol = null;
        try {

            normRow = normalizeForRecursiveFunction(row.clone());

            normCol = normalizeForRecursiveFunction(column.clone());

        } catch (Exception e) {
            System.out.println(b);
            map.print();
            e.printStackTrace();
        }


//        int rowBlackSupposedCount = 0;
//        int colBlackSupposedCount = 0;
//
//
//        for (int tmp : rowConditions) {
//            rowBlackSupposedCount += tmp;
//        }
//        for (int tmp : colConditions) {
//            colBlackSupposedCount += tmp;
//        }


        if (!recursiveFunction(rowConArray, normRow, 0, row, map.countOfCellShouldBeBlackInRow(cell.getX())) ||
                !recursiveFunction(colConArray, normCol, 0, column, map.countOfCellShouldBeBlackInCol(cell.getY())))
        {
            for (int i=0;i<10;i++){
                System.out.println("nuuuuuuuuuuuuuuuuul");
            }
            return null;

        }
        System.out.println("returned map------------->>>>>>>>>");
        map.print();
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
                    } else if (!row[i].isBlacked()) {
                        originalRow[i].setCanBeWhite(true);
                    }
                }
                return true;
            } else {
                System.out.println(count + " c");
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
        for (int i = start; i < row.length - count; i++) {
            Cell[] temp = row.clone();
            if (i != start && row[i].isSet() && row[i].isBlacked()) {
                for (int j = i; j < i + thisCondition; j++) {

                    if (temp[j].isSet() && !temp[j].isBlacked()) {
                        continue Task;
                    }

                    temp[j].setIsSet(true);
                    temp[j].setBlacked(true);
                }
                if (recursiveFunction(remainingConditions, temp, i + thisCondition + 1, originalRow, blackSupposedCount)) {
                    sw = true;
                }
            }


        }

        return sw;
    }

}
