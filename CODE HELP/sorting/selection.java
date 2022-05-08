public class selection {
    public static void main(String[] args) {
        int[] arr= {1,5,3,21,1,9};
        selectionsort(arr);
        print(arr);
    }
    public static void selectionsort(int[] arr) {
        int n=arr.length;

        for(int i=0; i<n-1; i++){
            int minIndex= i;

            for (int j = i+1; j < arr.length; j++) {
                    if(arr[minIndex]>arr[j])
                        minIndex = j;
            }
            
            if(i!=minIndex)  swap(arr, minIndex , i);
        }
    }

    public static void swap(int[] arr, int a, int b) {
        arr[a] = arr[a] ^ arr[b];
        arr[b] = arr[a] ^ arr[b];
        arr[a] = arr[a] ^ arr[b];
    }

    public static void print(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
                System.out.print(arr[i]+ " ");
        }
        
    }
}
