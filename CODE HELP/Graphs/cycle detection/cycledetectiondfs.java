import java.util.ArrayList;

public class cycledetectiondfs {
    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();

        for (int i = 0; i <= 8; i++) {
            adj.add(new ArrayList<>());
        }
        adj.get(1).add(3);
        adj.get(3).add(1);

        adj.get(2).add(5);
        adj.get(5).add(2);

        adj.get(3).add(4);
        adj.get(4).add(3);

        adj.get(5).add(6);
        adj.get(6).add(5);
        // adj.get(5).add(8);
        // adj.get(8).add(5);

        adj.get(6).add(7);
        adj.get(7).add(6);
        adj.get(7).add(8);
        adj.get(8).add(7);

        
        // adj.get(1).add(2);
        // adj.get(2).add(1);

        // adj.get(2).add(4);
        // adj.get(4).add(2);

        // adj.get(3).add(5);
        // adj.get(5).add(3);

        // adj.get(6).add(5);
        // adj.get(5).add(6);

        // adj.get(5).add(10);
        // adj.get(10).add(5);

        // adj.get(10).add(9);
        // adj.get(9).add(10);

        // adj.get(6).add(7);
        // adj.get(7).add(6);

        // adj.get(9).add(8);
        // adj.get(8).add(9);

        // // adj.get(8).add(7);
        // // adj.get(7).add(8);

        // adj.get(8).add(11);
        // adj.get(11).add(8);


        for (int i = 1; i <= 8; i++) {
            System.out.print(i + "- ");
            for (int j = 0; j < adj.get(i).size(); j++) {
                System.out.print(adj.get(i).get(j) + " ");
            }
            System.out.println();
        }

        System.out.println(checkCyclic(adj, 8));

    }

    private static boolean checkCyclic(ArrayList<ArrayList<Integer>> adj, int i) {
        boolean[] visit = new boolean[i + 1];
        for (int j = 1; j < i + 1; j++) {
            if (visit[j] == false) {
                if (cycleDfs(j, adj, visit, -1)) {
                    return true;
                }
            }
        }

        return false;
    }

    private static boolean cycleDfs(int i, ArrayList<ArrayList<Integer>> adj, boolean[] visit, int j) {
        visit[i]=true;
        for (Integer node: adj.get(i)){
            // int node= adj.get(i).get(it);
            if(visit[node]==false){
                if(cycleDfs(node, adj, visit, i))
                    return true;
            }else if(j!=node) return true;
        }
        return false;
    }
}
