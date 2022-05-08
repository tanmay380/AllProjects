
import java.util.Scanner;

public class wokmetere {
    public static void main(String[] args) {
        
        Scanner sc= new Scanner(System.in);
        int size=sc.nextInt();
        long m= sc.nextInt();


        long[] arr = new long[size];

        for (int i = 0; i <size; i++) {
            arr[i]=sc.nextInt();
        }
        sc.close();
        long ans=-1;
        long start=Integer.MAX_VALUE,end=0;
        
        for (int i = 0; i < arr.length; i++) {
            end+=arr[i];
        }

        long mid = (start+end)/2;
        while(start<=end){
            if(isPossible(arr, m , mid)){
                ans=mid;
                start=mid+1;
            }else{
                end=mid-1;
            }
            mid = (start+end)/2;
        }
        System.out.println(ans);

    }

    private static boolean isPossible(long[] arr, long m, long mid) {
        long curheght=0;

        for(int i=0;i<arr.length;i++){
            if(arr[i]>mid){
                curheght+=(arr[i]-mid);
            }
        }
        
        if(curheght>=m){
            return true;
        }
        else 
            return false;
    }
}
