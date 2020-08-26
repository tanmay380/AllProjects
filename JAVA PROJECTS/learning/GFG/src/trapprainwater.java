import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class trapprainwater {
    public static void main(String[] agrs) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            int size=Integer.parseInt(br.readLine());
            int[] arr = new int[size];
            int w=0;
            String[] array=br.readLine().trim().split(" ");
            for (int i = 0; i < size; i++) {
                arr[i]=Integer.parseInt(array[i]);
            }
            for (int i = 0; i < size; i++) {
                if(arr[size-1]<arr[0])
                if (arr[0] > arr[i]) {
                    w += (arr[0] - arr[i]);
                }
            }
            System.out.println(w);
        }
    }
}