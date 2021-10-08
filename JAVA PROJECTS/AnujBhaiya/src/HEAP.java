public class HEAP {
    public static void main(String[] args) {
        int[] arr = {10, 50, 60, 20, 70, 100, 90};
        int size = arr.length;
        CreateHeap createHeap = new CreateHeap();
        createHeap.heapcreation(arr, size);

    }

}
class CreateHeap{

    protected static void heapcreation(int[] arr, int size) {
        for (int i = size / 2; i >= 0; i--) {
            heapify(arr, size, i);
        }

        printarray(arr);
    }

    private static void printarray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    private static void heapify(int[] arr, int size, int i) {
        int largest = i;
        int left = i * 2;
        int right = i * 2 + 1;

        if (left < size && arr[left] > arr[largest]) {
            largest = left;
        }
        if (right < size && arr[right] > arr[largest]) {
            largest = right;
        }

        if (largest != i) {
            swaparray(arr, i, largest);
            heapify(arr, size, largest);
        }

    }
    private static void swaparray(int[] arr, int i, int largest) {
        int temp = arr[i];
        arr[i] =arr[largest];
        arr[largest]=temp;

    }
}

