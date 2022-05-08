public class binarysearch {
    public static void main(String[] args) {
        int [] arr = {1,2,3,4,5,6};
        int key=6;
        int mid = (0+arr.length-1)/2;
        System.out.println(search(arr,key,0,mid,arr.length-1));
    }

    private static int search(int[] arr, int key, int s, int mid, int e) {
        if(s>e)
            return -1;
        if(arr[mid]==key){
            
            return mid;
        }
        if(arr[mid]<key)
            s=mid+1;
        else 
            e=mid-1;
        
        return search(arr, key, s, (s+e)/2, e);       

    }
}
