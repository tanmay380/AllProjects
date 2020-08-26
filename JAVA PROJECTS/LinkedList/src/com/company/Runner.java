package com.company;

public class Runner {

    public static void main(String[] args) {
        LinkedList linkedList=new LinkedList();
        linkedList.inserAt(0,111);
        linkedList.insertAtStart(1);
        linkedList.insert(12);
        linkedList.insert(10);
        linkedList.insert(5);
        linkedList.delete(2);
        linkedList.show();
    }
}
