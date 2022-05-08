
public class binarysearchPaintersProblem{
    public static void main(String[] args) {
        int[] arr= {40,20,30,10};
        int painters= 2;

        System.out.println(binarySearch(arr, painters));

    }

    public static int binarySearch(int[] arr, int painters) {
        int s=0;

        int sum =0;
        for (int i = 0; i < arr.length; i++) {
                sum+=arr[i];
        }
        int e=sum;
        int ans = -1;
        int mid = (s+e)/2;

        while(s<=e){
            if(isPossible(arr, painters, mid)){
                ans=mid;
                e=mid-1;
            }else{
                s=mid+1;
            }
            mid= (s+e)/2;
        }
        return ans;        
    }

    public static boolean isPossible(int[] arr, int painters, int mid) {
        int painter=1;
        int timesum=0;
    
        for (int i = 0; i < arr.length; i++) {
            if(timesum+arr[i]<=mid){
                timesum+=arr[i];
            }else{
                painter++;
                if(painter > painters || arr[i]>mid) {
                    return false;
                }
                timesum=arr[i];
            }
        }
        return true;
    }
}