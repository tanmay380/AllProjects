import java.util.ArrayList;
import java.util.Arrays;

public class reversearray {
    public static void main(String[] args) {
        int[] arr1 = { 10 ,9 ,8, 6 ,7 };
        int m = 2;

        int i = m + 1, j = arr1.length - 1;

        ArrayList<Integer> arr = new ArrayList<>();
        arr.add(10);
        arr.add(9);
        arr.add(8);
        arr.add(6);
        arr.add(7);
        while (i < j) {
            int a= arr.get(i);			
            int b= arr.get(j);
            a=a^b;
            b=a^b;
            a=a^b;
            arr.set(i, a);            
            arr.set(j, b);
            i++;
            j--;
        }
        System.out.println(arr);
    }
}
