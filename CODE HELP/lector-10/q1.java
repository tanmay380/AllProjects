public class q1 {
    public static void main(String[] args) {        
    int[] a= {1,2,3,4,5,6,7};

    for(int i=0;i<a.length;i+=2){
        if(i+1<a.length){
            int c=a[i];
            a[i] = a[i+1];
            System.out.print(i);
            a[i+1]=c;     
        }   
    } 

for(int i=0;i<a.length;i++){
    System.out.print(a[i] +" ");
}

    }
}
