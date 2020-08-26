package com.company;

public class LinkedList {
    Node head;

    public void insert(int data) {
        Node node = new Node();
        node.data = data;
        node.next = null;

        if (head == null) {
            head = node;
        } else {
            Node n = head;
            while (n.next != null) {
                n = n.next;
            }
            n.next = node;
        }
    }
    public void insertAtStart(int data){
        Node node = new Node();
        node.data = data;
        node.next = null;
        node.next=head;
        head=node;

    }

    public void inserAt(int index,int  data){
        try {
            Node node = new Node();
            node.data = data;
            node.next = null;
            if(index==0){
                insertAtStart(data);
                return;
            }
            Node n = head;
            for (int i = 0; i < index - 1; i++) {
                n = n.next;
            }
            node.next = n.next;
            n.next = node;
        }
        catch (NullPointerException e){
            System.out.println("Index out of range");
        }
    }
    public void delete(int index){
        if (index==0){
            head=head.next;
        }
        else{
            Node n = head;
            Node n1 = null;
            for (int i = 0; i < index - 1; i++) {
                n = n.next;
            }
            n1=n.next;
            n.next=n1.next;
            n1=null;
        }
    }

    public void show(){
        Node n=head;
        while (n.next!=null){
            System.out.println(n.data);
            n=n.next;
        }
        System.out.println(n.data);
    }
}
