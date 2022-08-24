package com.example.stafftask;

public class Employee {

    public static int contract_ID;
    public int ID;
    // Data fields
    public String name;
    public int salary;
    public String department;
    public String dateofbirth;



    // Constructor
    public Employee(int ID,String name,String department , int salary, String dateofbirth) {
        ++contract_ID;
        this.ID = ID;
        this.name = name;
        this.salary = salary;
        this.department = department;
        this.dateofbirth = dateofbirth;

    }

    // Update Method
    public void update(String new_name ,String new_phone) {
        this.name = new_name;
    }

    // toString Method
    public String toString() {
        return  name + "-" + department;
    }

    // Equals Method
    public boolean equals(Object teacher) {
        Employee pointer = (Employee) teacher;

        return this.name.equals(pointer.name)
                && this.ID == pointer.ID;
    }

    public String getName(){
        return name;
    }
    public int getID(){return ID;}
    public int getSalary(){return salary;}
    public String getDepartment(){return department;}
    public String getDateofbirth(){return dateofbirth;}


    public void clearValues(){
        this.name = "";
        this.salary = 0;
        this.department = "";
        this.dateofbirth = "";
    }

    public String line() {
        return  ID + "-" +
                name + "-" +
                department + "-" +
                salary + "-" +
                dateofbirth ;
    }
    public boolean compareTo(Employee second_employee) {
        return this.name.compareTo(second_employee.name) > 0;
    }


}