import java.util.Arrays;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class stringinter {


    public static void main(String[] args) {
        // String sms="Rs.54.00 is debited from Kotak Bank a/c XXXX0238 to Q703403035@ybl on 26-11-21. " +
        //         "To report fraud/raise dispute, click kotak.com/fraud. New balance: Rs. 118.71";
        String sms="ALERT: INR 182.00 is spent on your BoB Credit Card ending 2954 at Grofersindiapvtltd on 13-03-2022. Available credit limit is Rs 49,458.00, Current outstanding is Rs 0.00. Not you?  Call 1800225100 (toll-free)";
        // String containsmsg="is debited from kotak bank a/c";
        String containsmsg="is spent on your BoB Credit Card";

        if (sms.toLowerCase(Locale.ROOT).contains(containsmsg.toLowerCase(Locale.ROOT))){

            String sub="";
            Pattern p = Pattern.compile("INR.\\d*.[0-9][0-9].*at\\s*(\\S+)");
            Matcher m = p.matcher(sms);
            while (m.find()){
                sub= m.group();
            }
            System.out.println();
            String[] ans= sub.split("is ");
            String[] amount = ans[0].split("INR");
            System.out.println(amount[1]);
            String[] where = ans[1].split("at");
            System.out.println(where[1]);
        }
    }

}
