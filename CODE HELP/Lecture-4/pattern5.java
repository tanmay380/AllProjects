public class pattern5 {
    public static void main(String[] args) {
        // patter5();
        // pattern6();
        // finalpat();
        dabang();
    }

    private static void patter5() {
        int n = 5;
        int row = 1;
        while (row <= n) {
            int col = 1;
            while (col <= (row)) {
                System.out.print(row + col - 1 + " ");
                col++;
            }
            row++;
            System.out.println();
        }
    }

    private static void pattern6() {
        int row = 5;
        int i = 1;
        while (i <= row) {
            int col = i;
            while (col > 0) {
                System.out.print(col-- + " ");
            }
            i++;
            System.out.println();
        }
    }

    private static void finalpat() {
        int row = 4;
        int i = 1;
        while (i <= row) {

            int space = row - i;
            while (space != 0) {
                System.out.print(" ");
                space--;
            }

            int col = 1;
            while (col <= i) {
                System.out.print(col);
                col++;
            }
            i++;
            System.out.println();
        }
    }

    private static void dabang() {
        int row = 5;
        int i = 1;

        while (i <= row) {
            int col = 1;
            while (col <= (row-i+1)) {
                System.out.print(col + " ");
                col++;
            }
            col=row-i+1;
            while (col!=row) {
                System.out.print(" * ");
                col++;
            }
            col=row-i+1;
            while (col!=row) {
                System.out.print(" * ");
                col++;
            }
            col=row-i+1;
            while (col >= 1) {
                System.out.print(col + " ");
                col--;
            }
            
            System.out.println();
            i++;
        }
    }
}
