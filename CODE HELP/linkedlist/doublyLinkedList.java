public class doublyLinkedList {
    Node head;

    static class Node{
        int data;
        Node prev;
        Node next;

        Node(int data){
            this.data= data;
            prev=null;
            next= null;
        }
    }
    private static void insertAtHead(int i, doublyLinkedList head2) {
        Node node = new Node(i);
        head2.head=node;
    }
    private static void insert(int i, doublyLinkedList head2) {
        Node node = new Node(i);
        node.next=null;

        Node temp = head2.head;
        while (temp.next!=null) {
            temp=temp.next;
        }
        temp.next=node;
        
        node.prev= temp;
    }

    static void print(doublyLinkedList head){
        Node temp = head.head;

        while (temp!=null) {
            System.out.print(temp.data+ " ");
            temp=temp.next;
        }
        System.err.println();
    }



    public static void main(String[] args) {
        doublyLinkedList head= new doublyLinkedList();

        insertAtHead(0, head);
        print(head);
        insert(1,head);
        print(head);
        insert(1,head);
        print(head);
        insert(1,head);
        print(head);

    }

    



    
}
