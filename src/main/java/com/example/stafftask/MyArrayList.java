package com.example.stafftask;
public class MyArrayList {

    // Data Fields
    private static final int max_size = 1000;
    private Employee[] my_array;
    public int top;

    // Constructor
    public MyArrayList () {
        my_array = new Employee[max_size];
        top = -1;
    }

    // Addition Method
    public boolean insert(Employee teacher) {
        if(isFull()) return false;

        my_array[++top] = teacher;
        return true;
    }

    // Concat Method
    public boolean concat(MyArrayList array) {
        if((array.capacity() + capacity()) > max_size)
            return false;

        for(int i = 0; i <= array.top; i++)
            this.insert(array.my_array[i]);
        array.top = -1;
        return true;
    }

    // Deletion Method
    public boolean delete(String name) {
        if(isEmpty()) return false;

        for(int i = 0; i <= top; i++) {
            if(my_array[i].name.equals(name)) {
                for(int j = i; j <= top; j++)
                    my_array[j] = my_array[j+1];
                top--;
                return true;
            }
        }

        return false;
    }
    public void clear() {
        for (int i = 0; i < top; i++)
            my_array[i] = null;
        top = -1;
    }

    // Display Method
    public void display() {
        if(isEmpty())
            System.out.println("NO ITEMS TO DISPLAY");

        for(int i = 0; i <= top; i++)
            System.out.println(my_array[i].toString());
    }

    public Employee[] displayInArray() {
        return my_array;
    }


    public Employee get(int index) {
        if(index > top || top < 0)
            return null;
        return my_array[index];
    }

    // Linear Search
    public int linear_search(String text) {
        if(isEmpty()) return -1;

        for(int i = 0; i <= top; i++)
            if(my_array[i].name.equals(text) ||  (my_array[i].ID+"").equals(text))
                return i;

        return -1;
    }

    // Binary Search
    /*public int binary_search(String name) {
        if(isEmpty()) return -1;

        if(!isNumeric(name)) {
            int l = 0, r = top, m;
            while(l <= r) {
                m = (l + r) / 2;
                if(my_array[m].name.equals(name))
                    return m;
                else if(my_array[m].name.compareTo(name) < 0)
                    l = m + 1;
                else
                    r = m - 1;
            }

        }else{

            int l = 0, r = top, m;
            while(l <= r) {
                m = (l + r) / 2;
                if(my_array[m].ID == Integer.parseInt(name))
                    return m;
                else if((my_array[m].ID < Integer.parseInt(name))){
                    l = m + 1;
                }
                else
                    r = m - 1;
            }
            return -1;

        }

        return -1;

    }*/

    public int binary_search(String name) {
        if(isEmpty()) return -1;

        int l = 0, r = top, m;
        while(l <= r) {
            m = (l + r) / 2;
            if(my_array[m].ID == Integer.parseInt(name))
                return m;
            else if((my_array[m].ID < Integer.parseInt(name))){
                l = m + 1;
            }
            else
                r = m - 1;
        }
        return -1;

    }



    /******* Capacity *******/
    // Full Method
    public boolean isFull() {
        if(top >= 999)
            return true;
        else
            return false;

        //return top == 999;
    }

    // Empty Method
    public boolean isEmpty() {
        return top == -1;
    }

    // Capacity Method
    public int capacity() {
        return top + 1;
    }

    // Bubble Sort
    public void bubble_sort() {
        for(int pass = 0; pass <= top; pass++) {
            for(int step = 0; step <= top - pass - 1; step++) {
                if(my_array[step].name.compareTo(my_array[step+1].name) > 0) {
                    String temp = my_array[step].name;
                    my_array[step].name = my_array[step+1].name;
                    my_array[step+1].name = temp;
                }
            }
        }
    }
    public void bubbleSort()
    {
        for (int i = 0; i <= top - 1; i++)
            for (int j = 0; j <= top - i - 1; j++)
                if (my_array[j].getID() > my_array[j + 1].getID()) {
                    System.out.println(my_array[j + 1].getID());
                    // swap arr[j+1] and arr[j]
                    int temp = my_array[j].ID;
                    my_array[j] = my_array[j + 1];
                    my_array[j + 1].ID = temp;
                }
    }
    public void bubble_sort_for_id() {
        for(int pass = 0; pass <= top; pass++)
            for(int step = 0; step < pass; step++)
                if(my_array[step].getID() < my_array[step+1].getID()) {
                    Employee temp = my_array[step];
                    my_array[step] = my_array[step+1];
                    my_array[step+1] = temp;
                }
    }

    public void selection_sort_for_id() {
        for(int pass = 1; pass <= top; pass++) {
            int index = pass - 1;
            Employee key = my_array[pass];

            while(index >= 0 && my_array[index].getID() < my_array[pass].getID()) {
                my_array[index+1] = my_array[index];
                index--;
            }

            my_array[index+1] = key;
        }
    }


    // Insertion Sort
    public void insertion_sort() {
        for(int pass = 1; pass <= top; pass++) {
            Employee key = my_array[pass];
            int index = pass - 1;

            while(index >= 0 && key.name.compareTo(my_array[pass].name) > 0) {
                my_array[index+1] = my_array[index];
                index--;
            }

            my_array[index+1] = key;
        }
    }

    public void selection_sort() {
        for(int pass = 0; pass <= top; pass++) {
            int smallest_index = pass;

            for(int index = pass; index <= top; index++) {
                if(my_array[smallest_index].compareTo(my_array[index]))
                    smallest_index = index;
            }

            if(smallest_index != pass) {
                Employee temp = my_array[smallest_index];
                my_array[smallest_index] = my_array[pass];
                my_array[pass] = temp;
            }
        }
    }




}