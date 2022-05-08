package day4;

import java.util.Arrays;

public class bubblesort {
    public static void main(String[] args) {
        int[] arr = { 9, 8, 7, 6, 5, 4,9 };

        int size = arr.length;

        bubblesortRecursiom(arr, size);
        System.out.println(Arrays.toString(arr));

    }

    private static void bubblesortRecursiom(int[] arr, int size) {
        if (size == 1 || size == 0)
            return;
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                arr[i] = arr[i] ^ arr[i + 1];
                arr[i + 1] = arr[i] ^ arr[i + 1];
                arr[i] = arr[i] ^ arr[i + 1];
            }
        }
        bubblesortRecursiom(arr, size - 1);
    }
}
