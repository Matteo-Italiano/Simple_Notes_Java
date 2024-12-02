package org.example;

public class Student {
    int rollno;
    String name;
    String city;

    Student(int rollno, String name, String city) {
        this.rollno = rollno;
        this.name = name;
        this.city = city;
    }

    public String toString() {
        return  rollno + " " + name + " " + city;
    }

    public static void main(String[] args) {
        Student s1 = new Student(1, "John", "San Francisco");
        Student s2 = new Student(2, "Jane", "San Francisco");


    }
}

