import java.util.Arrays;

public class insertionSort {
    public static void main(String[] args) {
        int[] arr= {4,5,3,1,35,21,46};
        insertionS(arr);
    }

    private static void insertionS(int[] arr) {
        for (int i=0;i<arr.length; i++) {
            int temp = arr[i];
            int j = i - 1;

            while (j >= 0 && arr[j]>temp){
                arr[j+1]=arr[j];
                j--;
            }
            arr[j+1]= temp;
        }

        System.out.println(Arrays.toString(arr));
    }
}
