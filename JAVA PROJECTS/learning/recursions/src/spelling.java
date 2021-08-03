public class spelling {
    public static void main(String[] args) {

        String[] str= {"zero", "one", "two", "three", "four", "five", "six", "seven","eight","nine"};

        getNumber(23,str);

    }

    private static void getNumber(int i, String[] str) {
        int singledigt = i%10;
        int newdigir= i/10;
        if (i==0){
            return;
        }

        getNumber(newdigir,str);

        System.out.print(str[singledigt]+" ");
//        System.out.print(newdigir+" ");
//


    }
}
