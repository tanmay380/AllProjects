// { Driver Code Starts
//Initial Template for Java
import java.util.*;

class Geeks1
{
    // Function to insert element into list
    public static void insert(ArrayList<Integer> list, int x)
    {
        list.add(x);

    }

    // Function to sort list in Increasing order
    public static void IncOrder(ArrayList<Integer> list)
    {
        Collections.sort(list);
    }

    // Function to search element in the lise
    // val : element to be searched
    public static void Search(ArrayList<Integer> list, int val)
    {
        int flag = -1;
        for(int i=0;i<list.size();i++){
            if (list.get(i).equals(val)){
                flag=i;
                System.out.println(flag);
                break;
            }
        }
        if(flag == -1){
            System.out.println("-1");
        }


    }

    // Function to sort list in decreasing order
    public static void DecOrder(ArrayList<Integer> list)
    {
        Collections.sort(list,Collections.reverseOrder());
    }
}


// { Driver Code Starts.

// Driver class with driver function
class GFG1
{
    // Driver code
    public static void main (String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while(t-- > 0)
        {
            // Declaring ArrayList
            ArrayList <Integer> list = new ArrayList<Integer>();
            int q = sc.nextInt();

            // Working through queries
            while(q-- > 0)
            {
                char ch = sc.next().charAt(0);
                Geeks1 obj = new Geeks1();
                if(ch == 'a')
                {
                    int x = sc.nextInt();
                    obj.insert(list, x);
                }

                if(ch == 'i')
                {
                    obj.IncOrder(list);
                }

                if(ch == 's')
                {
                    int ele = sc.nextInt();
                    obj.Search(list, ele);
                }
                if(ch == 'd')
                {
                    obj.DecOrder(list);
                }


            }
        }
    }
}  // } Driver Code Ends