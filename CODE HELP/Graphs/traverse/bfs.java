package traverse;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class bfs{
    public static void main(String[] args) {
         ArrayList<ArrayList<Integer>> adj = new ArrayList<>();

         for (int i = 0; i <=7; i++) {
             adj.add(new ArrayList<>());
         }
         adj.get(1).add(2);
         adj.get(2).add(1);

         adj.get(2).add(3);
         adj.get(3).add(2);

         adj.get(2).add(7);
         adj.get(7).add(2);

         adj.get(3).add(5);
         adj.get(5).add(3);

         adj.get(5).add(7);
         adj.get(7).add(5);

         adj.get(4).add(6);
         adj.get(6).add(4);

         for (int i = 1; i <=7; i++) {
            System.out.print(i + "- " );
             for (int j = 0; j <adj.get(i).size(); j++) {
                 System.out.print(  adj.get(i).get(j) + " ");
             }
             System.out.println();
         }

        System.out.println(bfs(7, adj));
        
    }

    private static ArrayList<Integer> bfs(int v,  ArrayList<ArrayList<Integer>> adj ) {
        ArrayList<Integer> bfs = new ArrayList<>();
        boolean vis[] = new boolean[v+1];

        for (int i = 1; i <=v; i++) {
            if(vis[i]==false){
                Queue<Integer> queue = new LinkedList<>();
                queue.add(i);
                vis[i]=true;

                while(!queue.isEmpty()){
                    Integer node = queue.poll();
                    bfs.add(node);

                    for (Integer integer : adj.get(node)) {
                        if(vis[integer]==false)
                            {
                                vis[integer]=true;
                                queue.add(integer);
                            }
                    }
                }
            }
        }
        return bfs;
    }

} 