
public class insert {

    public static void main(String[] args) {
        linkedlist l = new linkedlist();
        l.add(10);
        l.add(20);
        l.add(12);
        l.add(23);
        l.add(25);
        l.add(30);

        l.print();
        l.insertafter(200, 2);
        l.print();
        l.delete(3);
        l.print();

        l.reverse();
        l.print();

    }
}

class linkedlist {
    Node head;

    class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            next = null;
        }
    }

    public void add(int data) {
        Node new_node = new Node(data);
        new_node.next = null;

        if (head == null) {
            head = new_node;
        } else {
            Node n = head;
            while (n.next != null) {
                n = n.next;
            }
            n.next = new_node;
        }
    }

    public void insertafter(int data, int pos) {
        Node addafter = new Node(data);
        Node prev = head;
        for (int i = 0; i < pos; i++) {
            prev = prev.next;
        }
        addafter.next = prev.next;
        prev.next = addafter;
    }

    public void delete(int pos) {
        Node n = head;
        if (pos == 0) {
            head = head.next;
            return;
        }

        for (int i = 0; i < pos - 1; i++) {
            n = n.next;
        }
        n.next = n.next.next;
    }

    public void reverse(){
        Node cur = head;
        Node prev = null;
        while (cur!=null){
            Node temp = cur.next;
            cur.next=prev;
            prev=cur;
            cur=temp;
        }
        head=prev;
    }

    public void print() {
        Node node = head;
        System.out.println();
        while (node.next != null) {
            System.out.print(node.data + "---> ");
            node = node.next;
        }
        System.out.print(node.data);

    }
}
