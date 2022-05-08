import java.util.Arrays;

public class quicksort {
    public static void main(String[] args) {
        int[] arr = { 10,9,8,7,6,5};
        int s = 0;
        int e = arr.length - 1;

        qickSort(arr, s, e);
    }

    private static void qickSort(int[] arr, int s, int e) {
        if (s >= e)
            return;

        int p = partition(arr, s, e);
        // System.out.println(Arrays.toString(arr));
 
        qickSort(arr, s, p - 1);
        System.out.println(Arrays.toString(arr));
        qickSort(arr, p + 1, e);
        
        System.out.println(Arrays.toString(arr));
    }

    private static int partition(int[] arr, int s, int e) {
        int a = arr[s], count = 0;
        for (int i = s+1; i <= e; i++) {
            if (arr[i] <= a) {
                count++;
            }
        }
        int pivotIndex= s+count;
        // swap with the count number element
        int temp = arr[pivotIndex];
        arr[pivotIndex] = a;
        arr[s] = temp;


        int i = s, j = e;

        while (i <pivotIndex && j>pivotIndex) {
            if (arr[i] > a && arr[j] < a) {
                int t = arr[i];
                arr[i] = arr[j];
                arr[j] = t;
                i++;
                j--;
            }
            else if(arr[i]<a)
                i++;
            else 
                j--;
                
        }
        return pivotIndex;
    }

}
