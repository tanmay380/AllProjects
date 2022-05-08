public class compliment{
    public static void main(String[] args) {
        int n=7;
        int i=0;
        int ans=0;
        while(n!=0){
            int bit = n&1;

            if(bit==1)
                bit=0;
            else 
                bit=1;
            
            if(bit==1){
                ans = ans +(int) Math.pow(2, i);
            }

            n=n>>1;
            i++;
        }
        System.out.println(ans);
    }
}