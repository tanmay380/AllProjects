import javax.swing.*;
import java.awt.image.AreaAveragingScaleFilter;
import java.util.*;

public class dif {
    public static void main(String[] args) {

        for(int i=400;i<=700;i++){
            checkfornumber(i);
        }
    }

    private static boolean checkfornumber(int i) {
        long squared=(long)i*i;
        String sq=String.valueOf(squared);
        String left=sq.substring(0,sq.length()/2);
        String right=sq.substring(sq.length()/2);
        int numl=(left.isEmpty()) ? 0: Integer.parseInt(left);
        int numr=(right.isEmpty()) ? 0: Integer.parseInt(right);

        if(numl+numr==i){
            System.out.println(i);
            return true;
        }else
            return false;

    }
}
