import java.util.Scanner;

public class password {
    public static void main(String[] args) {
            String pass="tanmay";
            Scanner input=new Scanner(System.in);
        String check="";
        System.out.println(check.length()+ " "+pass.length());
        while (check.length()!=pass.length()){
             check=input.next();
            System.out.println("hllo");
        }
    }
}
