public class phonekeyprob {
    public static void main(String[] args) {
        String str="23";

        String ans="";
        int index=0;
        String[] mapping={"","","abc","def", "ghi","jkl","mno","pqrs","tuv","wxyz"};
        
        solve(str,index,mapping,ans);

        
    }

    private static void solve(String str, int index, String[] mapping,String ans) {
        if(index>=str.length()){
            System.out.println(ans);
            return;
        }
        int number = str.charAt(index)-'0';
        String value= mapping[number];

        for (int i = 0; i < value.length(); i++) {
            ans+=value.charAt(i);
            solve(str, index+1, mapping, ans);
            ans= ans.substring(0, ans.length()-1);
        }

    }
}
