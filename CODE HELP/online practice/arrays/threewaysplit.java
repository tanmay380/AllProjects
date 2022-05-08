public class threewaysplit {
        public static void main(String[] args) {
            int[] arr = {28 ,6 ,12 ,98 ,46, 81, 26 ,70, 59, 33, 21 ,97, 18, 8 ,21, 74, 19 ,38, 13, 14, 19 ,87 ,71 ,70 ,42 ,30 ,28 ,26, 60, 43 ,28 ,68, 1, 49, 19 ,50 ,52 ,68, 34, 92, 60, 55 ,34, 41 ,46 ,18 ,7, 28 ,32 ,87 ,63, 70, 37, 50 ,45, 29, 91, 66, 77, 62 ,80, 38, 35, 89, 1, 4, 27, 27, 100, 69 ,32 };
            int n=arr.length;

            
            int i=0, j= n-1;
            int sumi=arr[i],sumj=arr[j];
            int tempsum=0;
        
            while(i<j){
                if(sumi==sumj){
                    System.out.println(i + " "+j);
                    System.out.println(arr[i] + " " + arr[j]);
                    System.out.println(sumi);
                    sumi+=arr[++i];
                    sumj+=arr[--j];
                }
                if(sumi<sumj){
                    i++;
                    sumi+=arr[i];
                }
                if(sumi>sumj){
                    j--;
                    sumj+=arr[j];
                }              


            }
}
}
