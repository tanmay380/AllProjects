import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class nqueens {
    public static void main(String[] args) {
        int n = 6;
       

        placeQueen();

    }

    private static void placeQueen() {
        int n = 6;
        char[][] chessBoard = new char[n][n];
        for (int i = 0; i < chessBoard.length; i++) {
            for (int j = 0; j < chessBoard.length; j++) {
                chessBoard[i][j]='.';
            }
        }

        List<List<String>> res = new ArrayList<>();
        int leftside[] = new int[n];
        int upperDiagonal[] = new int[2*n-1];
        int lowerDiagonal[] = new int[2*n-1];

        dfs(chessBoard, 0, res, leftside, upperDiagonal, lowerDiagonal);
       

    }
    static List < String > construct(char[][] board) {
        List < String > res = new LinkedList < String > ();
        for (int i = 0; i < board.length; i++) {
            String s = new String(board[i]);
            System.out.println(s);
            res.add(s);
        }
        return res;
    }
    private static void dfs(char[][] chessBoard, int col, List<List<String>> res,  int leftside[] ,int upperDiagonal[],int lowerDiagonal[] ) {
        if (col==chessBoard.length) {
            res.add(construct(chessBoard));
            return;
        }
        for (int row = 0; row < chessBoard.length; row++) {
            if(leftside[row]==0 && lowerDiagonal[row+col]==0 && upperDiagonal[chessBoard.length-1+col-row]==0){
                chessBoard[row][col]='Q';
                leftside[row]=1;
                lowerDiagonal[row+col]=1;
                upperDiagonal[chessBoard.length-1 + col - row] =1;
                dfs(chessBoard, col+1, res, leftside, upperDiagonal, lowerDiagonal);
                chessBoard[row][col]='.';
                leftside[row]=0;
                lowerDiagonal[row+col]=0;
                upperDiagonal[chessBoard.length-1 + col - row] =0;
            }
        }

    }

    // private static boolean isSafe(char[][] chessBoard, int row, int col) {
    //     //row
    //     int r= row, c = col;
    //     //check in left
    //     while (col-->0) {
    //         if(chessBoard[row][col]=='Q')
    //             return false;
    //     }
    //     // check in upper left diagonal
    //     row=r; col = c;
    //     while(row-->0 && col-->0){
    //         if(chessBoard[row][col]=='Q')    return false;
    //     }
    //     row=r; col = c;
    //     while(row++<chessBoard.length-1 && col-->0){
    //         if(chessBoard[row][col]=='Q')    return false;             
    //     }

    //     return true;
    // }

    private static void print(char[][] array){
        for (int index = 0; index < array.length; index++) {
            for (int j = 0;j < array.length; j++) {
                System.out.print(array[index][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
