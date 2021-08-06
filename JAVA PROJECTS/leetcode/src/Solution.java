import java.util.*;

public class Solution {
    public static void main(String[] args) {
//        System.out.println(reverse());
//        System.out.println(pallindrome());
//        System.out.println(romanToInteger());
//        System.out.println(LongestCommonPrefi());
        System.out.println(ValidParenthesis());
    }

    private static boolean ValidParenthesis() {
        String s = "){}";
        if (s.length()<=1)
            return false;
        Stack<Character> stack = new Stack<>();

        for (int i=0; i<s.length(); i++){
            char s1= s.charAt(i);
            if ( s1== '(' || s1== '{' || s1== '[' ){
                stack.push(s1);
            }else if (!stack.isEmpty() && s1 == ')') {
                if (stack.peek() == '(')
                    stack.pop();
                else {
                    return false;
                }

            }
            else if (!stack.isEmpty() && s1 == '}') {
                if (stack.peek() == '{')
                    stack.pop();
                else {
                    return false;
                }
            }
            else if (!stack.isEmpty() && s1 == ']') {
                if (stack.peek() == '[')
                    stack.pop();
                else {
                    return false;
                }
            }
            else if(stack.isEmpty())
                return false;
        }


        if (stack.isEmpty())
            return true;
        else
            return false;
    }




    private static int romanToInteger() {
        String roman = "III";
        HashMap<Character, Integer> use = new HashMap<>();
        use.put('I', 1);
        use.put('V', 5);
        use.put('X', 10);
        use.put('L', 50);
        use.put('C', 100);
        use.put('D', 500);
        use.put('M', 1000);

        int x = 0;
        for (int i = 0; i < roman.length() - 1; i++) {
            char rm = roman.charAt(i);
            if (use.get(rm) >= use.get(roman.charAt(i + 1))) {
                x += use.get(rm);
            } else
                x -= use.get(rm);
        }
        x += use.get(roman.charAt(roman.length() - 1));

        return x;
    }

    private static boolean pallindrome() {
        int x = 121;
        int temp = x;
        if (x < 0) {
            return false;
        }
        int y = 0;
        while (temp > 0) {
            y = y * 10 + temp % 10;
            temp /= 10;
        }
        System.out.println(y);
        if (y == x) {
            return true;
        }

        return false;
    }

    private static long reverse() {
        long ans = 0;
        int x = -1230;
        boolean negative = false;
        if (x < 0) {
            negative = true;
            x *= -1;
        }

        while (x > 0) {
            ans = ans * 10 + (x % 10);
            x /= 10;

        }
        if (negative)
            ans *= -1;

        System.out.println(Integer.MAX_VALUE);

        return ans;

    }
}
