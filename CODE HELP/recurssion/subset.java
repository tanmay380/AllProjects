import java.util.ArrayList;
import java.util.Arrays;

public class subset {
    public static void main(String[] args) {
        int[] arr={1,2,3};
        
        int index=0;
        ArrayList<Integer> ans= new ArrayList<>();
        subset1(arr,index,ans);
    }

    private static void subset1(int[] arr, int index, ArrayList<Integer> ans) {
    
        if(index>=arr.length){
            // output.add(ans);
            System.out.println(ans);
            return;
        }
        //excluse
        int element=arr[index];
        ans.add(element);
        subset1(arr, index+1, ans);
        ans.remove(ans.size()-1);
        //include
        
        subset1(arr, index+1, ans);
        
    }
}
