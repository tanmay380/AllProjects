import java.util.Arrays;

public class quicksort1 {
    public static void main(String[] args) {
        int[] arr = { 9, 8, 7, 6, 4,10,2,3,4,5 };
        int end = arr.length - 1;
        int s = 0;

        quickSort(arr, s, end);
        System.out.println(Arrays.toString(arr));
    }

    private static void quickSort(int[] arr, int s, int e) {
        if (s >= e)
            return;
        int p = partition(arr, s, e);

        // left part
        quickSort(arr, s, p - 1);
        // right part\
        quickSort(arr, p + 1, e);

    }

    private static int partition(int[] arr, int s, int e) {
        int count = 0;

        for (int i = s; i <= e; i++) {
            if (arr[s] > arr[i])
                count++;
        }
        int temp = arr[s];
        arr[s] = arr[count + s];
        arr[count + s] = temp;

        int i = s, j = e;
        while (i <(count+s) && j>(count+s)) {
            while (arr[i] < arr[count + s])
                i++;
            while (arr[j] > arr[count + s])
                j--;
            if(arr[i]>arr[s+count] && arr[j]<arr[s+count]){
             int temp1 = arr[i];
                arr[i]=arr[j];
                arr[j]=temp1;
            }

        }

        return (count + s);
    }
}
