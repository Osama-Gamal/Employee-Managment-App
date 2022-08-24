package com.example.stafftask;

public class MyLinkedList {

    // Class Node
    public class Node {
        public Employee value;
        public Node next;
    }

    // Data fields
    public Node head;
    public Node tail;
    public static int filled;

    // Constructor
    public MyLinkedList() {
        head = null;
        filled = 0;
    }

    // Addition Method
    public void insert(Employee element) {
        if (isEmpty()) {
            head = tail = new Node();
            head.value = element;
        } else {
            tail = tail.next = new Node();
            tail.value = element;
        } filled++;
    }

    // Deletion Method
    public boolean remove(String name) {
        if(isEmpty())
            System.out.println("LinkedList is Underflow");
        else {
            if(head.value.name.equals(name)) {
                head = head.next;
                filled--;
                return true;
            }
            Node pointer = head;
            while(pointer.next != null) {
                if(pointer.next.value.name.equals(name)) {
                    pointer.next = pointer.next.next;
                    filled--;
                    return true;
                }
                pointer = pointer.next;
            }
        } return false;
    }
    public void clear(){
        head = null;
        filled = 0;

    }

    public void concat(MyLinkedList list) {
        for(int i = 0; i <= list.capacity(); i++)
            this.insert(list.get(i));
    }



    public int linear_search(String text) {
        Node pointer = head;
        int counter = 0;

        while(pointer != null) {
            if(pointer.value.name.equals(text) ||  (pointer.value.ID+"").equals(text)) {
                return counter;
            }
            pointer = pointer.next;
            counter++;
        }

        return -1;
    }

    public Node binary_search(String value) {
        Node start = head;
        Node last = null;
        do
        {
            Node mid = middleNode(start, last);
            if (mid == null)
                return null;
            if (mid.value.ID == Integer.parseInt(value))
                return mid;
            else if (mid.value.ID < Integer.parseInt(value))
            {
                start = mid.next;
            }
            // If the value is more than mid.
            else
                last = mid;
        } while (last == null || last != start);
        // value not present
        return null;
    }


    public Node middleNode(Node start, Node last) {
        if (start == null)
            return null;

        Node slow = start;
        Node fast = start.next;

        while (fast != last)
        {
            fast = fast.next;
            if (fast != last)
            {
                slow = slow.next;
                fast = fast.next;
            }
        }
        return slow;
    }

    public int capacity() {
        return filled;
    }

    public Employee get(int position) {
        if(isEmpty())
            System.out.println("LinkedList is Underflow");
        else if (!isValidPosition(position))
            System.out.println("Invalid range");
        else {
            Node ptr = head;
            for (int i = 0; i < position; i++)
                ptr = ptr.next;
            return ptr.value;
        } return null;
    }

    public void display() {
        if(isEmpty())
            System.out.println("NO ITEMS TO DISPLAY");

        for(int i = 0; i < filled; i++)
            System.out.println(get(i).toString());
    }
    public Employee displayInTableView() {
        if(isEmpty())
            return null;
        for(int i = 0; i < filled; i++)
            return get(i);

        return null;
    }

   /* public void bubble_sort() {
        if (filled > 1) {
            boolean wasChanged;
            do {
                Node current = head;
                Node previous = null;
                Node next = head.next;
                wasChanged = false;

                while ( next != null ) {
                    if (current.value.name.compareTo(next.value.name) > 0) {

                        wasChanged = true;

                        if ( previous != null ) {
                            Node sig = next.next;

                            previous.next = next;
                            next.next = current;
                            current.next = sig;
                        } else {
                            Node sig = next.next;

                            head = next;
                            next.next = current;
                            current.next = sig;
                        }

                        previous = next;
                        next = current.next;
                    } else {
                        previous = current;
                        current = next;
                        next = next.next;
                    }
                }
            } while( wasChanged );
        }
    }*/

    public void bubble_sort() {
        if (filled > 1) {
            boolean wasChanged;
            do {
                Node current = head;
                Node previous = null;
                Node next = head.next;
                wasChanged = false;

                while ( next != null ) {
                    if (current.value.getID() > next.value.getID()) {

                        wasChanged = true;

                        if ( previous != null ) {
                            Node sig = next.next;

                            previous.next = next;
                            next.next = current;
                            current.next = sig;
                        } else {
                            Node sig = next.next;

                            head = next;
                            next.next = current;
                            current.next = sig;
                        }

                        previous = next;
                        next = current.next;
                    } else {
                        previous = current;
                        current = next;
                        next = next.next;
                    }
                }
            } while( wasChanged );
        }
    }

    public void selectionSort()
    {
        Node auxiliary = this.head;
        Node temp = null;
        Node node = null;
        // Execute linked list node
        while (auxiliary != null)
        {
            node = auxiliary;
            temp = auxiliary.next;
            // Find the minimum node
            while (temp != null)
            {
                if (node.value.getID() > temp.value.getID())
                {
                    node = temp;
                }
                // Visit to next node
                temp = temp.next;
            }
            if (auxiliary.value.getID() > node.value.getID())
            {
                // Transfer minimum value to initial position
                // Swap the node value
                swapData(auxiliary, node);
            }
            // Visit to next node
            auxiliary = auxiliary.next;
        }
    }

    public void swapData(Node a, Node b)
    {
        Employee temp = a.value;
        a.value = b.value;
        b.value = temp;
    }


    public boolean isEmpty() {
        return head == null;
    }

    private boolean isValidPosition(int position) {
        return position >= 0 && position <= filled - 1;
    }
}