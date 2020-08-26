import java.util.*;
import java.lang.*;
import java.io.*;

class gfg
{
    static int kthsmallest(int arr[], int l, int r, int k)
    {
        // If k is smaller than number of elements in array
        if (k > 0 && k <= r - l + 1)
        {

            // partitioning the array along the pivot
            int pos = randomPartition(arr, l, r);


            // check if current element gives you the kth smallest element
            if (pos-l == k-1)
                return arr[pos];

            // else recurse for the left and right half accordingly
            if (pos-l > k-1)
                return kthsmallest(arr, l, pos-1, k);
            return kthsmallest(arr, pos+1, r, k-pos+l-1);
        }

        return Integer.MAX_VALUE;
    }


    static int partition(int arr[], int l, int r)
    {
        int x = arr[r], i = l;
        for (int j = l; j <= r - 1; j++)
        {
            if (arr[j] <= x)
            {
                int temp = arr[j];
                arr[j] = arr[i];
                arr[i] = temp;
                i++;
            }
        }
        int temp = arr[r];
        arr[r] = arr[i];
        arr[i] = temp;
        return i;
    }

    // Function to partition the array along the random pivot
    static int randomPartition(int arr[], int l, int r)
    {
        Random rand = new Random();
        int n = r-l+1;
        int pivot = rand.nextInt(n);
        int temp = arr[r];
        arr[r] = arr[l+pivot];
        arr[l+pivot] = temp;

        return partition(arr, l, r);
    }

    public static void main(String args[])
    {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while(t-- > 0)
        {
            int n = sc.nextInt();
            int arr[] = new int[n];

            for(int i = 0; i < n; i++)
                arr[i] = sc.nextInt();

            int k = sc.nextInt();
            System.out.println(kthsmallest(arr, 0, n-1, k));
        }
    }
}
