
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

class Threading extends Thread {

    @Override
    public void run() {
        while (true)
            try {
                Thread.sleep(200);
                System.out.println("Good mOrnig");
            }catch (Exception e){
                System.out.println(e);
            }
    }
}
class Threading1 extends Thread {

    @Override
    public void run() {
        while (true)
            try {
                Thread.sleep(200);
                System.out.println("Welcome");
            }catch (Exception e){
                System.out.println(e);
            }
    }
}

public class helo {
    public static void main(String[] args) {
        Threading t1 = new Threading();
        Threading1 t2 = new Threading1();
        System.out.println(t1.getState());
//        t1.start();
//        t2.start();
            


    }
}
