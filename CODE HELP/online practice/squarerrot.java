public class squarerrot {
    public static void main(String[] args) {
        int number =144;
        int precision = 2;
        // squareroot(number);
        floatSquareroor(number, precision);

    }

    private static void floatSquareroor(int number, int precision) {
        int i=0, j = number/5 ;
        float ans=0;

        int mid = (i+j)/2;
        while(i<=j){
            if(mid*mid==number){
                System.out.println(mid);
                break;
            }
            if(mid*mid<number){
                i=mid+1;
                ans = mid;
            }
            else
                j=mid-1;
            mid = (i+j)/2;
        }
        number = 

        while(precision!=0){
            for(double k=0.1; k<0.9; k++){
                
            }

            precision--;
        }


    }

    private static void squareroot(int number) {
        int i=0, j = number/5;

        int mid = (i+j)/2;
        while(i<=j){
            if(mid*mid==number){
                System.out.println(mid);
                break;
            }
            if(mid*mid<number)
                i=mid+1;
            else
                j=mid-1;


            mid = (i+j)/2;
        }
    }
}
