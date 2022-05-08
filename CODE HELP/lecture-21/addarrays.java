import java.util.ArrayList;
import java.util.Arrays;

public class addarrays {
    public static void main(String[] args) {
        int[] arr1={3,2};
        int[] arr2 = {1,2,3};

        int m=arr1.length;
        int n=arr2.length;

        int i=m-1,j=n-1;
        ArrayList<Integer> ans= new ArrayList<>();

        int carry=0;

        while(i>=0 && j>=0){
            int sum = arr1[i]+arr2[j] + carry;
            carry= sum/10;
            sum=sum%10;
            ans.add(sum);           
            i--;
            j--;
        }

        while(i>=0){
            int sum = arr1[i]+ carry;
            carry= sum/10;
            sum=sum%10;
            ans.add(sum);           
            i--;
        }

        while(j>=0){
            int sum = arr2[j]+ carry;
            carry= sum/10;
            sum=sum%10;
            ans.add(sum);           
            j--;
        }

        while (carry!=0) {
            int sum = carry;
            carry= sum/10;
            sum=sum%10;
            ans.add(sum);
        }

        int [] ans1= new int[ans.size()];
        for (int k = ans.size()-1; k >=0; k--) {
            ans1[ans.size()-k-1]=ans.get(k);
        }
        System.out.println(Arrays.toString(ans1));
    }
}
