import java.util.Scanner;

public class binarysearchSQRT {
    public static void main(String[] args) {
    
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number you want the sr of:-> ");
        int input= sc.nextInt();
        sc.close();

        System.out.println("The sruare root is = "+ sqrt(input));
    }
    public static int  sqrt(int n) {
        int s=0;
        int e=n;
        
        int mid= (s+e)/2;
        int ans=-1;
        while(s<e){
            
            if(mid*mid==n){
                return mid;
            }
            if(mid*mid<n){
                ans=mid;
                s=mid+1;
            }
            else{
                e=mid-1;
            }

         mid= (s+e)/2;
        }
        return ans;
        
    }
}
