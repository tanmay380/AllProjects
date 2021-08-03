import java.lang.reflect.Array;
import java.util.Arrays;

public class BubbleSort {
    public static void main(String[] args) {
        int[] arr = {4,5,3,1,35,21,46};

        bubbleSort(arr);
    }

    private static void bubbleSort(int[] arr) {
        for (int i=0;i<arr.length-1;i++){
            for (int j=0;j<arr.length-1;j++){
                if (arr[j+1]<arr[j])
                    swap1(arr,j,j+1);
            }
        }
        System.out.println(Arrays.toString(arr));


    }

    private static void swap1(int[] arr, int j, int i) {

        arr[j]=arr[j]^arr[i];
        arr[i]=arr[j]^arr[i];
        arr[j]=arr[j]^arr[i];
    }
}
