public class BinarySearch {
    public static void main(String[] args) {
//        System.out.println(Binarysearch());
        System.out.println(Infintiesearch());
    }

    private static boolean Infintiesearch() {
        int[] arr= new int[10000];//[1,2,3,4,5,6,7,8,9,10,11,1,2,12,1,21,21,212]

        for (int i=0;i<10000;i++)
            arr[i]=i*3;


        int k= 10;

        int low=0, high=1;
        while (arr[high]<k){
            low=high;
            high=2*high;
        }

        return Binarysearch(arr,k,low,high);
    }

    private static boolean Binarysearch(int[] arr, int k, int low, int high) {


        while (low<=high){
            int mid = (low+high)/2;
            if (arr[mid]==k){
                return true;
            }
            if (k<arr[mid]){
                high=mid-1;
            }
            if (k>arr[mid]){
                low=mid+1;
            }
        }
        return false;
    }
}
