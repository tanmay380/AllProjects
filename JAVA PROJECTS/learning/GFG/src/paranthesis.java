import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class paranthesis {
    public static void main(String[] args) throws IOException {
        String string = "";
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            String[] strings = br.readLine().trim().split("\\.");
            for (int i = strings.length - 1; i >= 0; i--) {
                string += strings[i];
                if (i != 0) {
                    string += ".";
                }
                
            }
            string+="\n";
        }
        System.out.println(string);
    }

}


