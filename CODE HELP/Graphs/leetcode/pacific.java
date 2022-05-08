import java.util.ArrayList;

public class pacific {
    public static void main(String[] args) {
        int x = h.length;
        int y = h[0].length;
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                List<Integer> temp = new ArrayList<>();
                check(i, j, x, y, h, ans, temp);
            }
        }
        return ans;
    }

    public void check(int i, int j, int x, int y, int[][] h, List<List<Integer>> ans, List<Integer> temp) {
        /*if ((i < 0 && j < 0) || (i >= x && j >= y)) {
            ans.add(i);
            ans.add(j);
        }*/

        if (i < 0 || j < 0 || i >= x || j >= y)
            return;

        if((i==0 && j==y-1) || (i==x-1 && j==0)) {
            ans.add(i);
            ans.add(j);
            return;
        }

    }
}
