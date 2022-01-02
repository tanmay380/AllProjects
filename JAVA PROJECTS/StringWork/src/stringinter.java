import java.util.Arrays;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class stringinter {


    public static void main(String[] args) {
        String sms="Rs.54.00 is debited from Kotak Bank a/c XXXX0238 to Q703403035@ybl on 26-11-21. " +
                "To report fraud/raise dispute, click kotak.com/fraud. New balance: Rs. 118.71";
        String containsmsg="is debited from kotak bank a/c";

        if (sms.toLowerCase(Locale.ROOT).contains(containsmsg.toLowerCase(Locale.ROOT))){
            System.out.println("in");

            String sub="";
            Pattern p = Pattern.compile("^Rs[.]\\d*[.]");
            Matcher m = p.matcher(sms);
            while (m.find()){
                sub= m.group();
                System.out.println(m.group());
            }
            String[] ans= sub.split("Rs.");
            System.out.println(ans[1]);
            String[] finalstr = ans[1].split("\\.");
            System.out.println(Arrays.toString(finalstr));
        }
    }

}
