import java.util.Arrays;

public class rank {
    public static void main(String[] args) {
        int[] arr ={3,4,4 };
        int n  = arr.length;
        int k  = 1;

        Arrays.sort(arr);
        System.out.println(arr[n-k]);
    }
}
