public class noofclosedislands {
    public static void main(String[] args) {
        int x = grid.length;
        int y = grid[0].length;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if(grid[i][j]==0){
                    count(grid, i, j , x, y);
                }
            }
        }
    }
    public int count(int[][]grid, int i, int j, int x, int y){
        if(i<0 || j<0 || i>=x || j>=y || grid[i][j]==2) return 0;
        grid[i][j] =2;
        count1 =0;
        count1+=count(grid, i+1, j, x, y);
        count1+=count(grid, i-1, j, x, y);
        count1+=count(grid, i, j+1, x, y);
        count1+=count(grid, i, j-1, x, y);
        return count1;
        System.out.println();
    }
}
