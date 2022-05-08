
import java.util.*;

public class tripletSum {
    public static void main(String[] args) {
        int[] arr= {1,2,3,1,2,3,4};
        int sum=5;
       
        // bruteforce(arr,sum);
        optimised(arr,sum);

    }

    private static void optimised(int[] arr, int sum) {
        Arrays.sort(arr);
        for(int i=0; i<arr.length-2; i++){ 

            int j=i+1;
            int k=arr.length-1;
            ArrayList<Integer> temp  = new ArrayList<>();
                    temp.add(1);

            while(j<k){
                if(sum==(arr[i]+arr[j]+arr[k])){
                    System.out.println(arr[i] +" " + arr[j] + " " + arr[k]);
                    j++;
                    k--;
                }
                else if(sum>(arr[i]+arr[j]+arr[k])){
                    j++;
                }
                else{
                    k--;
                }
            }

        }



    }


    private static void bruteforce(int[] arr, int sum) {

        ArrayList<ArrayList<Integer>> ans = new  ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < arr.length-2; i++) {
            for (int j = i+1; j < arr.length-1; j++) {                
                for (int j2 = j+1; j2 < arr.length; j2++) {
                    if(arr[i]+arr[j]+arr[j2]==sum){
                        ArrayList<Integer> temp = new ArrayList<>();
                        temp.add(arr[i]);
                        temp.add(arr[j]);
                        temp.add(arr[j2]);
                        Collections.sort(temp);
                        if(!ans.contains(temp)){
                            ans.add(temp);
                        }
                    }
                }
            }
        }
        for(ArrayList<Integer> new1 : ans){
            System.out.println(new1);
        }
    }
}
