public class binarysearchPivot {
    public static void main(String[] args) {
        int[] arr = { 7, 8, 9, 10, 1, 2, 3, 4 };
        System.out.println(findPositions(arr));

    }
    private static int findPositions(int[] arr) {
        int pivot= getPivot(arr);
        int n=arr.length;
        int key=2;

        if(key>=arr[pivot] && key<arr[n-1])
        {
            return binarySearch(arr, pivot, n-1, key);
        }
        else{
        
            return binarySearch(arr, 0, pivot-1, key);
        }
    }
    private static int binarySearch(int[] arr, int start, int end, int key) {
        int s=start;
        int e=end;

        int mid = (s+e)/2;

        while(s<e){
            if(arr[mid]==key){
                return mid;
            }
            else if(key>arr[mid]){
                s=mid+1;
            }else
                e=mid-1;
            mid=(s+e)/2;
        }
    
        return -1;
    }

    private static int getPivot(int[] arr) {
        int s = 0;
        int e = arr.length;
        int mid = (s + e) / 2;
        while (s < e) {
            if (arr[mid] >= arr[0]) {
                s = mid + 1;
            } else
                e = mid;
            mid = (s + e) / 2;
        }
        return s;
    }

}
