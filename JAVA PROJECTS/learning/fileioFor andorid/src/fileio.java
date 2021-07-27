import java.awt.*;
import java.io.*;
import java.nio.Buffer;
import java.util.Arrays;

class Country {
    String name;
    String code;

    public Country(String name, String code) {
        this.name = name;
        this.code = code;
    }
}

public class fileio {
    private static final Country[] country = new Country[269];

    public static void main(String[] args) throws Exception {
        FileInputStream file = new FileInputStream("src/countrycode.txt");
        OpenFIle(file);

    }


    static void OpenFIle(FileInputStream file) throws Exception {
        DataInputStream bf = new DataInputStream(file);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(bf));
        String st;
        int i =0;
//        System.out.println(bufferedReader.lines().toString());
        while ((st = bufferedReader.readLine()) != null) {

//            System.out.println(st.length());
            if (st.length() > 2) {
//                    System.out.println(st);
                String token[] = st.split("[+]");
//                System.out.println(Arrays.toString(token));
                if (token.length > 1) {
                    country[i] =new Country(token[0], token[1]);
                    i++;
                }
            }
        }

        System.out.println(country[2].name );
    }
}
