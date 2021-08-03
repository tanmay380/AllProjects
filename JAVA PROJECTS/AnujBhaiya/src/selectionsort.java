import java.util.Arrays;

public class selectionsort {
    public static void main(String[] args) {
//        int[] arr= {4,5,3,1,35,21,46};
        int[] arr = {4, 1, 9, 2, 3, 6};
        SelectionSort(arr);

    }

    private static void SelectionSort(int[] arr) {

        for (int i = 0; i < arr.length-1; i++) {
            int min=i;
            for (int j = i+1; j < arr.length; j++) {
                if (arr[j]<arr[min]) min=j;

            }
            if (min!=i)
                swap1(arr,min,i);
        }

        System.out.println(Arrays.toString(arr));
    }

    private static void swap1(int[] arr, int i, int j) {
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }
}
