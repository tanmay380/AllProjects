
//class Mythread extends Thread {
//    @Override
//    public void run() {
//        while (true) {
//            System.out.println("My thread id Running");
//            System.out.println("I am Happy");
//        }
//    }
//}
//
//class Mythread1 extends Thread {
//    @Override
//    public void run() {
//        while (true) {
//            System.out.println("Thread 2 is good");
//            System.out.println("I am sad");
//        }
//    }
//}

import javax.lang.model.element.Name;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.SortedMap;
import java.util.TreeMap;

//class Threading extends Thread {
//
//    @Override
//    public void run() {
//        while (true)
//            try {
//                Thread.sleep(200);
//                System.out.println("Good mOrnig");
//            }catch (Exception e){
//                System.out.println(e);
//            }
//    }
//}
//class Threading1 extends Thread {
//
//    @Override
//    public void run() {
//        while (true)
//            try {
//                Thread.sleep(200);
//                System.out.println("Welcome");
//            }catch (Exception e){
//                System.out.println(e);
//            }
//    }
//}

 class list{
    String name;
    String nm;

     public list(String name, String nm) {
         this.name = name;
         this.nm = nm;
     }

     public String getName() {
         return name;
     }
 }

public class helo {
    public static void main(String[] args) {
//        Threading t1 = new Threading();
//        Threading1 t2 = new Threading1();
//        System.out.println(t1.getState());
////        t1.start();
////        t2.start();
//

        LinkedHashMap<list, String> sortedMap = new LinkedHashMap<>();
        list l1= new list("hello","9315");
        list l2= new list("hdsfello","931df5");
        list l3= new list("helsdflo","93sdfsdf15");

        sortedMap.put(l1,l1.name);
        sortedMap.put(l2,l2.name);

        System.out.println(sortedMap.keySet());






    }
}
