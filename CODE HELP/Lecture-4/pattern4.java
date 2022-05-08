public class pattern4{
    public static void main(String[] args) {
        int lines=4;
        int i=1;
        while(i<=lines){
            for (int j = 0; j < i; j++) {
                System.out.print("*");
            }
            System.out.println();
            i++;
        }
    }
}