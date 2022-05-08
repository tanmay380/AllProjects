public class binaryshopping {
    public static void main(String[] args) {
        String str = "01101";
        String ans="";

        int steps=0;
        for (int i = str.length()-1; i >=0; i--) {
            if(str.charAt(i)=='1' && steps<2){
            System.out.println(steps);
                str=str.substring(0, i)+'0'+str.substring(i+1);
                steps++;
            }
            System.out.println(steps+" "+ str);
        }
        if(steps<2){
            str=str.substring(0, str.length()-1)+'1'+str.substring(str.length());
        }
        System.out.println(str);
    }
}
