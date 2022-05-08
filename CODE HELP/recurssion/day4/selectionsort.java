package day4;

import java.util.Arrays;

public class selectionsort {
    public static void main(String[] args) {
        int[] arr = { 9,8,7,6,5,4,3,2,1,9,8,7, 9,8,7,6,5,4,3,2,1,9,8,7};
        int size = arr.length;

        selectionsortRecurston(arr, 0);
        System.out.println(Arrays.toString(arr));
    }

    private static void selectionsortRecurston(int[] arr, int curindex) {
        int size = arr.length;
        if (size == 0 || size == 1) {
            return;
        }
        if (curindex == size)
            return;
        
        int minin=curindex;

        for (int i = curindex; i < arr.length; i++) {
            if (arr[i] < arr[minin]){
                minin=i;
            }
        }
        int temp  =  arr[curindex];
        arr[curindex]=arr[minin];
        arr[minin]=temp;
        
        selectionsortRecurston(arr,++curindex);
        
    }
}
