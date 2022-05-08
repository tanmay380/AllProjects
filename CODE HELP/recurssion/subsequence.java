public class subsequence {
    public static void main(String[] args) {
        String str = "abc";
        String ans = "";
        subsequence1(str, 0, ans);

    }

    private static void subsequence1(String str, int i, String ans) {
        if (i >= str.length()) {
            System.out.println(ans);
            return;
        }
        subsequence1(str, i+1, ans);
        subsequence1(str, i+1, ans+str.charAt(i));
    }
}
