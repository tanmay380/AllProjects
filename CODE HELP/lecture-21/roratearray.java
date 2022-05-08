import java.util.Arrays;

public class roratearray {
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7};
        int n = arr.length;
        int k = 3;
        // int[] temp = new int[n];
        for(int i=0;i<n;i++){
            int temp=arr[(i+k)%n];
            arr[(i+k)%n]=arr[i]; 
            arr[i]=temp;       
        }
        // System.out.println(Arrays.toString(temp));
        // arr=temp;
        System.out.println(Arrays.toString(arr));


        String s ="aaabbb";
        s.length();
        s.charAt(index)
        
    }
}
