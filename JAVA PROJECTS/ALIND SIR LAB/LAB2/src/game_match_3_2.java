import javax.print.attribute.standard.PrinterMakeAndModel;
import java.util.Random;

public class game_match_3_2 {
    public static int[][] mainarray = {
            {11,11,101,11,11},
            {11,11,111,11,11},
            {11,11,181,11,11},
            {1211,11,171,11,11},
            {1211,11,161,11,11},//5 4
            {111,111,111,11,11},
            {1211,11,141,11,11},
            {111,131,131,11,11},
            {121,11,11,131,11}};

    public static Random random = new Random();

    public static void moveupperelements(int count, int j, int i, int row, int column, char mode) {
        switch (mode) {
            case 'r':
                for (int i1 = i; i1 > 0; i1--) {
                    for (int j1 = j; j1 < count + j; j1++) {
                        mainarray[i1][j1] = mainarray[i1 - 1][j1];
                    }
                }
                System.out.println("running row");
                for (int j1 = j; j1 < count; j1++)
                    mainarray[0][j1] = random.nextInt(6);
                break;

            case 'v':
                int samecount = count;
                for (int i1 = i - 1; i1 >= 0; i1--) {
                    mainarray[i + samecount - 1][j] = mainarray[i1][j];
                    samecount--;
                }
                System.out.println("running coloumn");
                int store = i + samecount - 1;
                for (int i1 = store; i1 >= 0; i1--) {
                    mainarray[i1][j] = random.nextInt(6);
                }
                break;
        }
    }


    public static void main(String[] args) {
        int row = 9, column = 5;
        checksamerow(row, column);
        checksamecolumn(row, column);


//        System.out.println("final showing ");
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                System.out.print(mainarray[i][j] + " ");
            }
            System.out.println();
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

        private static void checksamecolumn ( int row, int column) throws ArrayIndexOutOfBoundsException {
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
    }

