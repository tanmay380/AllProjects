public class pivotbinarysearch {
    public static void main(String[] args) {
        int[] arr= {3,8,10,17,1};
        int n  = arr.length; 

        int i=0, j=n-1;
        int mid = (i+j)/2;
        while(i<j){
            if(arr[mid]>=arr[0])
                i=mid+1;
            else    
                j=mid;
            mid=(i+j)/2;
        }
        System.out.println(j);

    }
}
