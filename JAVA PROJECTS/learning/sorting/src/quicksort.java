public class quicksort {
    public static void main(String[] args) {
        int arr[] = {10,9, 8, 6, 5, 4};
//        int arr[] = {9, 4, 4, 8};
        int n = arr.length;
        printArray(arr, n);
        quicksort1(arr, 0, n - 1);
        printArray(arr, n);
    }

    private static void quicksort1(int[] arr, int low, int high) {
        int partitionIndex;

        if (low < high) {
            partitionIndex = partionIndex(arr, low, high);
            System.out.println(partitionIndex);
            printArray(arr,arr.length);
            quicksort1(arr, low, partitionIndex - 1);
            quicksort1(arr, partitionIndex + 1, high);
        }
    }

    private static int partionIndex(int[] A, int low, int high) {
        int pivot = A[low];
        int i = low + 1;
        int j = high;
        int temp;

        do {
            while (i < high && A[i] <= pivot) {
                i++;
            }

            while (A[j] > pivot) {
                j--;
            }

            if (i < j) {
                temp = A[i];
                A[i] = A[j];
                A[j] = temp;
            }
        } while (i < j);

        temp = A[low];
        A[low] = A[j];
        A[j] = temp;


        return j;
    }

    private static void printArray(int[] arr, int n) {
        for (int j = 0; j < n; j++) {
            System.out.print(arr[j] + " ");
        }
        System.out.println();
    }
}
