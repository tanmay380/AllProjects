import java.util.Stack;

public class maxLength {
    public static void main(String[] args) {
        String s = "()())(";
//        System.out.println(maxlength(s));
        countZeroes();

    }

    private static void countZeroes() {
        int arr[] = {1,1,1,1,1,0,0,0};
        int n =arr.length;
        int mid = n/2;
        int zero=0;

        for (int i=0; i<=mid; i++){
            if (arr[i]==0)
                zero++;
        }
        for (int i=mid+1; i<n; i++){
            if (arr[i]==0)
                zero++;
        }
        System.out.println(zero);
    }

    private static int maxlength(String s) {
        Stack<Integer> stack = new Stack<>();
        int ans = 0 ;
        stack.push(-1);


        for (int i = 0; i <s.length(); i++) {
            if(s.charAt(i)=='('){
                stack.push(i);
            }else{
                if(!stack.isEmpty()){
                    stack.pop();
                }
                if(!stack.isEmpty()){
                    System.out.println(i + "  " + ans + " " + stack.peek());
                    ans = Math.max(ans, i - stack.peek());
                    System.out.println(ans);
                }else{
                    stack.push(i);
                }
            }
        }

        return ans;
    }
}
