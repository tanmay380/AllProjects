import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class lab1antakshari {
    public static boolean checkinlist(String word) throws IOException {
        String words = " ";

        BufferedReader file = new BufferedReader(new FileReader("lab\\src\\pthondictionary.txt"));

        while (words != null) {
            try {

            words = file.readLine();
            if (words.equals(word)) {
                return true;
            }
        }catch (Exception e) {
                return false;
            }
    }
        file.close();
        return false;
}


    private static String computerchoice(char lastletter) throws IOException {

        BufferedReader file = new BufferedReader(new FileReader("lab\\src\\pthondictionary.txt"));
        FileWriter fileWriter = new FileWriter("lab\\src\\output.txt");
        String ch = " ";
        while (ch != null) {//writing the words from last letter of the words from user to a new file
            try {
                ch = file.readLine();
                if (ch.charAt(0) == lastletter) {
                    fileWriter.append(ch + "\n");
                }
            } catch (Exception e) {
                break;
            }
        }
        file.close();
        fileWriter.close();

        BufferedReader fileReader = new BufferedReader(new FileReader("lab\\src\\output.txt"));
        List<String> words = new ArrayList<>();

        ch = " ";
        String word = " ";
        while (ch != null) {
            ch = fileReader.readLine();
            words.add(ch);
        }
        fileReader.close();
        boolean filedelete=new File("lab\\src\\output.txt").delete();
        Random random = new Random();
        word = words.get(random.nextInt(words.size()));

        return word;
    }


    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);
        boolean inlist;
        ArrayList<String> usedwords = new ArrayList<>();
        int i = 1,userlength=0,complength=0;
        char userlastletter;
        BufferedReader fr = null;
        System.out.print("How many rounds you want to play-> ");
        int rounds = input.nextInt();
        char lastletter = '0';
        while (i <=rounds) {
            System.out.print("Enter the word for round " + i + "-> ");
            String words = input.next().toLowerCase();
            if (i != 1) {

                if (words.charAt(0) != lastletter) {
                    System.out.println("The letter should start form " + lastletter);
                    continue;
                }
            }
            inlist = checkinlist(words);
            if (inlist) {
                userlastletter = words.charAt(words.length() - 1);
                if (usedwords.contains(words)) {
                    System.out.println("Word already used. Please use different");
                    continue;
                }
                userlength+=words.length();
                usedwords.add(words);
                String computerlastword = computerchoice(userlastletter);
                System.out.println("The word of computer is-> " + computerlastword);
                usedwords.add(computerlastword);
                complength+=computerlastword.length();
                lastletter = computerlastword.charAt(computerlastword.length() - 1);
                if (i!=rounds)
                    System.out.println("The word should start from " + lastletter);
            } else {
                System.out.println("Word not in list");
                continue;
            }
            i++;
        }
        if (userlength > complength) {
            System.out.println("You win");
        } else if (userlength < complength) {
            System.out.println("Computer wins");
        } else {
            System.out.println("Draw");
        }
    }
}
