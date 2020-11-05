package solution;

import org.jetbrains.annotations.NotNull;
import utils.Cell;
import utils.Map;

public class Backtracking {

    public boolean solve(@NotNull Map map) {

        if (!map.isAcceptable()) return false;

        if (map.isComplete()) {
            map.print();
            return true;
        }


        Cell temp = MRV(map);
        int choice = LCV(temp);

        boolean answer = false;
        switch (choice) {
            case 1:
                temp.setIsSet(true);
                temp.setBlacked(true);
                return solve(map);
            case 2:
                temp.setIsSet(true);
                temp.setBlacked(false);
                return solve(map);
            case 31:
                temp.setIsSet(true);
                temp.setBlacked(true);
                answer = solve(map);
                if (answer) return true;
                temp.setIsSet(true);
                temp.setBlacked(false);
                return solve(map);
            case 32:
                temp.setIsSet(true);
                temp.setBlacked(false);
                answer = solve(map);
                if (answer) return true;
                temp.setIsSet(true);
                temp.setBlacked(true);
                return solve(map);

            default:
                return false;

        }

    }

    private Cell MRV(Map map) {

        return null;
    }

    private int LCV(Cell cell) {
        // returns 1 for black decision and 2 for white and 3 is for both...
        if (cell.canBeBlack() && !cell.canBeWhite()) return 1;
        else if (!cell.canBeBlack() && cell.canBeWhite()) return 2;
        else if (!cell.canBeWhite() && !cell.canBeBlack()) {
            System.err.println("something went wrong. it shouldn't be like this!");
            return -1;
        } else {
//            Cell left = null;
//            Cell top = null;
//            Cell temp = cell;
//            while (temp.getLeft() != null) {
//                temp = temp.getLeft();
//            }
//            left = temp;
//            temp = cell;
//            while (temp.getUp() != null) {
//                temp = temp.getUp();
//            }
//            top = temp;


            // white


        }


    }

    private void forwardChecking(Cell cell) {
        //later
    }

}
