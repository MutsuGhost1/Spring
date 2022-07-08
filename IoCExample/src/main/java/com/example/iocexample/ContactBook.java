package com.example.iocexample;

import java.util.List;

public class ContactBook {
    private List<Student> studentList;

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    @Override
    public String toString() {
        return "ContactBook {" +
                "studentList=" + studentList +
                " }";
    }
}
