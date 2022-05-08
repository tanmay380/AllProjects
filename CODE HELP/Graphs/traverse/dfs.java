package traverse;
import java.util.ArrayList;

public class dfs {
    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i <=7; i++) {
            adj.add(new ArrayList<>());
        }
        adj.get(1).add(2);
        adj.get(2).add(1);

        adj.get(2).add(4);
        adj.get(2).add(7);

        adj.get(3).add(5);

        adj.get(4).add(2);
        adj.get(4).add(6);

        adj.get(5).add(3);
        adj.get(6).add(4);
        adj.get(6).add(7);
        adj.get(7).add(2);
        adj.get(7).add(6);

        for (int i = 1; i <=7; i++) {
            System.out.print(i + "- " );
             for (int j = 0; j <adj.get(i).size(); j++) {
                 System.out.print(  adj.get(i).get(j) + " ");
             }
             System.out.println();
         }


        System.out.println(dfs(7, adj));

    }

    private static ArrayList<Integer> dfs(int i, ArrayList<ArrayList<Integer>> adj) {
        ArrayList<Integer> ans = new ArrayList<>();
        boolean[] visited= new boolean[i+1];
        for (int j = 1; j <=i; j++) {
            if (visited[j]==false) {
                dfscall(j,visited,adj,ans);
            }
        }
        return ans;
    }

    private static void dfscall(int i, boolean[] visited, ArrayList<ArrayList<Integer>> adj, ArrayList<Integer> ans) {
        ans.add(i);
        System.out.println(i);
        visited[i]=true;
        for (Integer it : adj.get(i)) {
            if(visited[it]==false)
                dfscall(it, visited, adj, ans);
        }
    }
}
