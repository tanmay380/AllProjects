import java.util.LinkedList;
import java.util.Queue;

public class matrix {
    public static void main(String[] args) {

        int[][] mat = { { 0, 0, 0 },
                { 0, 1, 0 },
                { 1, 1, 1 } };
        int x = mat.length;
        int y = mat[0].length;

        Queue<int[]> q = new LinkedList<>();

        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                if (mat[i][j] == 0)
                    q.add(new int[] { i, j });
                else {
                    mat[i][j] = -1;
                }
            }
        }

        int[][] dirs = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };

        int length = 0;

        while (!q.isEmpty()) {
            int size = q.size();
            length++;
            while (size-->0) {
                int[] p = q.poll();

            for (int[] d : dirs) {
                int r = p[0] + d[0];
                int c = p[1] + d[1];

                if(r<0 || c<0 || r>=x || c>=y || mat[r][c]!=-1)
                    continue;

                mat[r][c]=length;
                q.add(new int[]{r,c});
            }
            }
            
        }

        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat.length; j++) {
                System.out.print(mat[i][j] + " ");
            }
            System.out.println();
        }
        
    }
}
