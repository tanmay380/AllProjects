public class drawdiamond {
    public static void main(String[] args) {
        int r = 3, c = 1, s = 1;

        
        int spce = s-1;
       for(int i=0; i<s; i++){
           for(int j=0;j<spce; j++){
               System.out.print(" ");
           }
           for(int j=0; j<=i;j++){
               System.out.print("/ ");
           }
           System.out.println();
           spce--;

       }
    }
}
