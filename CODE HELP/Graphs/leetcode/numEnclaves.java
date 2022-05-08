public class numEnclaves {
    public static void main(String[] args) {
        int x= grid.length;
        int y= grid[0].length;

        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                if(i*j==0 || i==x-1 || j==y-1){
                    if(grid[i][j]==1){
                        dfs(grid, i, j , x, y);
                    }
                }
            }
        }       

        for (int i = 0; i <x; i++) {
            for (int j = 0; j < y; j++) {
                if(grid[i][j]==1){
                    dfs1(grid, i, j , x, y);
                }
            }
        }

    }
    public void dfs(int[][] grid, int i, int j , int x , int y){
        if(i<0 || j<0 || i>=x|| j>= y) return; 
        if(grid[i][j]==0) return;
        
        grid[i][j]=0;

        dfs(grid, i+1, j, x, y);
        dfs(grid, i-1, j, x, y);
        dfs(grid, i, j+1, x, y);
        dfs(grid, i, j-1, x, y);
    }
    public int dfs1(int[][] grid, int i, int j , int x , int y){
        if(i<0 || j<0 || i>=x|| j>= y) return 0; 
        if(grid[i][j]==0) return 0;
        
        grid[i][j]=0;
        int count =1;
       count+= dfs1(grid, i+1, j, x, y);
       count+= dfs1(grid, i-1, j, x, y);
       count+= dfs1(grid, i, j+1, x, y);
       count+= dfs1(grid, i, j-1, x, y);

       return count;



    }

}
