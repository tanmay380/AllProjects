import java.ma;

public class multiplelength {
    public static void main(String[] args) {
        int[] arr = { 12, 14, 14, 12, 11, 15, 10, 16, 20, 11, 18, 10, 20, 19, 10, 14 };
        doublereducesu product = 0;
        for (int i = 0; i < arr.length; i++) {
            double number= log2(arr[i]);
            product +=(double) number ;
            System.out.println(number);
        }

        int ans = (int) product;
        System.out.println(ans+1);
        int count = 0;
        System.out.println(count);
    }

    private static double log2(int x) {
        return (Math.log(x) / Math.log(2));
    }
}
