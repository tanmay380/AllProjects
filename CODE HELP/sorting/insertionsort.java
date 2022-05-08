import java.util.Arrays;

public class insertionsort {
    public static void main(String[] args) {
        int[] arr = { 100,90,890,70,60,40,50,30,20,10};
        int n = arr.length;

        for (int i = 1; i < n; i++) {
            int temp = arr[i];
            int j = i-1;
            while (j >= 0 && arr[j] >=temp) {
                System.out.println(i + " " + j);
                arr[j+1]=arr[j];
                j--;
            }
            arr[j+1]=temp;

            System.out.println(Arrays.toString(arr));
        }
        System.out.println(Arrays.toString(arr));
    }
}
