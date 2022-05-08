package day4;

import java.util.Arrays;

public class insertionsort {
    public static void main(String[] args) {
        int[] arr ={7,65,43,2,1};
        int size  =arr.length;

        insertionsortRecursion(arr,size);
        System.out.println(Arrays.toString(arr));
    }

    private static void insertionsortRecursion(int[] arr, int size) {
        if(size==0 || size==1)
            return;
        insertionsortRecursion(arr, size-1);

        int last= arr[size-1];
        int j = size-2;

        while(j>=0 && arr[j]>last){
            arr[j+1]=arr[j];
            j--;
        }
        arr[j+1]=last;
        
    }
}
