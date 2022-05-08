public class selectionSort {
    public static void main(String[] args) {
        int[] arr = { 10, 5, 1, 8, 9, 7, 6};

        selectionsort(arr);

        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    public static void selectionsort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minimumIndex = i;

            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minimumIndex]) 
                    minimumIndex = j;
            }
            if(i!=minimumIndex)
                swap(arr, i, minimumIndex);
        }
    }

    public static void swap(int[] arr, int index1, int index2) {

        arr[index1] = arr[index1] ^ arr[index2];
        arr[index2] = arr[index1] ^ arr[index2];
        arr[index1] = arr[index1] ^ arr[index2];
    }

}
