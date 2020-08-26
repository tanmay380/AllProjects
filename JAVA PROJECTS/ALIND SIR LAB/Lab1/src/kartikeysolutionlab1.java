import java.util.*;
import java.io.*;

public class kartikeysolutionlab1 {

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of rounds");
        int testCases = sc.nextInt();
        sc.nextLine();
        File file = new File("lab\\src\\pthondictionary.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        HashSet<String> set = new HashSet<>();
        HashMap<Character, ArrayList<String>> map = new HashMap<>();
        String st;
        while ((st = br.readLine()) != null) {
            // System.out.println(st);
            ArrayList<String> al = new ArrayList<>();
            char c = st.charAt(0);
            if (map.containsKey(c)) {
                al = map.get(c);
            }
            al.add(st);
            map.put(c, al);
            set.add(st);
        }
        String word = "";
        String compWord = "";
        char lastCompChar = '0';
        for (int t = 0; t < testCases; t++) {
            System.out.print("Enter your word for round " + (t + 1));
            if (t != 0) {
                System.out.println(" starting from the letter " + lastCompChar);
            } else {
                System.out.println();
            }
            String userWord = sc.nextLine();
            userWord = userWord.toLowerCase();
            word += userWord;
            if (t != 0 && userWord.charAt(0) != lastCompChar) {
                System.out.println("Please enter a word starting from letter " + lastCompChar);
                t--;
                continue;
            }
            if (!set.contains(userWord)) {
                System.out.println("The word is not present in the dictonary");
                t--;
                continue;
            }
            set.remove(userWord);
            char lastLetter = userWord.charAt(userWord.length() - 1);
            ArrayList<String> al = map.get(lastLetter);
            String compChoose = al.remove(al.size() - 1);
            compWord += compChoose;
            map.put(lastLetter, al);
            lastCompChar = compChoose.charAt(compChoose.length() - 1);
            System.out.println("Your input " + userWord + "\nComputer input " + compChoose);
        }

        if (word.length() > compWord.length()) {
            System.out.println("You win");
        } else if (word.length() < compWord.length()) {
            System.out.println("Computer wins");
        } else {
            System.out.println("Draw");
        }

    }
}