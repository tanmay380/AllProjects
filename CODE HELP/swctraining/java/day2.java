import java.util.Arrays;


public class day2 {
    public static void main(String[] args) {
        int[] arr = { 5,4,3,2,1 };
        int j = arr.length -1;

        int i = 0;

        sort(arr, i, j);
        System.out.println(Arrays.toString(arr));
    }

    private static void sort(int[] arr, int i, int j) {
        
        System.out.println(Arrays.toString(arr));
        if (i < j) {
            int mid = (i + j) / 2;

            sort(arr, i, mid);
            sort(arr, mid + 1, j);

            merge(arr, i, mid, j);
        }else{
            System.out.println("out1");
        }
    }

    private static void merge(int[] arr, int i, int mid, int end) {
        int l1 = mid - i + 1;
        int r1 = end - mid;

        int[] larr = new int[l1];
        int[] rarr = new int[r1];

        for (int a = 0; a < l1; a++) {
            System.out.println("l1 is " + l1 + "a+;1 "+ (a+l1));
            larr[a] = arr[i + a];
        }
        for (int a = 0; a < r1; a++) {
            rarr[a] = arr[mid + a + 1];
        }


        int s=0,j=0;
        int k=i;
        while(s<l1 && j<r1){
            
            // System.out.println(s + " " + l1+ " "+ j+" "+r1+" "+k + i + " "+mid+ " "+ end);
            if(larr[s]<=rarr[j]){
                arr[k]=larr[s];
                s++;
            }
            else{
                arr[k]=rarr[j];
                j++;
            }
            k++;            
        }
        while(s<l1){
            
            arr[k]=larr[s];
            s++;
            k++;
        }
        while(j<r1){
            arr[k]=rarr[j];
            j++;
            k++;
        }
    }

}
