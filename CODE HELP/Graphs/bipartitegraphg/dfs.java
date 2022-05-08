import java.util.ArrayList;

public class dfs {
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

        System.out.println(isBipertite(8,adj));
    }

    private static boolean isBipertite(int i, ArrayList<ArrayList<Integer>> adj) {
        int arr[]= new int[i+1];
        for (int j = 0; j < arr.length; j++) {
            arr[j]=-1;
        }
        for (int j = 1; j <=i; j++) {
            if(arr[j]==-1){
                if(isBipertitedfs(j,adj,arr)){
                    return true;
                }
            }
        }

        return false;
    }

    private static boolean isBipertitedfs(int j, ArrayList<ArrayList<Integer>> adj, int[] arr) {
        if(arr[j]==-1) arr[j]=1;
        for (Integer it : adj.get(j)) {
            if(arr[it]==-1){
                arr[it]=1-arr[j];
                if(!isBipertitedfs(it, adj, arr)) return false;
            }else if(arr[j]==arr[it]) return false;
        }
        return true;
    }
}
