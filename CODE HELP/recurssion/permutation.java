import java.util.Arrays;

public class permutation {
    public static void main(String[] args) {
        int[] arr={1,2,3};
        
        permute(arr,0);
    }

    private static void permute(int[] arr, int index) {
        if(index>=arr.length){
            System.out.println(Arrays.toString(arr));
            return;
        }
        for (int i = index; i < arr.length; i++) {
            swap(arr,index,i);
            permute(arr, index+1);
            // swap(arr,index,i);
        }


    }

    private static void swap(int[] arr, int index, int i) {
        int temp =arr[i];
        arr[i]=arr[index];
        arr[index]= temp;
    }

    
}
