public class paintersPartition {
    public static void main(String[] args) {
        int[] arr = { 48 ,90 };
        int painter = 2;
        int start = 0, end = 0;
        for (int i = 0; i < arr.length; i++) {
            end += arr[i];
        }
        int ans = -1;
        int mid = (start + end) / 2;
        while (start <= end) {
            if (isPossible(arr, arr.length, mid, painter)) {
                ans = mid;
                end = mid - 1;
            } else
                start = mid + 1;

            mid = (start + end) / 2;
        }
        System.out.println(ans);
    }

    private static boolean isPossible(int[] arr, int length, int mid, int painter) {
        int curPainter = 1;
        int cursum = 0;
        for (int i = 0; i < arr.length; i++) {
            if (cursum + arr[i] <= mid) {
                cursum += arr[i];
            } else {
                curPainter++;
                if (curPainter > painter || arr[i] > mid)
                    return false;
                cursum=arr[i];
            }
        }

        return true;
    }
}
