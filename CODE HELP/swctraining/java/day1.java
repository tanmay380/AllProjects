import java.util.Arrays;
import java.util.Scanner;

public class day1 {
    public static void main(String[] args) {
        //MORNING
        // decimalToBinary();
        // binaryToDecimal();
        // decimalToBinary7();

        //AFTERNOON
        // selectionSort1();
        // fibonacci();
        // permutation();
        String path="";
        path+="d";
        path+="e";
        System.out.println(path);
    }

    private static void permutation() {
        int[] arr = {1,2,3};
        int size= arr.length;
    
        permRec(arr);
    }

    private static void permRec(int[] arr) {

    }

    private static void fibonacci() {
        int n = 10;
        for (int i = 0; i <= n; i++) {
            
            System.out.print (fibonacciRecursion(i)+" ");
        }

    }
    

    private static int fibonacciRecursion(int n) {
        if(n==1 || n==0){
        
            return n;
        }
        else    {
            int x= fibonacciRecursion(n-1) + fibonacciRecursion(n-2);
            
            return x;
        }
    }

    private static void selectionSort1() {
        int[] arr = {2,5,1,7,3,100,90,10,90,89,67,45,23,14};
        int size = arr.length;

        for (int i = 0; i < arr.length; i++) {
            int minIndes=i;
            for (int j = i+1; j < arr.length; j++) {
                if(arr[j]<arr[minIndes])
                    minIndes=j;
            }
            if(i!=minIndes){
                arr[i]=arr[i]^arr[minIndes];
                arr[minIndes]=arr[i]^arr[minIndes];
                arr[i]=arr[i]^arr[minIndes];
            }
        }

        System.out.println(Arrays.toString(arr));

    }

    private static void decimalToBinary7() {
        int[] arr = { 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 1, 0, 1,0,0,1 };
        int ans = 0;
        int cur = 0;
        for (int i = 0; i < arr.length; i++) {
            if (cur > 6) {
                cur = 0;
                System.out.println(ans);                
                ans=0;
            }
            ans += binaryToDecimal(arr[i], (6 - cur));
            System.out.println("ans is + " + ans + " " +  (6 - cur));
            cur++;
        }
        
        System.out.println(ans);

    }

    void decimalToBinary() {
        int number = 0;
        Scanner sc = new Scanner(System.in);
        number = sc.nextInt();
        sc.close();

        double ans = 0;
        int i = 0;

        while (number != 0) {
            int rem = number & 1;
            ans = (rem * Math.pow(10, i)) + ans;
            i++;

            number = number >> 1;
        }

        System.out.println(ans);
    }

    static int binaryToDecimal(int arr, int pos) {
        // int number;
        // Scanner sc= new Scanner(System.in);
        // number = sc.nextInt();
        // sc.close();

        int i = 0;
        double ans = 0;
        // while (size > 0) {
        //     // int rem = arr[size - 1] % 10;
        //     // number = number/10;

        //     if (rem == 1) {
        //         ans = (rem * Math.pow(2, pos)) + ans;
        //     }
        //     i++;
        // }
        // System.out.println(ans);
            if(arr==1){
            ans=(Math.pow(2, pos)*arr);
            }

        return (int) ans;
    }
}