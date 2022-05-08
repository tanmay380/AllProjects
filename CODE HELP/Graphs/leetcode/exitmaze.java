import java.sql.Time;
import java.util.LinkedList;
import java.util.Queue;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

public class exitmaze {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        String[][] grid = { { "+", ".", "." }, { "+", "+", "+" }, { ".", ".", "." }, { "+", "+", "+" },
                { "+", "+", "." },
                { "+", "+", "." }, { ".", "+", "." }, { ".", "+", "+" }, { "+", "+", "+" }, { "+", "+", "+" } };
        int x = grid.length;
        int y = grid[0].length;

        int[] entrance = { 7, 0 };

        Queue<int[]> q = new LinkedList<>();
        grid[entrance[0]][entrance[1]] = "+";

        q.add(entrance);
        int steps = 0;
        while (!q.isEmpty()) {
            steps++;
            int size = q.size();
            while (size-- > 0) {
                int[][] dir = { { 0, 1 }, { 1, 0 }, { -1, 0 }, { 0, -1 } };
                int[] p = q.poll();

                for (int[] i : dir) {
                    int a = p[0] + i[0];
                    int b = p[1] + i[1];

                    if (a >= 0 || b >= 0 || a < x || b < y || grid[a][b] == "."){

                    // if(grid[a][b]=="." && ((a==0 && b<y) || (a<x && b==0) ||(a==x-1 && b<y) ||
                    // (a<x && b==y-1))){
                    if ((a == 0 || b == 0 || a == x - 1 || b == y - 1)) {
                        System.out.println("ans " + steps);
                        long end = System.currentTimeMillis();
                        long elapsedTime = end - start;
                        System.out.println(elapsedTime);
                        return;
                    }
                    System.out.println(a + " + " + b);
                    q.add(new int[] { a, b });
                    grid[a][b] = "+";
                }
            }

            }

        }
        long end = System.currentTimeMillis();
        long elapsedTime = end - start;
        System.out.println(-1);

    }
}
