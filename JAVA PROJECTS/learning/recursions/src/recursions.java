import java.util.Scanner;

public class recursions {
    public static void main(String[] args) {
       Scanner sc= new Scanner(System.in);

       int number = sc.nextInt();

       recu(number);
    }
    static  void recu(int n){
        System.out.println("valiue of n "+ n);

        if (n<=0) {
            System.out.println("eh");
            return;
        }
        recu(n-1);

        System.out.println(n);
    }
}
