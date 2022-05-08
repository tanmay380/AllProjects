import java.util.LinkedList;
import java.util.Queue;

import javax.management.Query;

public class rottenorange {
    public static void main(String[] args) {
        //int[][] grid = {{2,1,1},{0,1,0},{0,1,1}};
        int[][] grid ={{0}};
        int x = grid.length;
        int y= grid[0].length;

        Queue<int[]> q= new LinkedList<>();
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                if(grid[i][j]==2)
                    q.add(new int[]{i,j});
            }
        }
        int time=0;
        while (!q.isEmpty()) {
            int size = q.size();
            while (size-->0) {
                
                int[][] dir = {{0,1},{1,0}, {-1,0} , {0,-1}};
                int[] p = q.poll();
    
                for (int[] d : dir) {
                    int a = p[0] + d[0];
                    int b = p[1] + d[1];
    
                    if(a>=0 && b>=0 && a<x && b<y && grid[a][b]!=2 && grid[a][b]!=0)
                    {
                        q.add(new int[]{a,b});
                        grid[a][b]=2;
    
                    }
    
                }
            }
            
            time++;
        }
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if(grid[i][j]==1)
                    time=-1;
            }
        }
        
        System.out.println(time>-1 ? 0 : time);
    }
}
