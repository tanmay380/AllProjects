import java.util.Arrays;

public class movezeroes {
    public static void main(String[] args) {
        int[] nums = { 0, 0, 1 };
        int n = nums.length;

       int i=0, j=0;
       while(j<n){
           if(nums[j]!=0){
                nums[i]=nums[j];
                nums[j]=0;
                i++;
           }
           j++;

        }    

        System.out.println(Arrays.toString(nums));
    }
}
