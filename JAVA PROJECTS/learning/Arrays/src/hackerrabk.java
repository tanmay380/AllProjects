import java.util.Arrays;
import java.util.Collections;

public class hackerrabk {
    public static void main(String[] args) {
        ArrayManipulation();


    }

    private static void ArrayManipulation() {
        int[][] arr= {{1, 5, 3},
                {4, 8 ,7},
                {6, 9, 1}};
        int arrsize =   10;
        int[] answer = new int[arrsize];

        for (int i=0; i< 3; i++){
            int start = arr[i][0];
            int end = arr[i][1];
            for (int j=start-1; j<end; j++){
                answer[j]+=arr[i][2];
            }

        }
        int max=0;
        for (int i = 0; i < answer.length; i++) {
            if (max<answer[i])
                max=answer[i];
        }
        System.out.println(max);


    }
}
