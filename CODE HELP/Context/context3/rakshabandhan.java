import java.util.Arrays;

public class rakshabandhan {
    public static void main(String[] args) {
        int[] arr= {-1,0,-2};

        int size= arr.length-1;

        Arrays.sort(arr);
        int ans  =0 ;
        int count=0;

        for (int i = size; i >=0; i--) {
            ans+=arr[i];
            if(ans>0){
                count++;
            }
        }
        System.out.println(count);
    }
}
