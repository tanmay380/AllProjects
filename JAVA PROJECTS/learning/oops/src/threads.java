class A extends Thread{
    public void run(){
        System.out.println("Therad A started");
        for(int i=0;i<5;i++){
            System.out.println("From thread A : i="+ i );
        }
        System.out.println("Exir from thread  A");
    }
}
class B extends Thread{
    public void run(){
        System.out.println("Therad B started");
        for(int i=0;i<5;i++){
            System.out.println("From thread B : i="+ i );
        }
        System.out.println("Exir from thread  B");
    }
}
class C extends Thread {
    public void run() {
        System.out.println("Therad C started");
        for (int i = 0; i < 5; i++) {
            System.out.println("From thread C : i=" + i);
        }
        System.out.println("Exir from thread  C");
    }
}
class threads{
    public static void main(String[] args) {
        A a=new A();
        B b=new B();
        C c=new C();
        c.setPriority(Thread.MAX_PRIORITY);
        b.setPriority(a.getPriority()+1);
        a.setPriority(Thread.MIN_PRIORITY);
        System.out.println("Start from thread A");
        a.start();
        System.out.println("Start from thread B");
        b.start();
        System.out.println("Start from thread c");
        c.start();

        System.out.println("end");
    }
}

