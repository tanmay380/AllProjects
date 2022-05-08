/*
    Time Complexity : O(N*logN)
    Space Complexity : O(1)

    where 'N' is the size of the given array.
*/

import java.util.Arrays;
import java.util.Comparator;

public class ninjatecnique {
    public static void main(String[] args) {
        
        int[][] a= {{5,2},{-4,3},{-10,-5}};
        int n=a.length;
        System.out.println(ninjaTechnique(n, a));
    }

  private static final int INF = 100010;

  public static int ninjaTechnique(int n, int a[][]) {

   

    // Store the ending coordinate of a segment first.
    for (int i = 0; i < n; i++) {
      // Swap if first coordinate is the ending coordinate.
      if (a[i][0] > a[i][1]) {
        int tmp = a[i][0];
        a[i][0] = a[i][1];
        a[i][1] = tmp;
      }
    }
    print(a);
    

    Segment[] arr = new Segment[n];
    System.out.println();

    for (int i = 0; i < n; i++) {
      arr[i] = new Segment(a[i][0], a[i][1]);
      System.out.println(arr[i].start+" "+arr[i].end);
    }
    // System.out.println(Arrays.toString(arr));

    // Sort the segments by its end points.
    Arrays.sort(arr, Comparator.comparingInt(o -> o.end));
    System.out.println();

    for (int i = 0; i < arr.length; i++) {
        System.out.println(arr[i].start + " " + arr[i].end);
    }

    // Variable to store the point of last placed Ninja.
    int prev = -INF;

    // Variable to store the final result.
    int ans = 0;

    for (int i = 0; i < n; i++) {
      // Check if the beginning coordinate is greater than the last placed Ninja copy.
      if (arr[i].start > prev) {
          System.out.println("start is " + arr[i].start);
 
        // Update the value of 'prev'.
        prev = arr[i].end;

        // Increment the 'ans'.
        ans++;
      }
    }

    // Return the final result.
    return ans;
  }

  static void print(int[][] arr){
      for (int i = 0; i < arr.length; i++) {
          for (int j = 0; j < arr[i].length; j++) {
              System.out.print(arr[i][j] +" ");
          }
          System.out.println();
      }
  }

  static class Segment {

    int start, end;

    Segment(int x, int y) {
      this.start = x;
      this.end = y;
    }
  }

}