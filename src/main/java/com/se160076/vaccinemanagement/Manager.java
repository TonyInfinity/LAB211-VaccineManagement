/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se160076.vaccinemanagement;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

/**
 *
 * @author Tony
 */
public final class Manager {

    public final static void printMenu() {
        System.out.println("\n|------------------------------------- VACCINE MANAGEMENT -------------------------------------|");
        System.out.println("| 1. Show All Injections.                                                                      |");
        System.out.println("| 2. Add New Injections.                                                                       |");
        System.out.println("| 3. Update Injections.                                                                        |");
        System.out.println("| 4. Delete Injections                                                                         |");
        System.out.println("| 5. Search Injections Based On Student ID.                                                    |");
        System.out.println("| 6. Quit.                                                                                     |");
        System.out.println("|----------------------------------------------------------------------------------------------|\n");
    }

    public final static void printInjections(ArrayList<Injection> il, ArrayList<Student> sl, ArrayList<Vaccine> vl) {
        if (il.isEmpty() || sl.isEmpty() || vl.isEmpty()) {
            System.out.println("List Is Empty.");
            return;
        }

        Collections.sort(il, new Comparator<Injection>() {
            @Override
            public int compare(Injection i1, Injection i2) {
                return i1.getInjectionId().compareTo(i2.getInjectionId());
            }
        });

        System.out.printf("|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|\n");
        System.out.printf("| %-20s | %-20s | %-20s | %-20s | %-20s | %-25s | %-25s | %-25s | %-25s | \n", "Injection ID", "Student ID", "Student Name", "Vaccine ID", "Vaccine Name", "1st Injection Location", "1st Injection Date", "2nd Injection Location", "2nd Injection Date");
        System.out.printf("|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|\n");

        for (Injection i : il) {
            System.out.printf("| %-20s | %-20s |", i.getInjectionId(), i.getStudentId());
            for (int j = 0; j < sl.size(); j++) {
                if (i.getStudentId().equals(sl.get(j).getStudentId())) {
                    System.out.printf(" %-20s |", sl.get(j).getStudentName());
                }
            }
            System.out.printf(" %-20s |", i.getVaccineId());
            for (int f = 0; f < vl.size(); f++) {
                if (i.getVaccineId().equals(vl.get(f).getVaccineId())) {
                    System.out.printf(" %-20s |", vl.get(f).getVaccineName());
                }
            }
            System.out.printf(" %-25s | %-25s | %-25s | %-25s |\n", i.getFirstInjectionLocation(), i.getFirstInjectionDate(), i.getSecondInjectionLocation(), i.getSecondInjectionDate());
        }

        System.out.printf("|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|\n");
    }

    public final static void addInjections(ArrayList<Injection> il, ArrayList<Student> sl, ArrayList<Vaccine> vl) {
        do {
            String injectionId = "";
            String studentId = "";
            String vaccineId = "";
            String firstInjectionLocation = "";
            String firstInjectionDate = "";
            String secondInjectionLocation = "-";
            String secondInjectionDate = "-";

            do {
                System.out.println("Enter Injection Id: ");
                injectionId = Validator.validateInjectionId();
            } while (Validator.validateInjectionId(il, injectionId));

            do {
                System.out.println("Enter Student Id: ");
                studentId = Validator.validateString();
            } while (!Validator.validateStudentId(sl, il, studentId));

            System.out.println("Enter Vaccine Id: ");
            int number = 1;
            for (Vaccine v : vl) {
                System.out.println(number + ". " + v.getVaccineName());
                number++;
            }
            int choice = Validator.validateMenuChoice(1, 6);
            switch (choice) {
                case 1:
                    vaccineId = "2";
                    break;
                case 2:
                    vaccineId = "4";
                    break;
                case 3:
                    vaccineId = "6";
                    break;
                case 4:
                    vaccineId = "7";
                    break;
                case 5:
                    vaccineId = "9";
                    break;
                case 6:
                    vaccineId = "10";
                    break;
                default:
                    break;
            }

            do {
                System.out.println("Enter 1st Injection Location: ");
                firstInjectionLocation = Validator.validateFirstLocation();
            } while (!Validator.validateFirstLocation(firstInjectionLocation));

            do {
                System.out.println("Enter 1st Injection Date: ");
                firstInjectionDate = Validator.validateString();
            } while (!Validator.validateDate(firstInjectionDate));

            do {
                System.out.println("Enter 2nd Injection Location: ");
                secondInjectionLocation = Validator.validateSecondLocation();
            } while (!Validator.validateSecondLocation(secondInjectionLocation));

            if (!secondInjectionLocation.isEmpty()) {
                do {
                    System.out.println("Enter 2nd Injection Date: ");
                    secondInjectionDate = Validator.inputString();
                } while (!Validator.validateDate(secondInjectionDate, firstInjectionDate));
            }

            if (secondInjectionLocation.isEmpty() || secondInjectionDate.isEmpty()) {
                il.add(new Injection(injectionId, studentId.toUpperCase(), vaccineId, firstInjectionLocation, firstInjectionDate, "N/a", "N/a"));
            } else {
                il.add(new Injection(injectionId, studentId.toUpperCase(), vaccineId, firstInjectionLocation, firstInjectionDate, secondInjectionLocation, secondInjectionDate));
            }
            System.out.println("Add Injection Successfull.");
        } while (Validator.checkInputYN());

        writeInjectionFile(il);
    }

