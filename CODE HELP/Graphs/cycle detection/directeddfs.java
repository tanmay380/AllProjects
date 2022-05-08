import java.util.ArrayList;

public class directeddfs {
    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> adj =new ArrayList<>();
        for (int i = 0; i <=9; i++) {
            adj.add(new ArrayList<>());
        }
        adj.get(1).add(2);
        adj.get(2).add(3);
        adj.get(3).add(4);
        adj.get(3).add(6);
        adj.get(4).add(5);
        adj.get(6).add(5);
        adj.get(7).add(2);
        adj.get(7).add(8);
        adj.get(8).add(9);
        adj.get(9).add(7);

        dfs(adj,9);
    }

    private static void dfs(ArrayList<ArrayList<Integer>> adj, int i) {
    }
}
