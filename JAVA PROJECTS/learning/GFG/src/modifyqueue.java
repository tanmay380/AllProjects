// { Driver Code Starts
//Initial Template for Java

import java.util.*;
class modifyqueue{
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int t=sc.nextInt();
        while(t-->0){
            int n=sc.nextInt();
            int k=sc.nextInt();
            Queue<Integer> q=new LinkedList<>();
            while(n-->0){
                q.add((int)sc.nextInt());
            }
            GfG g=new GfG();
            Queue<Integer> ans=g.modifyQueue(q,k);
            while(!ans.isEmpty()){
                int a=ans.peek();
                ans.poll();
                System.out.print(a+" ");
            }
            System.out.println();
        }
    }
}
// } Driver Code Ends


//User function Template for Java

class GfG
{
    public Queue<Integer> modifyQueue(Queue<Integer> q, int k)
    {
        Stack<Integer> stack=new Stack<>();
        Queue<Integer> qq=new LinkedList<>();
        while (k-->0){
            int temp=q.peek();
            q.poll();
            stack.push(temp);
        }
        while (!stack.isEmpty()){
            int a=stack.peek();
            stack.pop();
            qq.add(a);
        }
        while(!q.isEmpty()){                        // Add rest of the elements to the new queue
            int a=q.peek();
            q.poll();
            qq.add(a);
        }
        return qq;
    }
}
