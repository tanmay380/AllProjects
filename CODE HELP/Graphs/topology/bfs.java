import java.util.LinkedList;
import java.util.Queue;

public class bfs {
    public static void main(String[] args) {
    bf1s();    
    }

    private static void bf1s() {
        int[] arr = new int[6];
        Queue<Integer> q = new LinkedList<>();
        
        for (int i = 0; i <=5; i++) {
            if(arr[i]==0){
                q.add(i);
            }}
                while (!q.isEmpty()) {
                    int head= q.poll();
                    System.out.println(head);
                    for (Integer it : adj.get(head)) {
                        arr[it]-=1;
                        if(arr[it]==0)
                            q.add(it);
                    }
                    
                }
            
        }
    
    }

    
}
