
import java.io.*;
import java.util.*;
class Geeks3{

    // Function to insert element into the queue
    static void insert(PriorityQueue<Integer> q, int k){

        q.add(k);
    }

    // Function to find an element k
    static boolean find(PriorityQueue<Integer> q, int k){

        boolean t=false;
        if(q.contains(k)){
            t=true;
        }
        return t;
    }

    // Function to delete the max element from queue
    static int delete(PriorityQueue<Integer> q){
        return q.poll();
    }

}

class GFG3 {

    // Driver code
    public static void main (String[] args) {

        // Taking input using scanner class
        Scanner sc = new Scanner(System.in);
        int testcase = sc.nextInt();

        while(testcase-- > 0){

            // Priority Queue with comparator
            PriorityQueue<Integer> p_queue = new PriorityQueue<Integer>(new Comparator<Integer>() {

                public int compare(Integer w1, Integer w2) {
                    return w2.compareTo(w1);
                }
            });

            int n = sc.nextInt();

            // Invoking object of Geeks class
            Geeks3 obj = new Geeks3();

            for(int i = 0;i<n;i++){
                int k = sc.nextInt();
                obj.insert(p_queue, k);
            }

            int x = sc.nextInt();
            for(int i = 0;i<x;i++){
                int k = sc.nextInt();
                boolean f = obj.find(p_queue, k);
                if(f != false){
                    System.out.println("1");
                    System.out.println(obj.delete(p_queue));
                }
                else{
                    System.out.println("-1");
                }
            }
        }
    }
}  // } Driver Code Ends