public class findUnique {
    public static void main(String[] args) {
        int[] arr = {1,1,3,3,2,2,7};
        
        findDuplicates(arr);
        // findMissiongNUmber(arr);

    }

    private static void findMissiongNUmber(int[] arr) {
        for(int i=0; i< arr.length; i++){
            int index = Math.abs(arr[i])-1;

        }
    }

    private static void findDuplicates(int[] arr) {
        for(int i=0; i<arr.length; i++){
            int index= Math.abs(arr[i]) -1;
            if(arr[index]<0){
                System.out.println(index+1);
                continue;
            }
            arr[index]=-arr[index];
        }
        for(int i=0; i<arr.length; i++){
            System.out.print(arr[i] +" ");
        }
        for(int i=0; i< arr.length ;i++){
            if(arr[i]>0){
                System.out.print(i+1 +" ");
            }
        }
    }   
}
