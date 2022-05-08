import java.util.ArrayList;
import java.util.Arrays;

public class toerofhanoi {
    public static void main(String[] args) {

        ArrayList<ArrayList<Integer>> ans = new ArrayList<ArrayList<Integer>>();
        System.out.println(tfh(3, 1, 2, 3, ans));

    }

    private static ArrayList<ArrayList<Integer>> tfh(int i, int a, int b, int c, ArrayList<ArrayList<Integer>> ans) {
        if (i > 0) {
            tfh(i - 1, a, c, b, ans);
            ans.add(new ArrayList<>(Arrays.asList(a, c)));
            tfh(i - 1, b, a, c, ans);
        }
        return ans;
    }
}
