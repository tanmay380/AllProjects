import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class GFG {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        while (t-->0){
            int n = Integer.parseInt(br.readLine());
            int[] arr = new int[n];
            String inputLine[] = br.readLine().trim().split(" ");
            for (int q = 0; q < n; q++) {
                arr[q] = Integer.parseInt(inputLine[q]);
            }

            StringBuffer str = new StringBuffer();

            ArrayList<Integer> res=new ArrayList<Integer>();
            int max=arr[n-1];
            res.add (max);
            for (int j = n - 2; j >= 0; j--) {
                if (max <=arr[j]) {
                    max = arr[j];
                    res.add(max);
                }
            }
            for(int i=res.size()-1; i>=0; i--){
                str.append(res.get(i)+" ");
            }
            System.out.println(str);
        }
    }
}