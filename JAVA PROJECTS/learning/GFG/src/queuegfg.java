
import java.util.*;

class Geeks2{

    static void insert(Queue<Integer> q, int k){

        q.add(k);

    }
    static int findFrequency(Queue<Integer> q, int k){
        int fre=Collections.frequency(q,k);
        return fre;

    }

}

class GFG2 {

    // Driver code
    public static void main (String[] args) {

        // Taking input using scanner class
        Scanner sc = new Scanner(System.in);
        int testcase = sc.nextInt();

        while(testcase-- > 0){
            // Declaring Queue
            Queue<Integer> q = new LinkedList<>();
            int n = sc.nextInt();

            // Invoking object of Geeks class
            Geeks2 obj = new Geeks2();

            for(int i = 0;i<n;i++){
                int k = sc.nextInt();
                obj.insert(q, k);
            }

            int x = sc.nextInt();
            for(int i = 0;i<x;i++){
                int k = sc.nextInt();
                int f = obj.findFrequency(q, k);
                if(f != 0){
                    System.out.println(f);
                }
                else{
                    System.out.println("-1");
                }
            }
        }
    }
}  // } Driver Code Ends