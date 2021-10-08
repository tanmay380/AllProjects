import java.util.Locale;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Answers a1= new Answers();
//        a1.fibonacci();
        a1.permuteMain();


    }

}
class Answers{
    //Fibonaaci Start
    protected void fibonacci(){
        long start = System.currentTimeMillis();
        int dp[] = new int[11];
//        System.out.println(fibonaaciRecusDP( 100,dp));
        System.out.println(fibonaaciRecusDPTable( 10,dp));

        long end = System.currentTimeMillis();
        System.out.println(end-start);
    }

    private int fibonaaciRecusDPTable(int num, int[] dp) {
        for(int i=0; i<=num;i++){
            if (i<=1){
                dp[i] = i;
                continue;
            }
            dp[i] = dp[i-1] + dp[i-2];
        }
        return  dp[num];
    }

    private long fibonaaciRecusDP(int i, long[] dp) {
        if (i==0 || i==1) {
            return dp[i] = i;
        }
        if (dp[i]!=0){
            return dp[i];
        }
        long num1= fibonaaciRecusDP(i-1 ,dp);
        long num2= fibonaaciRecusDP(i-2, dp);
        return dp[i] = num1+num2;
    }
    //Fibonaaci END

    //Permutation Start
    protected  void permuteMain(){
        int[] arr = {1,4,3,2};
        int target = 5;



    }

    //Permutaiton End



}

