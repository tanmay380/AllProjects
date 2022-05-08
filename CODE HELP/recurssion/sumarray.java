public class sumarray {
    public static void main(String[] args) {
        int[] arr = {1,2,3,4};
        System.out.println(sum(arr, arr.length));
    }

    private static int sum(int[] arr, int size) {
        if (size == 0) {
            return 0;
        }
        if (size == 1) {
            return arr[0];
        }
        int ans = arr[size-1] + sum(arr, size - 1);

        return ans;
    }
}
