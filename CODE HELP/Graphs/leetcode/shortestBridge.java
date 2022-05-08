import java.util.LinkedList;
import java.util.Queue;

/*public class shortestBridge {
    public static void main(String[] args) {
        int[][] grid = { { 0, 1, 0 }, { 0, 0, 0 }, { 0, 1, 1 } };

        int x = grid.length;
        int y = grid[0].length;
        boolean t =true;
        Queue<int[]> q = new LinkedList<>();
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                if (grid[i][j] == 1 && t){
                    dfs(i, j, x, y, q, grid);
                    t=false;
                    break;
                }
            }
        }

        int count = -1;
        t=true;
        while (!q.isEmpty()) {
            count++;
            int size = q.size();
            while (size-- > 0) {
                int[] p = q.poll();
                int[][] dir={{0,1}, {1,0}, {-1,0}, {0,-1}};
                for (int[] is : dir) {
                    int a = p[0]+is[0];
                    int b = p[1]+is[1];

                    if(a>=0 && b>=0 && a<x && b<y && grid[a][b]==0 ){
                        q.add(new int[]{a,b});
                        grid[a][b]=2;
                    }
                     if(a>=0 && b>=0 && a<x && b<y && grid[a][b]==1){
                        System.out.println(" inside id " +count);
                        t=false;
                        return;
                    }
                }
            }
        }
        System.out.println(count);

        // int size=q.size();
        // while(size-->0){
        // int[] p =q.peek();
        // System.out.println(p[0] + " " + p[1]);
        // q.poll();
        // }
    }

    private static void dfs(int i, int j, int x, int y, Queue<int[]> q, int[][] grid) {
        if (i < 0 || j < 0 || i >= x || j >= y || grid[i][j] == 0 || grid[i][j] == 2)
            return;

        grid[i][j] = 2;
        q.add(new int[] { i, j });

        dfs(i + 1, j, x, y, q, grid);
        dfs(i - 1, j, x, y, q, grid);
        dfs(i, j + 1, x, y, q, grid);
        dfs(i, j - 1, x, y, q, grid);

        return;

    }
}*/
class Solution {

    public void dfs(int a[][], int i, int j) {
        if (i < 0 || i >= a.length || j < 0 ||  j >= a[0].length || a[i][j] == 0 || a[i][j] == 2) return;
        a[i][j] = 2;
        dfs(a,i-1,j);dfs(a,i+1,j);dfs(a,i,j-1);dfs(a,i,j+1);
    }
    public int shortestBridge(int[][] a) {
        boolean found = false;
        Queue<int[]> q = new LinkedList<>();
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                 if (a[i][j] == 1 && !found) {
                     found = true;
                     dfs(a,i,j);
                 }
                if (found && a[i][j] == 1) q.add(new int[]{i,j});
            }
        }
        int ans = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int c = 0; c < size; c++) {
                int b[] = q.remove();
                int i = b[0];
                int j = b[1];
                if ((i > 0 && a[i-1][j] == 2) || (i < a.length-1 && a[i+1][j] == 2) || (j > 0 && a[i][j-1] == 2) || 
                    (j < a[0].length-1 && a[i][j+1] == 2)) return ans;
                if (i > 0 && a[i-1][j] == 0) {
                    a[i-1][j] = 1;
                    q.add(new int[]{i-1,j});
                }
                if (i < a.length-1 && a[i+1][j] == 0) {
                    a[i+1][j] = 1;
                    q.add(new int[]{i+1,j});
                }
                if (j > 0 && a[i][j-1] == 0) {
                    a[i][j-1] = 1;
                    q.add(new int[]{i,j-1});
                }
                if (j < a[0].length-1 && a[i][j+1] == 0) {
                    a[i][j+1] = 1;
                    q.add(new int[]{i,j+1});
                }
            }
            ans++;
        }
        return 0;
    }
    }