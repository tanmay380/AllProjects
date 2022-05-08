package day4;

public class reversestring {
    public static void main(String[] args) {
        String str= "abcde";
        int size = str.length();

        System.out.println(reverseRecur(str,0, size-1));
    }

    private static String reverseRecur(String str,int s, int e) {
        
        StringBuilder sb = new StringBuilder(str);
        if(s>e){
            System.out.println(s+" " + e);
            return  sb.toString();
        }
        else{
        sb.insert(s, str.charAt(e));
        sb.insert(e, str.charAt(s));
        System.out.println(sb);

        s++;e--;
        return reverseRecur(str, s, e);
        }
    }
}
