public class rotatedsort {
    public static void main(String[] args) {
        int[] arr= {7,8,1,3,5};
        int n =  arr.length;

        int x = findpivot(arr,n);
        System.out.println(x);
        findnumberroted(arr, x ,5);
    }

    private static void findnumberroted(int[] arr, int x, int key) {
        int i=0, j =x;
        if(key>=arr[x] && key<=arr.get(arr.length-1)){
            i=x;j=arr.length-1;
        }else{
            i=0;j=x-1;
        }
        System.out.println(i + " " + j);
        
        int mid  = (i+j)/2;
        while(i<=j){
            System.out.println(i +" "+ j +" "+ mid);
            if(arr.get(mid) == key){
                System.out.println(mid);
                break;
            }

            if(arr.get(mid)<key){
                i = mid+1;
            }
            else{
                j=mid;
            }
            mid  = (i+j)/2;
        }
    }

    private static int findpivot(int[] arr, int n) {
        int i=0, j=n-1;

        int mid  = (i+j)/2;
        while(i<j){
            
            if(arr.get(mid)>=arr.get(0))
                i=mid+1;
            else
                j=mid;

            mid  = (i+j)/2;
        }

        return j;
    }
}
