public interface numberofislands {
    public static void main(String[] args) {
        
        int x=grid.length;
        int y=grid[0].length;
        int maxArea=0;

        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                if(grid[i][j]==1){
                    int Area= dfs(grid,i,j,x,y,0);
                    maxArea= maxArea < Area ? maxArea=Area: maxArea;
                    System.out.println();
                }
            }
        }
        
        System.out.println(maxArea);
    }
    public static int    dfs(char[][] grid, int i,int j, int x, int y,inr curArea){
        if(i<0 || j<0 || i>=x || j>=y) return curArea;
        if(grid[i][j] ==2 || grid[i][j]==0) return curArea;
        grid[i][j]=2;
       curArea+= dfs(grid, i+1, j, x, y,curArea);
       curArea+= dfs(grid, i-1, j, x, y,curArea);
       curArea+= dfs(grid, i, j+1, x, y,curArea);
       curArea+= dfs(grid, i, j-1, x, y,curArea);

        return curArea;
    }
}
