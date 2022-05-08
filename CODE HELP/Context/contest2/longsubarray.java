public class longsubarray {
    public static void main(String[] args) {
        int[] arr = {1,2,3};
        int q = 1;

        int ans = arr[0], count = 0;

        for (int i = 0; i < arr.length; i++) {
            ans = arr[i] & ans;
            System.out.println(ans);
            if (ans >= q) {
                ++count;
            } else {
                break;
            }
        }
        System.out.println(count);
    }
}
