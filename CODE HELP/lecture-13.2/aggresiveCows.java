import java.util.Arrays;

public class aggresiveCows {
    public static void main(String[] args) {
        int[] arr= {0,4,3,7,10,9};
        int n =arr.length;
        int k = 3;
        
        
        Arrays.sort(arr);

        int start=0;
        int end=arr[0];
        

        for (int i = 0; i < arr.length; i++) {
            if(arr[i]>end){
                end=arr[i];
            }           
        }
        int ans=-1;

        int mid=(start+end)/2;
        while(start<=end){
                if(isPossible(arr,k,mid)){
                    ans=mid; 
                    start=mid+1;
                }else{
                    end=mid-1;
                }
                mid=(start+end)/2;

        }
        System.out.println(ans);
    }

    private static boolean isPossible(int[] arr, int k, int mid) {
        int cowCount=1;
        int lastPos=arr[0];
        for (int i = 0; i < arr.length; i++) {
            if(arr[i]-lastPos>=mid){
                cowCount++;
                if(cowCount==k)
                    return true;
                lastPos=arr[i];
            }
        }

        return false;
    }
}
