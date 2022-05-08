import java.util.Arrays;

public class bubblesort {
    public static void main(String[] args) {
        int[] arr = {10,1,7,100,6,14,9};
        int size= arr.length;

        for(int i=0; i< size-1;i++){
            for (int j = 0; j < arr.length-1-i; j++) {
                if(arr[j]>arr[j+1]){
                    arr[j]=arr[j]^arr[j+1];
                    arr[j+1]=arr[j]^arr[j+1];
                    arr[j]=arr[j]^arr[j+1];
                }
            }
        }
        System.out.println(Arrays.toString(arr));

    }
}
