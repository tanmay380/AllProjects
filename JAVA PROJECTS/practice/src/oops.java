public class oops{
    public static void main(String[] args) {
        int[] arr={1,2,3,4,5,6,7,8,9};
        int r= arr.length-1;
        int search= 90;
        System.out.println(BinarySearch(arr,0,r,search));
    }

    static int BinarySearch(int[] arr, int l, int  r, int key){
        if(r>=l) {
            int mid = (l + r-1) / 2;
            if (arr[mid] == key)
                return mid;
            if (arr[mid] < key) {
                BinarySearch(arr, l, mid - 1, key);
            } else
                BinarySearch(arr, mid + 1, r, key);
        }
        return -1;

    }

}