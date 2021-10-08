public class sudoku {
    public static void main(String[] args) {
        int[][] board = {
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0}};

        System.out.println(board.length);
        System.out.println(board[0].length);
        sudokusolver(board, 0, 0);

    }

    private static void sudokusolver(int[][] board, int i, int j) {

        if (i == board.length-1 && j == board[0].length) {

            System.out.println(i+" " + j);
            display(board);
            return;
        }

        if (j == board[0].length) {
            i += 1;
            j = 0;
        }

        if (board[i][j] != 0) {
            sudokusolver(board, i, j + 1);
        } else {
            for (int value = 1; value <= 9; value++) {
                if (isSafe(board, i, j, value)) {
                    board[i][j] = value;
                    sudokusolver(board, i, j + 1);
                    board[i][j] = 0;
                }
            }


        }


    }

    private static void display(int[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static boolean isSafe(int[][] board, int i, int j, int value) {
        if (board[i][j] != 0) return false;

        //entire ROW
        for (int k = 0; k < 9; k++) {
            if (board[k][j] == value)
                return false;
        }
        //entire COLUMN
        for (int k = 0; k < 9; k++) {
            if (board[i][k] == value)
                return false;
        }

        i = (i / 3) * 3;
        j = (j / 3) * 3;
        for (int k = 0; k < 3; k++) {
            for (int l = 0; l < 3; l++) {
                if (board[i + k][j + l] == value) return false;
            }
        }


        return true;
    }
}
