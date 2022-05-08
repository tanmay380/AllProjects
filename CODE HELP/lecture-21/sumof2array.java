public class sumof2array {
    public static void main(String[] args) {
        int [] arr1= {1,2,3, 4}, arr2={6};
        int n1=0, n2=0;
        for (int i = 0; i < arr1.length; i++) {
            n1=(10*n1 )+ arr1[i];
        }
        for (int i = 0; i < arr2.length; i++) {
            n2=(10*n2 )+ arr2[i];
        }
        int ans = n1+n2;
        
        int mod=0;
        while(ans!=0){
            System.out.print(ans/10+" ");
            ans/=10;
        }
        System.out.println(mod);
    }
}
