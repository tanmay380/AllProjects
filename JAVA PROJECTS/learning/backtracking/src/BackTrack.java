import java.util.Arrays;

public class BackTrack {
    final int N = 5;

    public static void main(String[] args) {
        int[][] baord = new int[5][5];
        for (int[] row : baord)
            Arrays.fill(row, 0);

        BackTrack b = new BackTrack();
        if (b.NQueenProblem(baord, 0)){
            System.out.println("Esist");
        }else System.out.println("NOT EXIST");
        b.printArray(baord);

    }

    private boolean NQueenProblem(int[][] baord, int c) {
        if (c >= N) return true;

        for (int i = 0; i < N; i++) {
            if (isSafe(baord, i, c)) {
                baord[i][c] = 1;
                if (NQueenProblem(baord, c + 1)) {
                    return true;
                }

                baord[i][c] = 0;
            }
        }
        return false;
    }

    void printArray(int[][] arr){
        for (int i=0;i<N;i++){
            for (int j=0;j<N;j++){
                System.out.print(" " +arr[i][j]+" ");
            }
            System.out.println("");
        }
    }

    private boolean isSafe(int[][] arr, int row, int col) {
        printArray(arr);
        System.out.println("");
        int i, j;
        for (i = 0; i < col; i++)
            if (arr[row][i] == 1)
                return false;
        //checking for upper diagonal on left side
        for (i = row, j = col; i >= 0 && j >= 0; i--, j--)
            if (arr[i][j] == 1)
                return false;
        for (i = row, j = col; i >= 0 && j >= 0; i--, j--) {
            if (arr[i][j] == 1)
                return false;

        }
        return true;

    }
}
