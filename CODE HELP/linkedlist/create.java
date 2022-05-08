
public class create {
    Node head;

    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }

        Node(int data, Node node) {
            this.data = data;
            this.next = node;
        }
    }

    private static void insertAtHead(int i, create create) {
        Node node = new Node(i);
        if (create.head == null) {
            create.head = node;
        } else {
            node.next = create.head;
            create.head = node;
        }
    }

    static void insert(int data, create create) {
        Node node = new Node(data);

        if (create.head == null) {
            System.out.println("in");
            create.head = node;
        } else {
            Node last = create.head;
            while (last.next != null) {
                System.out.println("out" + last.data);
                last = last.next;
            }
            last.next = node;
        }
    }

    static void deleteValue(int data, create create) {
        Node temp = create.head;
        Node prev = create.head;

        while (temp.data != data) {
            prev = temp;
            temp = temp.next;
        }
        prev.next = temp.next;
    }

    static void reverse(create create) {
        Node head = create.head;
        Node prev = null;
        while (head != null) {
            Node temp = head.next;
            head.next = prev;
            prev = head;

            head = temp;

        }

        create.head = prev;
    }

    private static void count(create create) {
        Node head = create.head;
        int count = 0;
        while (head != null) {
            count++;
            head = head.next;
        }
        count = (count >> 1)+1;
        System.out.println(count);
        head = create.head;
        System.out.println(head.data);
        while (count >1) {
            head=head.next;
            count--;
        }
        System.out.println(head.data);
    }

    static void print(create create) {
        Node node = create.head;
        while (node != null) {
            System.out.print(node.data + " ");
            node = node.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        create create = new create();

        insert(1, create);
        insert(2, create);
        insert(3, create);
        insert(4, create);
        insert(5, create);
        print(create);

        insertAtHead(0, create);
        print(create);

        deleteValue(3, create);
        print(create);

        reverse(create);
        print(create);

        count(create);
    }

}
