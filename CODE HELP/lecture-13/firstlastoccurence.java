public class firstlastoccurence {
    public static void main(String[] args) {
        int[] arr= {2,2,2,2,2,6};
        int n =arr.length;
        int find = 2;

        // firstOccurence(arr, n, find);
        lastOccurence(arr, n, find);
    }

    private static void firstOccurence(int[] arr, int n, int find) {
        int i = 0; 
        int j=n-1;
        int min = 0;

        while(i<=j){
            int mid = (i+j)/2;
            if(arr[mid]==find){
                System.out.println(mid);
                min=mid;
                j = mid-1;
            }   
            if(arr[mid]<find){
                i=mid+1;
            }else
                j = mid -1;
        }
        System.out.println(min);
    }

    private static void lastOccurence(int[] arr, int n, int find) {
        int i = 0; 
        int j=n-1;
        int min = 0;
        System.out.println(j);
        
        int mid = (i+j)/2;

        while(i<=j){
             
            System.out.println("mid is - > " + mid + "  " + i +" " + j);
            if(arr[mid]==find){
                System.out.println(mid);
                min=mid;
                i = mid+1;
            }   
            if(arr[mid]<find){
                System.out.println(mid);
                i=mid+1;
            }else if(arr[mid]>find)
                j = mid-1;
            mid = (i+j)/2;

        }
        System.out.println(mid);
    }
}
