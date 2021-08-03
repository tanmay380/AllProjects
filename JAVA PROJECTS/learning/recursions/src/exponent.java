public class exponent {
    public static void main(String[] args) {
        System.out.println(exp(2, 3));
    }

    private static int exp(int i, int i1) {
        if (i1 %2== 0)
            return 1;

        return exp(i, i1 - 1);
    }
}
