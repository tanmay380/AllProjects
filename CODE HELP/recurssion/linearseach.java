public class linearseach {
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6};
        int key=1;

        System.out.println(search(arr,key, arr.length));
   }

    private static String search(int[] arr, int key, int size) {
        if(size==0){
            return "not found";
        }
        if(arr[size-1]==key){
            return "found";
        }
        return search(arr, key, size-1);

    }
}
