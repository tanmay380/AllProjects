import java.util.Arrays;

public class DPBASIC {
    public static void main(String[] args) {
        int n = 18;
        int[] a = {7, 5, 1};
        int dp[] = new int[n + 1];
        Arrays.fill(dp, -1 );
        dp[0] = 0;

        System.out.println(minCoins(n, a, dp));
        for (int x :
                dp) {
            System.out.print(x+" ");
        }
    }
    static int minCoins(int n, int[] a, int dp[]) {
        if (n == 0) return 0;
        int ans = Integer.MAX_VALUE;

        for (int i = 0; i < a.length; i++) {
            if (n - a[i] >= 0) {
                int subanswer = 0;
                System.out.println(n+"-"+a[i]+ "="+ (n-a[i]));
                if (dp[n - a[i]] != -1) {
                    subanswer = dp[n - a[i]];
                } else {
                    subanswer = minCoins(n -  a[i], a, dp);
                }
                if (subanswer != Integer.MAX_VALUE && subanswer + 1 < ans) {
                    ans = subanswer + 1;
                }
            }
        }
        dp[n] =ans;
        return ans;
    }

}