    public final static void updateInjections(ArrayList<Injection> il) {
        do {
            String injectionId = "";
            String secondInjectionLocation = "";
            String secondInjectionDate = "";
            boolean completed = true;
            do {
                System.out.println("Enter Injection Id To Update: ");
                injectionId = Validator.validateString();
                if (!Validator.validateInjectionIdToUpdate(il, injectionId)) {
                    System.out.println("\nId Does Not Exists.");
                }
            } while (!Validator.validateInjectionIdToUpdate(il, injectionId));

            for (Injection i : il) {
                if (i.getInjectionId().equalsIgnoreCase(injectionId)) {
                    if (i.getSecondInjectionLocation().equalsIgnoreCase("N/a") && i.getSecondInjectionDate().equalsIgnoreCase("N/a")) {
                        do {
                            System.out.println("Enter 2nd Injection Location: ");
                            secondInjectionLocation = Validator.validateFirstLocation();
                        } while (!Validator.validateFirstLocation(secondInjectionLocation));

                        do {
                            System.out.println("Enter 2nd Injection Date: ");
                            secondInjectionDate = Validator.validateString();
                        } while (!Validator.validateDate(secondInjectionDate, i.getFirstInjectionDate()));

                        i.setSecondInjectionLocation(secondInjectionLocation);
                        i.setSecondInjectionDate(secondInjectionDate);

                        System.out.println("Update Injection Successfull.");
                        completed = false;
                    }
                }
            }
            if (completed == true) {
                System.out.println("Student Has Completed 2 Injections.");
            }

        } while (Validator.checkInputYN());
        writeInjectionFile(il);
    }

    public final static void deleteInjections(ArrayList<Injection> il) {
        do {
            String injectionId = "";
            do {
                System.out.println("Enter Injection Id To Delete: ");
                injectionId = Validator.validateString();
                if (!Validator.validateInjectionIdToUpdate(il, injectionId)) {
                    System.out.println("Injection Id Does Not Exist.");
                }
            } while (!Validator.validateInjectionIdToUpdate(il, injectionId));

            for (int i = 0; i < il.size(); i++) {
                if (il.get(i).getInjectionId().equalsIgnoreCase(injectionId)) {
                    if (Validator.deleteConfirmation()) {
                        il.remove(i);
                        System.out.println("Delete Injection Successful.\n");
                    } else {
                        return;
                    }
                }
            }
        } while (Validator.checkInputYN());
        writeInjectionFile(il);
    }

