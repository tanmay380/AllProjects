public class catchfish {
    public static void main(String[] args) {
        int[] arr = { 1, 0 ,0,1};
        int n = arr.length;
        int k = 2;

        int  min = 1000000000, curfish = 0;

        for (int i = 0; i < arr.length; i++) {
            int j = i; curfish=0;
            while (curfish!=k && j<n) {
                if(arr[j]==1){
                    curfish++;
                }
                j++;                
            }
            if(curfish==k){
                min=Math.min(min, j-i);
            }            
        }
        if(min==1000000000)
            min=-1;
        System.out.println(min);
    }
}
