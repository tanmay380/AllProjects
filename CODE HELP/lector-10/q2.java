
public class q2 {
    public static void main(String[] args) {
        int[] a= {1,2,3,4,3,2,1};

        int ans =0;
         
        for (int i = 0; i < a.length; i++) {
            
            System.out.println(ans +" " + a[i] );
            ans=ans^a[i];
        }
        System.out.println(ans);
    }
}
