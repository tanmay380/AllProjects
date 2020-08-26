import javax.print.attribute.standard.PrinterMakeAndModel;
import java.util.Random;

public class game_match_3_p_2 {
    public static int[][] mainarray = new int[9][5];

    public static Random random = new Random();

    public static void moveupperelements(int count, int j, int i, int row, int column, char mode) {
        switch (mode) {
            case 'r':
                for (int i1 = i; i1 > 0; i1--) {
                    for (int j1 = j; j1 < count + j; j1++) {
                        mainarray[i1][j1] = mainarray[i1 - 1][j1];
                    }
                }
                for (int j1 = j; j1 < count; j1++)
                    mainarray[0][j1] = random.nextInt(6);
                checksamerow(row, column);
                checksamecolumn(row, column);
                break;

            case 'v':
                int samecount = count;
                for (int i1 = i - 1; i1 >= 0; i1--) {
                    mainarray[i + samecount - 1][j] = mainarray[i1][j];
                    samecount--;
                }
                int store = i + samecount - 1;
                for (int i1 = store; i1 >= 0; i1--) {
                    mainarray[i1][j] = random.nextInt(6);
                }
                checksamecolumn(row, column);
                checksamerow(row, column);
                break;
        }
    }

    private static void checksamerow(int row, int column) {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                int count = 0, k = j;
                do {
                    count++;
                    k++;
                    if (k == column) {
                        break;
                    }
                } while (mainarray[i][j] == mainarray[i][k]);
                if (count >= 3) {
                    moveupperelements(count, j, i, row, column, 'r');
                }
            }
        }
    }

    private static void checksamecolumn(int row, int column) throws ArrayIndexOutOfBoundsException {
        for (int i = 0; i < row - 1; i++) {
            for (int j = 0; j < column; j++) {
                int count = 0, k = i;
                do {
                    count++;
                    k++;
                    if (k == row) {
                        break;
                    }
                } while (mainarray[i][j] == mainarray[k][j]);
                if (count >= 3) {
                    moveupperelements(count, j, i, row, column, 'v');
                }
            }
        }
    }

    private static void fillarray(int[][] mainarray, int row, int column) {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                mainarray[i][j] = random.nextInt(6);
            }
        }
    }

    private static void printarray(int [][] array, int row, int column){
        System.out.print("   ");
        for (int j = 0; j < column; j++) {
            System.out.print(j+" ");
        }
        System.out.print("\n   ");
        for (int j = 0; j < column; j++) {
            System.out.print("__");
        }
        System.out.println();
        char letter='A';
        for (int i = 0; i < row; i++) {
            System.out.print(letter+++" | ");
            for (int j = 0; j < column; j++) {
                System.out.print(mainarray[i][j] + " ");
            }
            System.out.println();
        }
    }
    public static void main(String[] args) {
        int row = 9, column = 5;
        fillarray(mainarray, row, column);
        printarray(mainarray,row,column);
        checksamerow(row, column);
        checksamecolumn(row, column);
        System.out.println("Final Array ");
        printarray(mainarray,row,column);

//        for (int i = 0; i < row; i++) {
//            for (int j = 0; j < column; j++) {
//                System.out.print(mainarray[i][j] + " ");
//            }
//            System.out.println();
//        }
    }


}

