import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class undirectedgraphbfs {

    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();

        for (int i = 0; i <= 11; i++) {
            adj.add(new ArrayList<>());
        }

        adj.get(1).add(2);
        adj.get(2).add(1);

        adj.get(2).add(4);
        adj.get(4).add(2);

        adj.get(3).add(5);
        adj.get(5).add(3);

        adj.get(6).add(5);
        adj.get(5).add(6);

        adj.get(5).add(10);
        adj.get(10).add(5);

        adj.get(10).add(9);
        adj.get(9).add(10);

        adj.get(6).add(7);
        adj.get(7).add(6);

        adj.get(9).add(8);
        adj.get(8).add(9);

        adj.get(8).add(7);
        adj.get(7).add(8);

        adj.get(8).add(11);
        adj.get(11).add(8);


        for (int i = 1; i <=11; i++) {
            System.out.print(i + "- " );
             for (int j = 0; j <adj.get(i).size(); j++) {
                 System.out.print(  adj.get(i).get(j) + " ");
             }
             System.out.println();
         }

        System.out.println(detectCycle(11, adj));

    }

    private static boolean detectCycle(int i, ArrayList<ArrayList<Integer>> adj) {
        boolean[] visited = new boolean[i + 1];

        for (int j = 1; j <= i; j++) {
            if (visited[j] == false) {
                if (isCyclic(adj, visited, j))
                    return true;
            }
        }

        return false;
    }

    private static boolean isCyclic(ArrayList<ArrayList<Integer>> adj, boolean[] visited, int j) {
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(j, -1));
        visited[j] = true;

        while (!q.isEmpty()) {
            int node = q.peek().first;
            int prev = q.peek().second;
            q.poll();


            for (Integer i : adj.get(node)) {
                if (visited[i] == false) {
                    q.add(new Node(i, node));
                    visited[i] = true;
                } else if (prev != i)
                    return true;
            }
        }

        return false;
    }
}

class Node {
    int first;
    int second;

    Node(int a, int b) {
        first = a;
        second = b;
    }
}
