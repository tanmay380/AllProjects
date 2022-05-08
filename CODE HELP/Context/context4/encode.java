import java.util.ArrayList;
import java.util.Arrays;

public class encode {
    public static void main(String[] args) {
        char[] letters= {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
        char[] vowels = {'a','e','i','o','u'};

        String str = "code";
        String ans="";

        for (int i = 0; i < str.length(); i++) {
            char search =str.charAt(i);
            
            int index= searchinletters(search, letters);

            if(seacrchinVowels(search, vowels)){
                ans+=letters[index+1];
            }else{
                ans+=letters[index-1];
            }
        }
        System.out.println(ans);

    }

    private static boolean seacrchinVowels(char search, char[] vowels) {
     for (int i = 0; i < vowels.length; i++) {
         if(search==vowels[i])
            return true;
     }
        return false;
    }

    private static int searchinletters(char search, char[] letters) {
        return Arrays.binarySearch(letters, search);
    }
}
