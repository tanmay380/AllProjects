public class oveririding {

    public static void main(String[] args) {
        int[] a= {5,4,3,2,1,10, 9, 8, 7 ,6};
        int l=0, r=a.length-1;
        mergeSort(a, l ,r);

        printArray(a);
    }
    static  void mergeSort(int[] a, int l, int r){
       if(l<r) {
           int mid = (l + r) / 2;

           mergeSort(a, l, mid);
           mergeSort(a, mid + 1, r);

           mergeArray(a,l,mid,r);

       }
    }

    private static void mergeArray(int[] a, int l, int mid, int r) {
        int n1= mid-l+1;
        int n2= r-mid;

        int a1[] = new int[n1];
        int a2[] = new int[n2];

        for (int i=0;i<n1;i++){
            a1[i]=a[l+i];
        }
        for (int i=0;i<n2;i++){
            a2[i]=a[mid+1+i];
        }

        int i=0;
        int j=0;
        int k=l;

        while (i<n1 && j<n2){
            if (a1[i]<a2[j]){
                a[k] = a1[i];
                k++; i++;
            }else{
                a[k]=a2[j];
                k++; j++;
            }
        }

        while (i<n1){
            a[k]=a1[i];
            k++; i++;
        }
        while (j<n2){
            a[k]=a2[j];
            k++; j++;
        }
    }

    private static void printArray(int[] b) {
        for (int i=0;i<b.length;i++){
            System.out.print(b[i] + " ");
        }
    }
}
