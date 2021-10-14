/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se160076.vaccinemanagement;

/**
 *
 * @author Tony
 */
public class Student {
    private final String studentId;
    private String studentName;
    
    public Student(String studentId) {
        this.studentId = studentId;
    }

    public Student(String studentId, String studentName) {
        this.studentId = studentId;
        this.studentName = studentName;
    }

    public String getStudentId() {
        return studentId;
    }

    //public void setStudentId(String studentId) {
    //    this.studentId = studentId;
    //}

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
}
