import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class mrkim {

    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        int n = sc.nextInt();
        
        int[][] location = new int[n+2][2];
        int[][] graph= new int[n+2][n+2];

        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph.length; j++) {
                graph[i][j]=0;
            }
        }
            
        for (int i = 0; i < n+2; i++) {
            location[i][0]=sc.nextInt();
            location[i][1]=sc.nextInt();
        }   
        sc.close();

        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph.length; j++) {
                if((i==0 && j==1) || (i==1 && j==0)) continue;
                graph[i][j]=Math.abs(location[i][0]-location[j][0]) + Math.abs(location[i][1]-location[j][1]);
                // graph[j][i]=Math.abs(location[i][0]-location[j][0]) + Math.abs(location[i][1]-location[j][1]);
            }
        }
        System.out.println();
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph.length; j++) {
                System.out.print(graph[i][j] + " ");
            }
            System.out.println();
        }
        shortestPath(0,graph, n+2);
    }

    private static void shortestPath(int s, int[][] graph, int N) {
        int dist[] = new int[N];
        boolean visited[] = new boolean[N];

        for (int i = 0; i < dist.length; i++) {
            dist[i]=1000000;
        }
        dist[s]=0;

        for (int i = 0; i < visited.length; i++) {
            int minV = findMin(dist, visited);
            visited[minV]= true;

            for (int j = 0; j < visited.length; j++) {
               
                if(graph[minV][j]!=0 && visited[j]==false && dist[minV]!=1000000){
                    int newDist = dist[minV] + graph[minV][j];
                    if(newDist < dist[j])
                        dist[j]=newDist;
                }
            }
        }    
        System.out.println();  
        for (int i = 0; i < visited.length; i++) {
            System.out.print(visited[i] + " ");
            System.out.print(dist[i]+ " ");
        }  
    }

    private static int findMin(int[] dist, boolean[] visited) {
        int minV=-1;
        for (int i = 0; i < dist.length; i++) {
            if(visited[i]==false && (minV == -1 || dist[i]<dist[minV])){
                minV=i;
            }
        }
        return minV;
    }
}


    
