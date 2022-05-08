public class ratinamaze {
    public static void main(String[] args) {

        int[][] arr = { { 1, 0, 0, 0 },
                { 1, 1, 0, 1 },
                { 1, 1, 0, 0 },
                { 0, 1, 1, 1 } };
        int srcx = 0;
        int srcy = 0;

        int[][] visit = new int[arr.length][arr.length];
        for (int i = 0; i < visit.length; i++) {
            for (int j = 0; j < visit.length; j++) {
                visit[i][j] = 0;
            }
        }
       
        String path = "";
        solve(arr, 4, srcx, srcy, visit, path);
    }

    private static void solve(int[][] arr, int n, int x, int y, int[][] visit, String path) {
        if (x == n - 1 && y == n - 1) {
            System.out.println(path);
            return;
        }
        visit[x][y] = 1;

        // down
        int newx = x + 1;
        int newy = y;
        if (isSafe(arr, newx, newy, visit, n)) {
            path += "D";
            solve(arr, n, newx, newy, visit, path);
            path = path.substring(0, path.length() - 1);

        }

        // left
        newx = x;
        newy = y - 1;

        if (isSafe(arr, newx, newy, visit, n)) {
            path += "L";
            // System.out.println(path);
            solve(arr, n, newx, newy, visit, path);
            path = path.substring(0, path.length() - 1);
        }

        // right
        newx = x;
        newy = y + 1;

        if (isSafe(arr, newx, newy, visit, n)) {
            path += "R";
            // System.out.println(path);
            solve(arr, n, newx, newy, visit, path);
            path = path.substring(0, path.length() - 1);
        }

        // UP
        newx = x - 1;
        newy = y;

        if (isSafe(arr, newx, newy, visit, n)) {
            path += "U";
            // System.out.println(path);
            solve(arr, n, newx, newy, visit, path);
            path = path.substring(0, path.length() - 1);
        }
        visit[x][y] = 0;
    }

    private static boolean isSafe(int[][] arr, int x, int y, int[][] visit, int n) {
        if ((x >= 0 && x < n) && (y >= 0 && y < n) && visit[x][y] == 0 && arr[x][y] == 1) {
            return true;
        }
        return false;
    }
}