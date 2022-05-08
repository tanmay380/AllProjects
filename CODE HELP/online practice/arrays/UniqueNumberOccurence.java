import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class UniqueNumberOccurence {
    public static void main(String[] args) {
          int[] arr = {1,2,1,1,3,3};

          HashMap<Integer, Integer> map = new HashMap<>();
          
          for(int i=0;i<arr.length;i++){
              map.put(arr[i], map.getOrDefault(arr[i], 0)+1);
              System.out.println(map.size());
          }
          System.out.println(map.entrySet());
        
          for(Map.Entry<Integer, Integer> pair : map.entrySet() ){
              map.remove(pair.getKey());
              if(map.containsValue(pair.getValue())){
                    
                System.out.println(false);
                break;
              }
          }
          System.out.println(true);
    }
}
