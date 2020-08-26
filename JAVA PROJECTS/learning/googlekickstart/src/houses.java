import javax.swing.plaf.IconUIResource;
import java.util.Arrays;
import java.util.Scanner;

public class houses {
    public static void main(String[] args) {
        double length=8;
        int floor= (int) Math.floor(Math.sqrt(length));
        int ceil= (int) Math.ceil(Math.sqrt(length));
        System.out.println(floor+" "+ceil);
    }
}
