package com.example.iocexample.pojo;

public class Student {
    private int no;
    private String name;

    public Student() {

    }

    public Student(int no, String name) {
        this.no = no;
        this.name = name;
        System.out.println("Constructor - Student(int no, String name) " + this);
    }

    public static Student getStudent(int no, String name) {
        return new Student(no, name);
    }

    public Student duplicate() {
        return new Student(no, name);
    }

    public void setNo(int no) {
        this.no = no;
    }

    public void setName(String name) {
        this.name = name;
    }

    private void destroy() {
        System.out.println("destroy: " + this);
    }

    @Override
    public String toString() {
        return "Student {" +
                " hashCode=" + hashCode() +
                ", no=" + no +
                ", name='" + name + '\'' +
                " }";
    }
}
