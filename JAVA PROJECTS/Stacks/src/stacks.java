import java.util.Stack;

public class stacks {
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);

        System.out.println(stack.peek() + " " + stack.contains(5)+ " "+ stack.search(5) +" " + stack.pop()
        );

    }
}
