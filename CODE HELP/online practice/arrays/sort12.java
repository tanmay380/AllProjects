import java.util.Arrays;

public class sort12 {
    public static void main(String[] args) {
        int[] arr = {1,2,1,1,2,2,2,0,0,0};
        int n= arr.length;

        int i=0, j =0, k=n-1;
        while(i<j){
            
        }
        System.out.println(Arrays.toString(arr));
    }

    private static void swap(int[] arr, int i, int j) {
        
        arr[i]=arr[i]+arr[j];
        arr[j]=arr[i]-arr[j];
        arr[i]=arr[i]-arr[j];
    }

}
