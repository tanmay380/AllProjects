import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.spec.RSAOtherPrimeInfo;
import java.util.Scanner;
import java.util.concurrent.ScheduledExecutorService;

public class fileio1 {
    public static void main(String[] args) throws FileNotFoundException {
        String see1;
        File myfile = new File("D:\\Users\\JAVA PROJECTS\\learning\\fileoi\\u1.txt");
        if (myfile.length()>0) {
            System.out.println("File is not empty");
            System.out.println("Would ypu like to see its ontent");
            Scanner see=new Scanner(System.in);
            see1=see.nextLine();
            if(see1.equals("yes")){
                Scanner myreader=new Scanner(myfile);
                while (myreader.hasNextLine()){
                    see1=myreader.nextLine();
                    System.out.println(see1);
                }
            }
        }else{
            System.out.println("File is Empty");
        }
    }}




