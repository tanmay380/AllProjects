import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

public class couple {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int test = Integer.parseInt(bf.readLine());
        for (int i = 0; i < test; i++) {
            String[] input = new String[2];
            input = bf.readLine().split(" ");

            int a = Integer.parseInt(input[0]);
            int b = Integer.parseInt(input[1]);

            int lcm = (a * b) / gcd(a, b);
            HashSet<Integer> set = new HashSet<>();
            int count=0;
            for (int j = 2; j < lcm; j++) {
                while (lcm % j == 0) {
                    count++;
                    lcm = lcm / j;
                }
                if (lcm > 2)
                    count++;

            }
            System.out.println(set);
            if (isPrime(count)) {
                System.out.println("Yes");
            } else {
                System.out.println("No");
            }
        }
    }

    private static boolean isPrime(int size) {
        Boolean prime[] = new  Boolean[size+1];
        for (int i = 2; i < prime.length; i++) {
            prime[i]=true;
        }
        prime[0]=prime[1]=false;
        for (int i = 2; i < prime.length; i++) {
            if(prime[i]==true){
                for (int j = 2*i; j < prime.length; j+=i) {
                    prime[j]=false;
                }
            }
        }
        return prime[size];


    }

    private static int gcd(int a, int b) {
        if (b != 0) {
            return gcd(b, a % b);
        } else
            return a;
    }
}
