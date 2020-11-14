package utils;

import java.util.ArrayList;

public class utils {
    public static Cell[] copyOfCellArray(Cell[] line) {
        Cell[] cells = new Cell[line.length];
        for (int i = 0; i < line.length; i++) {
            cells[i] = new Cell(line[i].getX(), line[i].getY());
            cells[i].setCanBeWhite(line[i].canBeWhite());
            cells[i].setCanBeBlack(line[i].canBeBlack());
            cells[i].setBlacked(line[i].isBlacked());
            cells[i].setIsSet(line[i].isSet());
        }
        return cells;
    }

    public static ArrayList<Integer> copyOfIntArrayList(ArrayList<Integer> array){
        ArrayList<Integer> copy = new ArrayList<>();
        for (int i =0;i<array.size();i++){
            int temp =array.get(i);
            copy.add(temp);
        }
        return copy;
    }


}

