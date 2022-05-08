
import java.util.ArrayList;
import java.util.Arrays;
public class paisrsum {
    public static void main(String[] args) {
        int[] arr = { 2, -3, 3, 3, -2 };
        int n = 0;
        ArrayList<int[]> temp_ans = new ArrayList<>();
        int index=0;

        for (int i = 0; i < arr.length; i++) {
            for (int j = i+1; j < arr.length; j++) {
                if((arr[i]+arr[j])==n){
                    int[] temp = new int[2];
                     temp[0] = Integer.min(arr[i], arr[j]);
                     temp[1] = Integer.max(arr[i], arr[j]);
                     temp_ans.add(temp);
                     index++;
                }
            }
        }
        int[][] ans  = new int[index][2];
        index =0;
        for (int i[] : temp_ans) {
            ans[index]=i;
            index++;
        }
        // for(int i=0;i<index;i++){
        //     for(int j=0; j<ans.length; j++){
        //          Arrays.sort(ans[i]);
                 
        //     }
        // }
        Arrays.sort(ans, new java.util.Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                return Integer.compare(a[0], b[0]);
            }
        });
        System.out.println(Arrays.deepToString(ans));

    }
}
