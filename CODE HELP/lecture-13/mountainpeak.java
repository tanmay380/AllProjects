public class mountainpeak {
    public static void main(String[] args) {
        int[] arr = { 1, 2, 3, 4, 5, 4 };
        int n = arr.length;
        int i = 0, j = n - 1;
        int mid = (i + j) / 2;

        while (i <= j) {
            if(arr[mid]<arr[mid+1]){
                i = mid+1;
            }else if(arr[mid]>arr[mid+1])
                {
                    j =mid-1;
                }
            if(arr[mid-1]<arr[mid] && arr[mid]>arr[mid+1]){
                System.out.println(mid + " " + arr[mid]);
            }

            mid = (i + j) / 2;
        }

    }
}