    public final static void searchInjections(ArrayList<Injection> il, ArrayList<Student> sl, ArrayList<Vaccine> vl) {
        do {
            System.out.println("1. Search By Student ID.");
            System.out.println("2. Search By Student Name.");
            System.out.println("3. Search By Vaccine ID.");
            int choice = Validator.validateMenuChoice(1, 3);
            boolean found = false;
            switch (choice) {
                case 1:
                    System.out.println("Enter Student ID To Search: ");
                    String id = Validator.validateString();

                    for (Injection i : il) {
                        if (i.getStudentId().equalsIgnoreCase(id)) {
                            System.out.println("\n|-----------------------------------------------|");
                            System.out.printf("| Injection ID          : %-20s  |\n", i.getInjectionId());
                            System.out.printf("| Student ID            : %-20s  |\n", i.getStudentId());
                            for (int j = 0; j < sl.size(); j++) {
                                if (i.getStudentId().equals(sl.get(j).getStudentId())) {
                                    System.out.printf("| Student Name          : %-20s  |\n", sl.get(j).getStudentName());
                                }
                            }
                            System.out.printf("| Vaccine ID            : %-20s  |\n", i.getVaccineId());
                            for (int f = 0; f < vl.size(); f++) {
                                if (i.getVaccineId().equals(vl.get(f).getVaccineId())) {
                                    System.out.printf("| Vaccine Name          : %-20s  |\n", vl.get(f).getVaccineName());
                                }
                            }
                            System.out.printf("| 1st Injection Location: %-20s  |\n", i.getFirstInjectionLocation());
                            System.out.printf("| 1st Injection Date    : %-20s  |\n", i.getFirstInjectionDate());
                            System.out.printf(
                                    "| 2nd Injection Location: %-20s  |\n", i.getSecondInjectionLocation()
                            );
                            System.out.printf("| 2nd Injection Date    : %-20s  |\n", i.getSecondInjectionDate());
                            System.out.println("|-----------------------------------------------|");
                            found = true;
                        }
                    }
                    break;

                case 2:
                    System.out.println("Enter Student Name To Search: ");
                    String name = Validator.validateString();
                    String idToSearch = "";
                    for (Student s : sl) {
                        if (s.getStudentName().equalsIgnoreCase(name)) {
                            idToSearch = s.getStudentId();
                        }
                    }

                    for (Injection i : il) {
                        if (i.getStudentId().equalsIgnoreCase(idToSearch)) {
                            System.out.println("\n|-----------------------------------------------|");
                            System.out.printf("| Injection ID          : %-20s  |\n", i.getInjectionId());
                            System.out.printf("| Student ID            : %-20s  |\n", i.getStudentId());
                            for (int j = 0; j < sl.size(); j++) {
                                if (i.getStudentId().equals(sl.get(j).getStudentId())) {
                                    System.out.printf("| Student Name          : %-20s  |\n", sl.get(j).getStudentName());
                                }
                            }
                            System.out.printf("| Vaccine ID            : %-20s  |\n", i.getVaccineId());
                            for (int f = 0; f < vl.size(); f++) {
                                if (i.getVaccineId().equals(vl.get(f).getVaccineId())) {
                                    System.out.printf("| Vaccine Name          : %-20s  |\n", vl.get(f).getVaccineName());
                                }
                            }
                            System.out.printf("| 1st Injection Location: %-20s  |\n", i.getFirstInjectionLocation());
                            System.out.printf("| 1st Injection Date    : %-20s  |\n", i.getFirstInjectionDate());
                            System.out.printf(
                                    "| 2nd Injection Location: %-20s  |\n", i.getSecondInjectionLocation()
                            );
                            System.out.printf("| 2nd Injection Date    : %-20s  |\n", i.getSecondInjectionDate());
                            System.out.println("|-----------------------------------------------|");
                            found = true;
                        }
                    }
                    break;

                case 3:
                    System.out.println("Enter Vaccine ID To Search: ");
                    String vaccineId = Validator.validateString();
                    for (Injection i : il) {
                        if (i.getVaccineId().equalsIgnoreCase(vaccineId)) {
                            System.out.println("\n|-----------------------------------------------|");
                            System.out.printf("| Injection ID          : %-20s  |\n", i.getInjectionId());
                            System.out.printf("| Student ID            : %-20s  |\n", i.getStudentId());
                            for (int j = 0; j < sl.size(); j++) {
                                if (i.getStudentId().equals(sl.get(j).getStudentId())) {
                                    System.out.printf("| Student Name          : %-20s  |\n", sl.get(j).getStudentName());
                                }
                            }
                            System.out.printf("| Vaccine ID            : %-20s  |\n", i.getVaccineId());
                            for (int f = 0; f < vl.size(); f++) {
                                if (i.getVaccineId().equals(vl.get(f).getVaccineId())) {
                                    System.out.printf("| Vaccine Name          : %-20s  |\n", vl.get(f).getVaccineName());
                                }
                            }
                            System.out.printf("| 1st Injection Location: %-20s  |\n", i.getFirstInjectionLocation());
                            System.out.printf("| 1st Injection Date    : %-20s  |\n", i.getFirstInjectionDate());
                            System.out.printf(
                                    "| 2nd Injection Location: %-20s  |\n", i.getSecondInjectionLocation()
                            );
                            System.out.printf("| 2nd Injection Date    : %-20s  |\n", i.getSecondInjectionDate());
                            System.out.println("|-----------------------------------------------|");
                            found = true;
                        }
                    }
                    break;

                default:
                    break;
            }
            if (found == false) {
                System.out.println("No Records Found.");
            }
        } while (Validator.checkInputYN());
    }

