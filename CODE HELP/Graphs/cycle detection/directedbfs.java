import java.util.ArrayList;

public class directedbfs {
    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();

        for (int i = 0; i <=4; i++) {
            adj.add(new ArrayList<>());
        }

        adj.get(1).add(2);
        adj.get(2).add(3);
        adj.get(3).add(4);
        adj.get(4).add(2);

        bfs(adj, 4);
    }

    private static void bfs(ArrayList<ArrayList<Integer>> adj, int i) {
           
    }
}
