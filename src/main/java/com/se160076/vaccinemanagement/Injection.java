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
public class Injection extends Student implements Comparable<Injection> {

    private final String injectionId;
    private final String studentId;
    private final String vaccineId;
    private String firstInjectionLocation;
    private String firstInjectionDate;
    private String secondInjectionLocation;
    private String secondInjectionDate;

    public Injection(String injectionId, String studentId, String vaccineId, String firstInjectionLocation, String firstInjectionDate, String secondInjectionLocation, String secondInjectionDate) {
        super(studentId);
        this.injectionId = injectionId;
        this.studentId = studentId;
        this.vaccineId = vaccineId;
        this.firstInjectionLocation = firstInjectionLocation;
        this.firstInjectionDate = firstInjectionDate;
        this.secondInjectionLocation = secondInjectionLocation;
        this.secondInjectionDate = secondInjectionDate;
    }

    public Injection(String injectionId, String studentId, String studentName, String vaccineId, String firstInjectionLocation, String firstInjectionDate, String secondInjectionLocation, String secondInjectionDate) {
        super(studentId, studentName);
        this.injectionId = injectionId;
        this.studentId = studentId;
        this.vaccineId = vaccineId;
        this.firstInjectionLocation = firstInjectionLocation;
        this.firstInjectionDate = firstInjectionDate;
        this.secondInjectionLocation = secondInjectionLocation;
        this.secondInjectionDate = secondInjectionDate;
    }

    public String getInjectionId() {
        return injectionId;
    }

    //public void setInjectionId(String injectionId) {
    //    this.injectionId = injectionId;
    //}
    @Override
    public String getStudentId() {
        return studentId;
    }

    //@Override
    //public void setStudentId(String studentId) {
    //   this.studentId = studentId;
    //}
    public String getVaccineId() {
        return vaccineId;
    }

    //public void setVaccineId(String vaccineId) {
    //    this.vaccineId = vaccineId;
    //}
    public String getFirstInjectionLocation() {
        return firstInjectionLocation;
    }

    public void setFirstInjectionLocation(String firstInjectionLocation) {
        this.firstInjectionLocation = firstInjectionLocation;
    }

    public String getFirstInjectionDate() {
        return firstInjectionDate;
    }

    public void setFirstInjectionDate(String firstInjectionDate) {
        this.firstInjectionDate = firstInjectionDate;
    }

    public String getSecondInjectionLocation() {
        return secondInjectionLocation;
    }

    public void setSecondInjectionLocation(String secondInjectionLocation) {
        this.secondInjectionLocation = secondInjectionLocation;
    }

    public String getSecondInjectionDate() {
        return secondInjectionDate;
    }

    public void setSecondInjectionDate(String secondInjectionDate) {
        this.secondInjectionDate = secondInjectionDate;
    }

    @Override
    public int compareTo(Injection i) {
        return i.getInjectionId().compareTo(getInjectionId());
    }

    @Override
    public String toString() {
        return getInjectionId() + ";" + getStudentId() + ";" + getVaccineId() + ";" + getFirstInjectionLocation() + ";" + getFirstInjectionDate() + ";" + getSecondInjectionLocation() + ";" + getSecondInjectionDate();
    }
}
