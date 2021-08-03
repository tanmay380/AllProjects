public class ANujBhaiya {
    public static void main(String[] args) {
//        System.out.println(exp(3,30));
//        System.out.println(matrix(2,3));
        System.out.println(pallindrone("abba", 0,3));
    }

    private static boolean pallindrone(String str, int l, int r) {
        if (l>=r) return true;
        if (str.charAt(l)!=str.charAt(r)) return false;

        return pallindrone(str,l+1,r-1);
    }

    static void Permutation(String s, int l, int r){
        if (l==r){ System.out.println(s);
            return;
        }
        for (int i=l; i<=r; i++){
//            s=swap(s,l,i);
        }
    }


//    private static int Josephus(int n, int k) {
//        if (n==1) return n;
//
//
//
//
//    }

    static long exp(int a, int b){
        if (b==0) return 1;
        long ans = a*exp(a,b-1);
        return ans;
    }

    static  int matrix(int n, int m){
        if (n==1||m==1) return 1;

        return matrix(n-1,m)+matrix(n,m-1);
    }

}
