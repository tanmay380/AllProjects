import java.util.Arrays;

public class mergearray {
    public static void main(String[] args) {
        // int[] nums1 = { 1,2,3, 0, 0, 0 }, num2 = { 2, 5, 6 };
        int[] nums1 = { 10,20,30, 0, 0, 0 }, num2 = { 2, 5, 6 };
        int m = 3, n = 3;

        int i = 0, j = 0;
        while (i < m && j < n) {
            if (nums1[i] <= num2[j]) {
                i++;
            } else {
                for (int j2 = m-1; j2 >= i; j2--) {
                    System.out.println(j2);
                    nums1[j2 + 1] = nums1[j2];                   
                }
                m++;
                nums1[i]=num2[j];                
                j++;
            }
        }
        // while(i<m){
        //     nums1[i]=
        // }
    
        while(j<n){
            nums1[i]=num2[j];
            i++;
            j++;
        }
        
        System.out.println(Arrays.toString(nums1));
    }
}
