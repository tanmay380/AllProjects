public class quicksort {
    void sort(int[] arr, int l, int r) {
        if (l < r) {
            int pi=partition(arr,l,r);
            sort(arr,l,pi-1);
            sort(arr,pi+1,r);
        }
    }


    int partition(int[] arr, int l, int r) {
        int pivot = arr[r];//pivot is the right postion
        int i = l - 1;
        for (int j = l; j < r; j++) {
            if (arr[j] < pivot) {
                i++;
                int temp=arr[j];
                arr[j]=arr[i];
                arr[i]=temp;
            }
        }
        int temp = arr[i + 1];
        arr[i + 1] = arr[r];
        arr[r] = temp;
        return i + 1;
    }
    void printArray(int arr[])
    {
        int n = arr.length;
        for (int i=0; i<n; ++i)
            System.out.print(arr[i]+" ");
        System.out.println();
    }

    public static void main(String[] args) {
        int arr[]={10, 7, 8, 9, 1, 5};
        int b=arr.length;

        quicksort qs=new quicksort();
        qs.sort(arr,0,b-1);

        qs.printArray(arr);


    }
}
