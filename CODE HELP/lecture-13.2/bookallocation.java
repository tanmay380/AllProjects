public class bookallocation {
    public static void main(String[] args) {
        int[] arr = {2, 2 ,3 ,3 ,4 ,4 ,1 };
        int m = 3;
        int start=0, end =0;        
        for(int i=0; i<arr.length;i++){
            end+=arr[i];
        }
        int mid = (start+end)/2;
        long ans=-1;
        while(start<=end){            
            if(isPossible(arr, arr.length, m ,mid)){
                ans=mid;
                end=mid-1;
            }else{
                start= mid+1;
            }
            mid = (start+end)/2;
        }
        System.out.println(ans);
    }

    private static boolean isPossible(int[] arr, int length, int m, int mid) {
         int curstud=1;
         int sum=0;

            for (int i = 0; i < arr.length; i++) {
                    if(sum+arr[i]<=mid){
                        sum+=arr[i];
                    }else{ 
                        curstud++;
                        if(curstud>m || arr[i]>mid){
                            return false;
                        }
                        sum=arr[i];
                    }
            }

        return true;
    }   
}
