import java.util.Scanner;

public class wormhole {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        int graph[][] = new int[100][100];
        while (t-- > 0) {
            int n = sc.nextInt();
            int x[] = new int[2 * n + 2];
            int y[] = new int[2 * n + 2];

            for (int i = 0; i < y.length; i++) {
                for (int j = 0; j < y.length; j++) {
                        graph[i][j]=-1;
                }
            }

            for (int i = 0; i < 2; i++) {
                x[i] = sc.nextInt();
                y[i] = sc.nextInt();
            }

            for (int i = 2; i < 2 * n + 2; i += 2) {
                x[i] = sc.nextInt();
                y[i] = sc.nextInt();
                x[i + 1] = sc.nextInt();
                y[i + 1] = sc.nextInt();
                int cost = sc.nextInt();
                graph[i][i + 1] = cost;
                graph[i + 1][i] = cost;
            }


            for (int i = 0; i < 2*n+2; i++) {
                for (int j = i+1; j < 2*n+2; j++) {
                    if(i>=2 && i%2==0) continue;
                    graph[i][j]= Math.abs((x[i]-x[j]))+Math.abs((y[i]-y[j]));
                    graph[j][i]= Math.abs((x[i]-x[j]))+Math.abs((y[i]-y[j]));
                }
            }
            System.out.println();

            for (int i = 0; i < 2*n+2; i++) {
                for (int j = 0; j < 2*n+2; j++) {
                    System.out.print(graph[i][j] + " | ");
                }
                System.out.println();
            }
            dijkstra(graph, n);
        }
    }

    private static void dijkstra(int[][] graph, int n) {
        int visited[]= new int[2*n+2];
        int distance[] = new int[2*n+2];
        for (int i = 0; i < distance.length; i++) {
            visited[i]=0;
            distance[i]=Integer.MAX_VALUE;           
        }
        distance[0] = 0;
        for (int i = 0; i < distance.length-1; i++) {
            int u = findmin(visited, distance);
            visited[u]=1;
            for (int j = 0; j < distance.length; j++) {
                if(distance[u]!=Integer.MAX_VALUE && graph[u][v]!=-1 && graph[u][v]+distance[u]<distance[v]){

                }
            }
        }
    }
}
