public class Vehicle {


    void run() {
        System.out.println("Vehicle is running");
    }

}

class Bike2 extends Vehicle {
    void run() {
        System.out.println("Bike is running safely");
    }



    public static void main(String args[]) {
        Vehicle obj = new Bike2();//creating object  
        obj.run();//calling method  
    }
}