    public final static void loadStudentFile(ArrayList<Student> sl) {
        try {
            File f = new File("student.txt");
            if (!f.exists()) {
                return;
            }
            FileReader fr = new FileReader(f);
            BufferedReader bf = new BufferedReader(fr);
            String student;

            while ((student = bf.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(student, ";");

                String studentId = st.nextToken();
                String studentName = st.nextToken();
                if (!Validator.studentIdExists(sl, studentId)) {
                    sl.add(new Student(studentId, studentName));
                }
            }

            bf.close();
            fr.close();
        } catch (IOException e) {
            System.out.println("An Error Occurred.");
        }
    }

    public final static void loadVaccineFile(ArrayList<Vaccine> vl) {
        try {
            File f = new File("vaccine.txt");
            if (!f.exists()) {
                return;
            }
            FileReader fr = new FileReader(f);
            BufferedReader bf = new BufferedReader(fr);
            String vaccine;

            while ((vaccine = bf.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(vaccine, ";");

                String vaccineId = st.nextToken();
                String vaccineName = st.nextToken();

                if (!Validator.vaccineIdExists(vl, vaccineId)) {
                    vl.add(new Vaccine(vaccineId, vaccineName));
                }
            }

            bf.close();
            fr.close();
        } catch (IOException e) {
            System.out.println("An Error Occurred.");
        }
    }

    public final static void loadInjectionFile(ArrayList<Injection> il) {
        try {
            File f = new File("injection.txt");
            if (!f.exists()) {
                return;
            }
            FileReader fr = new FileReader(f);
            BufferedReader bf = new BufferedReader(fr);
            String injection;

            while ((injection = bf.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(injection, ";");

                String injectionId = st.nextToken();
                String studentId = st.nextToken();
                String vaccineId = st.nextToken();
                String firstInjectionLocation = st.nextToken();
                String firstInjectionDate = st.nextToken();

                String secondInjectionLocation = st.hasMoreTokens() ? st.nextToken() : "-";
                String secondInjectionDate = st.hasMoreTokens() ? st.nextToken() : "-";

                if (!Validator.injectionIdExists(il, injectionId)) {
                    il.add(new Injection(injectionId, studentId, vaccineId, firstInjectionLocation, firstInjectionDate, secondInjectionLocation, secondInjectionDate));
                }

            }

            bf.close();
            fr.close();
        } catch (IOException e) {
            System.out.println("An Error Occurred.");
        }
    }

    public static void writeInjectionFile(ArrayList<Injection> il) {
        try {
            FileWriter fw = new FileWriter("injection.txt");
            PrintWriter pw = new PrintWriter(fw);
            for (Injection i : il) {
                pw.println(i.toString());
            }
            pw.close();
            fw.close();
        } catch (IOException e) {
            System.out.println("An Error Occurred.");
            e.printStackTrace();
        }
    }

}
