public class RecurssionPermutation {
    public static void main(String[] args) {
        String permute = "ABC";
//        recurssion(permute,0,"");
        permutesameLength(permute, 0, permute.length());
    }

    private static void recurssion(String p, int index, String n) {
        if (index == p.length()) {
            System.out.print(n + " ");
            return;
        }
        recurssion(p, index + 1, n + p.charAt(index));
        recurssion(p, index + 1, n);
    }

    private static void permutesameLength(String p, int index, int r) {

        if (index == r) {
            System.out.print(p + " ");
            return;
        }

        for (int i = index; i < r; i++) {
            p = swap1(p, i, index);
            permutesameLength(p, index + 1, r);
//            p = swap1(p, i, index);
        }

    }

    private static String swap1(String p, int i, int j) {
        char temp;
        char[] chars = p.toCharArray();

        temp = chars[i];

        chars[i] = chars[j];
        chars[j] = temp;

        return String.valueOf(chars);

    }


}
