import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class instrav {
    public static void main(String[] args) {
//        Node root = new Node(1);
//        root.left=new Node(2);
//        root.left.left= new Node(4);
//        root.left.right= new Node(5);
//        root.left.right.left= new Node(6);
//
//        root.right= new Node(3);
//        root.right.left = new Node(7);
//        root.right.right= new Node(8);
//        root.right.right.left=new Node(9);
//        root.right.right.right= new Node(10);

        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);

        root.left.left = new Node(4);
        root.left.right = new Node(5);

        root.right.left = new Node(6);
        root.right.right = new Node(7);


//        root.preorder(root);
//        System.out.println();
//        root.inorder(root);
//        System.out.println();
//        root.postorder(root);
//        System.out.println();
//        System.out.print(root.bfs(root));
//        System.out.print(root.iterativePreoder(root));
        System.out.print(root.iterativeInorder(root));
//            root.iterativePostorder(root);
    }


}

class Node {
    int data;
    Node left;
    Node right;

    Node(int data) {
        this.data = data;
    }

    void preorder(Node node) {
        if (node == null)
            return;
        System.out.print(node.data + " ");
        preorder(node.left);
        preorder(node.right);
    }

    void inorder(Node node) {
        if (node == null)
            return;
        inorder(node.left);
        System.out.print(node.data + " ");
        inorder(node.right);

    }

    public void postorder(Node node) {
        if (node == null)
            return;
        postorder(node.left);
        postorder(node.right);
        System.out.print(node.data + " ");
    }

    List<List<Integer>> bfs(Node root) {
        Queue<Node> queue = new LinkedList<>();
        List<List<Integer>> wraplist = new LinkedList<>();

        if (root == null)
            return wraplist;
        queue.add(root);
        while (!queue.isEmpty()) {
            List<Integer> sublist = new LinkedList<>();
            int level = queue.size();
            for (int i = 0; i < level; i++) {
                if (queue.peek().left != null) queue.add(queue.peek().left);
                if (queue.peek().right != null) queue.add(queue.peek().right);
                sublist.add(queue.poll().data);
            }
            wraplist.add(sublist);
        }
        return wraplist;
    }

    List<Integer> iterativePreoder(Node root) {
        Stack<Node> stack = new Stack<>();
        List<Integer> ans = new LinkedList<>();
        if (root == null) {
            System.out.println("The tree is empty");
            return ans;
        }
        stack.push(root);
        while (!stack.isEmpty()) {
            root = stack.pop();
            ans.add(root.data);
            if (root.right != null)
                stack.push(root.right);
            if (root.left != null)
                stack.push(root.left);
        }
        return ans;
    }

    List<Integer> iterativeInorder(Node node) {
        List<Integer> ans = new LinkedList<>();
        Stack<Node> stack = new Stack<>();
//        Node node = root;
        while (true) {
            if (node != null) {
                stack.push(node);
                node = node.left;
            } else {
                if (stack.empty())
                    break;
                node = stack.pop();
                ans.add(node.data);
                node = node.right;
            }
        }

        return ans;
    }

    void iterativePostorder(Node root){
        Stack<Node> stack =new Stack<>();
        Stack<Integer> ans  = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()){
            Node  node = stack.pop();
            ans.add(node.data);
            if(node.left!=null)  stack.push(node.left);

            if(node.right!=null)  stack.push(node.right);

        }
        while (!ans.isEmpty()){
            System.out.print(ans.pop()+" ");
        }
    }
    class Pair{
        Node node;
        int num;
        Pair(Node node, int num){
            node= node;
            num=num;
        }
    }

    void allinonetraverse(Node root){
        Stack<Pair> stack= new Stack<>();
        List<Integer> postorder = new LinkedList<>();
        List<Integer> preorder = new LinkedList<>();
        List<Integer> inorder = new LinkedList<>();

        stack.push(new Pair(root,1));

        if (root==null) return;

        while (!stack.isEmpty()){
            Pair it= stack.pop();

            //PREORDER
            if (it.num==1){
                preorder.add(it.node.data);
                it.num++;
                stack.push(it);


            }



        }

    }


}