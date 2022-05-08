import java.util.Arrays;

public class reducesum {
    public static void main(String[] args) {
        int[][] arr = { {3, 1, 2}, 
                        {2, 3, 5}, 
                        {3, 1, 2}, };

        int[] sum = new int[arr[1].length];

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                sum[j]+=arr[i][j];
            }
        }

        System.out.println(Arrays.toString(sum));


    }
}
