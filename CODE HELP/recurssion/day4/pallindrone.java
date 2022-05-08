package day4;

public class pallindrone {
    public static void main(String[] args) {
        String str = "abcba";

        System.out.println(checkPallindrone(str,0));
    }

    private static Boolean checkPallindrone(String str, int s) {
        int end = str.length()-s-1;
        if(s>=end){
            return true;
        }
        if(str.charAt(s)!=str.charAt(end)){
            return false;
        }else{
        return checkPallindrone(str, ++s);
        }
    }
}
