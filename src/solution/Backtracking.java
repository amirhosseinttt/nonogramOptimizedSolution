package solution;

import org.jetbrains.annotations.NotNull;
import utils.Cell;
import utils.Map;

public class Backtracking {

    public boolean solve(@NotNull Map map) {

//        if (map.isComplete()) {
//            if (map.isAcceptable()){
//                map.print();
//                return true;
//            }
//            return false;
//        }
//
//        Cell selectedCell = MRV(map);
//        int choice = LCV(selectedCell);
//        boolean isSolve = solve(map);
//        if(!isSolve){
//            selectedCell.setIsSet(true);
//                selectedCell.setBlacked(true);
//                answer = solve(map);
//                if (answer) return true;
//                selectedCell.setIsSet(true);
//                selectedCell.setBlacked(false);
//                return solve(map);
//        }


//
//        boolean answer = false;
//        switch (choice) {
//            case 1:
//                temp.setIsSet(true);
//                temp.setBlacked(true);
//                return solve(map);
//            case 2:
//                temp.setIsSet(true);
//                temp.setBlacked(false);
//                return solve(map);
//            case 31:
//                temp.setIsSet(true);
//                temp.setBlacked(true);
//                answer = solve(map);
//                if (answer) return true;
//                temp.setIsSet(true);
//                temp.setBlacked(false);
//                return solve(map);
//            case 32:
//                temp.setIsSet(true);
//                temp.setBlacked(false);
//                answer = solve(map);
//                if (answer) return true;
//                temp.setIsSet(true);
//                temp.setBlacked(true);
//                return solve(map);
//
//            default:
//                return false;
//
//        }

    }

    private Cell MRV(Map map) {
        for (int conditionCount = map.getDimension(); conditionCount >= map.getDimension() / 2; conditionCount--) {
            for (int i = 0; i < map.getDimension(); i++) {
                if (map.differencesPaintedAndRemainingCell(i) == conditionCount && !map.isRowCompleted(i)) {
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
        ;
        while (map.differencesPaintedAndRemainingCell(bestRow) == 0 && bestRow < map.getDimension()) {
            bestRow++;
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

        return null;
    }

    private int LCV(Cell cell, Map map) {
        // returns 1 for black decision and 2 for white and 3 is for both...
        if (cell.canBeBlack() && !cell.canBeWhite()) return 1;
        else if (!cell.canBeBlack() && cell.canBeWhite()) return 2;
        else if (!cell.canBeWhite() && !cell.canBeBlack()) {
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
            map1 = forwardChecking(temp, map1);

            int domainCount1 = 0;

            Cell[][] table = map1.getTable();

            for (int i = 0; i < table.length; i++) {
                Cell tmp1 = table[temp.getX()][i];
                if (!tmp1.isSet()) {
                    if (tmp1.canBeBlack()) domainCount1++;
                    if (tmp1.canBeWhite()) domainCount1++;
                }
            }


            assert map2 != null;
            temp = map2.getCell(cell.getX(), cell.getY());
            temp.setIsSet(true);
            temp.setBlacked(true);
            map2 = forwardChecking(temp, map2);

            int domainCount2 = 0;

            table = map2.getTable();

            for (int i = 0; i < table.length; i++) {
                Cell tmp2 = table[temp.getX()][i];
                if (!tmp2.isSet()) {
                    if (tmp2.canBeBlack()) domainCount1++;
                    if (tmp2.canBeWhite()) domainCount1++;
                }
            }


            if (domainCount1 < domainCount2) return 321;
            else return 312;


        }
    }

    private Map forwardChecking(Cell cell, Map map) {


        return map;
    }

}
