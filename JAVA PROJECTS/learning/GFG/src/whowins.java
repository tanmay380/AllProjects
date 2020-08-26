import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class whowins {

    // Complete the encryption function below.
    static String encryption(String s) {
        double length=s.length();
        int floor= (int) Math.floor(Math.sqrt(length));
        int ceil= (int) Math.ceil(Math.sqrt(length));
        if(floor*ceil<length){
            floor++;
        }


        return "hello";

    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String s = scanner.nextLine();

        String result = encryption(s);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
