import javax.print.attribute.standard.PrinterMakeAndModel;
import java.util.Random;
import java.util.Scanner;

public class game_match_3_p_2 {
    public static int[][] mainarray = new int[9][5];
    private static int score=0;
    public static Random random = new Random();
    public static Scanner scanner = new Scanner(System.in);

    private static void moveupperelements(int count, int j, int i, int row, int column, char mode) {
        switch (mode) {
            case 'r':
                for (int i1 = i; i1 > 0; i1--) {
                    for (int j1 = j; j1 < count + j; j1++) {
                        mainarray[i1][j1] = mainarray[i1 - 1][j1];
                    }
                }
                for (int j1 = j; j1 < count; j1++) {
                    mainarray[0][j1] = random.nextInt(6);
                }
                score+=count;
                checksamerow(row, column);
                checksamecolumn(row, column);
                break;

            case 'v':
                int samecount = count;
                score+=count;
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

    private static int checksamerow(int row, int column) {
        int run=0;
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
                    run++;
                }
            }
        }
    return run;
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

    private static void printarray(int[][] array, int row, int column) {
        System.out.print("   ");
        for (int j = 0; j < column; j++) {
            System.out.print(j + " ");
        }
        System.out.print("\n   ");
        for (int j = 0; j < column; j++) {
            System.out.print("__");
        }
        System.out.println();
        char letter = 'A';
        for (int i = 0; i < row; i++) {
            System.out.print(letter++ + " | ");
            for (int j = 0; j < column; j++) {
                System.out.print(mainarray[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int row = 9, column = 5;// if changing these values dont forget to update the storage of the array at the top
        fillarray(mainarray, row, column);
//        printarray(mainarray, row, column); shows the array created by random
        checksamerow(row, column);
        checksamecolumn(row, column);
        score=0;
        printarray(mainarray, row, column);
        char choice;
        do {
            usertask();
            checksamerow(row, column);
            checksamecolumn(row, column);
            System.out.println("Your Current score is ->"+score);
            printarray(mainarray, row, column);
            while (true) {
                System.out.print("Do You want to play more Y or N -> ");
                choice = scanner.next().toUpperCase().charAt(0);
                if (choice=='Y'|| choice=='N')break;
                else System.out.println("TRY AGAIN....");

            }

        }while (choice=='Y');
        System.out.print("Your final score is -> "+score);
    }

    private static void usertask() {
        boolean valid = true;
        while (valid) {
        System.out.print("Enter the index of the elemtent you want to swap (A1)-> ");
        String index1 = scanner.next().toLowerCase();
        int row1 = index1.charAt(0) % 97;
        int column1 = index1.charAt(1) % 48;
        System.out.print("Enter the index of the elemtent you want to swap with (A1)-> ");
        String index2 = scanner.next().toLowerCase();
        int row2 = index2.charAt(0) % 97;
        int column2 = index2.charAt(1) % 48;
            if ((row1 == row2 && column1 == column2 + 1) || (row1 == row2 && column1 == column2 - 1)
                    || (column1 == column2 && row1 == row2 - 1) || (column1 == column2 && row1 == row2 + 1)) {
                swaparray(mainarray,row1,row2,column1,column2);
                valid=false;
            } else {
                valid = true;
                System.out.println("INPUT VALUES ARE NOT ADJACENT... TRY AGAIN....");
            }
        }
    }
    public static void swaparray(int[][] mainarray,int row1,int row2, int column1,int column2){
        int temp=mainarray[row1][column1];
        mainarray[row1][column1]=mainarray[row2][column2];
        mainarray[row2][column2]=temp;
    }
}

