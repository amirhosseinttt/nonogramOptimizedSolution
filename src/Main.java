import solution.Backtracking;
import utils.Map;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public Map getMapFromFile(String filePath) {
        return null;
    }

    public static Map getMapFromTerminal() {
        Scanner scanner = new Scanner(System.in);
        int dimension =  scanner.nextInt();
        int[][] rules = new int[dimension*2][];
        for (int i =0 ; i < dimension*2 ; i++){
            int n = scanner.nextInt();
            rules[i]= new int[n];
            for (int j =0; j< n ; j++){
                rules[i][j]= scanner.nextInt();
            }
        }
        return new Map(dimension,rules);
    }

    public static void main(String[] args) {
        Map map = getMapFromTerminal();
        Backtracking backtracking = new Backtracking();
        try {
          System.out.println("start");
          boolean answer =  backtracking.solve(map);
          if (!answer){
              System.out.println("not found");
          }
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        System.out.println(Backtracking.a);
    }
}
