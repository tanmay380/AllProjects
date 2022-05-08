package day4;

public class power {
    public static void main(String[] args) {        
    long a=3;
    long b=20;
    
    System.out.println(power(a,b));
    }


    private static long power(long a, long b) {
        if(b==0)
            return 1;
        if(b==1)
            return a;        
        
        long ans= power(a,b/2 );

        if(b%2==0){
            return ans*ans;
        }
        else{
            return a*ans*ans;
        }
    }
}
