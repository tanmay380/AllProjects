import java.util.Stack;

public class stack {
    public static void main(String[] args) {
        Stack<String> stringStack=new Stack<>();
        String s="ABCSDED";
        for(int i=0;i<s.length();i++){
            stringStack.push(s);
        }
        System.out.println(stringStack.pop());
    }
}
