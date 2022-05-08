import java.util.ArrayList;
import java.util.HashSet;

class Pair {
    int a;
    int b;

    Pair(int a, int b) {
        this.a = a;
        this.b = b;
    }
}

public class travellingsalesman {
    static   int ans1 = Integer.MAX_VALUE;
    public static void main(String[] args) {
        int[][] cost = {{0,1000,5000},{5000,0,1000},
        {1000,5000,0}};

        if (cost.length <= 1)
            System.out.println(0);

        ArrayList<ArrayList<Pair>> adj = new ArrayList<>();
        for (int i = 0; i < cost.length; i++) {
            adj.add(new ArrayList<Pair>());
        }
        for (int i = 0; i < cost.length; i++) {
            for (int j = 0; j < cost[i].length; j++) {
                adj.get(i).add(new Pair(j, cost[i][j]));
            }
        }
      
        HashSet<Integer> set = new HashSet<>();
         getDistance(adj, 0, 0, false, cost.length, ans1, set);
        System.out.println(ans1);

    }

    private static void getDistance(ArrayList<ArrayList<Pair>> adj, int node, int temp, boolean b, int n, int ans,
            HashSet<Integer> set) {
        System.out.println("function " + ans);
        System.out.println(set);
        if (set.size() == n) {
            System.out.println("temp:- " + temp +  "  " + ans);
            ans1 = Integer.min(ans, temp);
            System.out.println(ans1);
        }
        set.add(node);
        if (node == 0 && b == false) {
            set.remove(node);
            b = true;
        }
        for (Pair i : adj.get(node)) {
            int next = i.a;
            int w = i.b;

            if (set.size() < n - 1 && next == 0) {
                continue;
            }
            if (!set.contains(next)) {
                System.out.println("in");
                temp += w;
                set.add(next);
                getDistance(adj, next, temp, b, n, ans1, set);
                set.remove(next);
                temp -= w;
            }
        
        }
        System.out.println("enfing asn "  + ans + " " + temp);

    }
}
