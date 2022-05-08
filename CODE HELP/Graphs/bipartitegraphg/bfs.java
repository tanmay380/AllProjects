import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class bfs {
    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i <= 8; i++) {
            adj.add(new ArrayList<>());
        }

        adj.get(1).add(2);
        adj.get(2).add(1);

        adj.get(2).add(3);
        adj.get(3).add(2);

        adj.get(2).add(7);
        adj.get(7).add(2);

        adj.get(3).add(4);
        adj.get(4).add(3);

        adj.get(4).add(6);
        adj.get(6).add(4);

        adj.get(5).add(6);
        adj.get(6).add(5);
        adj.get(5).add(8);
        adj.get(8).add(5);

        adj.get(6).add(7);
        adj.get(7).add(8);


        System.out.println(biparttie(adj, 8));
    }

    private static boolean biparttie(ArrayList<ArrayList<Integer>> adj, int i) {

        int[] coulr = new int[i + 1];
        Queue<Integer> q = new LinkedList<>();
        for (int j = 0; j < coulr.length; j++) {
            coulr[j] = -1;
        }
        q.add(1);
        coulr[1] = 0;
        while (!q.isEmpty()) {
            int head = q.poll();
            for (Integer it : adj.get(head)) {
                if (coulr[it] == -1) {
                    q.add(it);
                    coulr[it] = 1-coulr[head];
                }
                else if(coulr[it]==coulr[head]) return false;
            }
        }

        return true;
    }
}
