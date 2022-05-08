import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class endoscopy {
    static int row, col, X, Y, L;
    static int map[][] = new int[1000][1000];
    static int visited[][] = new int[1000][1000];

    public static void main(String[] args) throws NumberFormatException, IOException {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t-- > 0) {
            row = sc.nextInt();
            col = sc.nextInt();
            X = sc.nextInt();
            Y = sc.nextInt();
            L = sc.nextInt();
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    map[i][j] = sc.nextInt();
                    System.out.print(map[i][j]);
                    visited[i][j] = 0;
                }
            }
            sc.close();
            if (map[X][Y] > 0) {
                System.out.println(solve(X, Y, L));
            } else {
                System.out.println(0);
            }
        }
    }

    private static boolean valid(int x, int y) {
        return (x < row && x >= 0 && y < col && y >= 0);
    }

    private static boolean left(int x, int y) {
        return (map[x][y] == 1 || map[x][y] == 3 || map[x][y] == 6 || map[x][y] == 7);
    }

    private static boolean right(int x, int y) {
        return (map[x][y] == 1 || map[x][y] == 3 || map[x][y] == 4 || map[x][y] == 5);
    }

    private static boolean up(int x, int y) {
        return (map[x][y] == 1 || map[x][y] == 2 || map[x][y] == 4 || map[x][y] == 7);
    }

    private static boolean down(int x, int y) {
        return (map[x][y] == 1 || map[x][y] == 2 || map[x][y] == 6 || map[x][y] == 5);
    }

    private static int solve(int x, int y, int l) {
        int ans = 0;
        Queue<Group> q = new LinkedList<>();
        q.add(new Group(x, y, l));
        visited[x][y] = 1;

        while (!q.isEmpty()) {
            Group cur = q.poll();
            int xc = cur.x;
            int yc = cur.y;
            int lc = cur.l;

            if (lc == 0)
                continue;

            ans++;

            if (valid(xc, yc - 1) && left(xc, yc) && right(xc, yc - 1) && visited[xc][yc - 1] == 0) {
                q.add(new Group(xc, yc - 1, lc - 1));
                visited[xc][yc - 1] = 1;
            }
            if (valid(xc, yc + 1) && right(xc, yc) && left(xc, yc + 1) && visited[xc][yc + 1] == 0) {
                q.add(new Group(xc, yc + 1, lc - 1));
                visited[xc][yc + 1] = 1;
            }
            if (valid(xc - 1, yc) && up(xc, yc) && down(xc - 1, yc) && visited[xc - 1][yc] == 0) {
                q.add(new Group(xc - 1, yc, lc - 1));
                visited[xc - 1][yc] = 1;
            }
            if (valid(xc + 1, yc) && down(xc, yc) && up(xc + 1, yc) && visited[xc + 1][yc] == 0) {
                q.add(new Group(xc + 1, yc, lc - 1));
                visited[xc + 1][yc] = 1;
            }
        }
        return ans;
    }
}

class Group {
    int x, y, l;

    Group(int _x, int _y, int _l) {
        x = _x;
        y = _y;
        l = _l;
    }
}