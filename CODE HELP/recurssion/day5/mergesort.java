package day5;

import java.util.Arrays;

public class mergesort {
    public static void main(String[] args) {
        int[] arr = {9,8,7,6,5,4,3,2,1};
        int s=0;
        int e= arr.length-1;

        mergesortRecursion(arr, s ,e);
        System.out.println(Arrays.toString(arr));
    }

    private static void mergesortRecursion(int[] arr, int s, int e) {
        if(s>=e){
            return;
        }
        int mid=s+(e-s)/2;
        //left part
        mergesortRecursion(arr, s, mid);
        //right part
        mergesortRecursion(arr, mid+1, e);
        //merge arrays
        merge(arr,s,e);
    }

    private static void merge(int[] arr, int s, int e) {
        int mid=s+(e-s)/2;
        int l1= mid-s+1;
        int l2 = e-mid;

        int[] arr1= new int[l1];
        int[] arr2= new int[l2];

        int k=s;
        for (int index = 0; index < l1; index++) {
            arr1[index]=arr[k++];
        }
         k=mid+1;
        for (int index = 0; index < l2; index++) {
            arr2[index]=arr[k++];
        }
        //merge 2 sorted arraysl
        int index1=0, index2=0;

        k=s;

        while(index1<l1 && index2<l2){
            if(arr1[index1]<arr2[index2]){
                arr[k++]=arr1[index1++];
            }else{
                arr[k++]=arr2[index2++];
            }
        }
        while (index1<l1) {
            arr[k++]=arr1[index1++];
        }
        while (index2<l2) {
            arr[k++]=arr2[index2++];
        }
    }
}
