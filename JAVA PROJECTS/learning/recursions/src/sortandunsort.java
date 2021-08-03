public class sortandunsort {
    public static void main(String[] args) {
        int arr[] = {1,2,4,5};
        System.out.println(sort(arr,1));
    }

    private static boolean sort(int[] arr,int index) {
        if (index>=arr.length)
            return true;
        if (arr[index]<arr[index-1]){
            return false;
        }
                sort(arr,index+1);
return true;
    }
}
