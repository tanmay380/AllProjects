public class final1 {
    static void bubblesort(int[] arr, int n) {
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n-1; j++) {
                if (arr[j] > arr[j+1]) {
                    int temp=arr[j+1];
                    arr[j+1]=arr[j];
                    arr[j]=temp;
                }
            }
        }
        for (int j = 0; j < n; j++) {
            System.out.print(arr[j]);
            }
    }


    public static void main(String[] args) {
        int[] arr = {7, 6, 5, 4, 3, 2, 1};
        int n = arr.length;
        bubblesort(arr, n);
    }
}
