public class MooresVoting {
    public static void main(String[] args) {
        int[] a = {-1, -2, -2, -2, -1};
//        majorityElement(a);
        maxSubArrayKades(a);
    }

    private static void maxSubArrayKades(int[] a) {
        int maxSum=-100;
        int curSum=0;

        for (int i=0;i<a.length;i++){
            curSum = curSum + a[i];
            if (curSum>maxSum){
                maxSum=curSum;
            }
            if (curSum<0){
                curSum=0;
            }
        }
        System.out.println(maxSum);

    }

    private static void majorityElement(int[] a) {
        int ansIndex = 0;
        int count = 0;

        for (int i = 0; i < a.length; i++) {
            if (a[i] == a[ansIndex]) {
                count++;
            } else
                count--;
            if (count == 0) {
                ansIndex = i;
                count = 1;
            }
        }
        int num=a[ansIndex];
        count=0;
        for (int i = 0; i < a.length; i++) {
            if (a[i]==num)
                count++;
        }
        if (count>=3)
            System.out.println(a[ansIndex]);
    }
}
