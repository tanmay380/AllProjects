public class toweofhanoi {
    static  int j=1;
    public static void main(String[] args) {

        towerofHanoi(5,1,2,3);
    }

    private static void towerofHanoi(int i, int i1, int i2, int i3) {
            if(i>0){
                towerofHanoi(i-1,i1,i3,i2);
                System.out.println(j+++"-" +i1 + " " +i3+ ", ");
                towerofHanoi(i-1,i2,i1,i3);
            }
    }
}
