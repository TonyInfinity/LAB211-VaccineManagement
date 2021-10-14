/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se160076.vaccinemanagement;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Tony
 */
public class Validator {

    private final static Scanner sc = new Scanner(System.in);

    public static int validateMenuChoice(int min, int max) {
        while (true) {
            try {
                int choice = Integer.parseInt(sc.nextLine().trim());
                if (choice < min || choice > max) {
                    throw new NumberFormatException();
                }
                return choice;
            } catch (NumberFormatException e) {
                System.out.println("\nChoice Must Be An Integer.");
                System.out.println("Please Enter Again\n");
                System.out.printf("Enter [%s, %s]: \n", min, max);
            }
        }
    }

    public static String validateFileName() {
        while (true) {
            String result = sc.nextLine().trim();
            Pattern pattern = Pattern.compile("^.*\\.(txt|TXT)$");
            Matcher matcher = pattern.matcher(result);
            if (result.isEmpty()) {
                System.out.println("\nFile Name Must Not Be Empty.");
                System.out.println("Enter: \n");
            } else {
                if (!matcher.matches()) {
                    System.out.println("\nFile Name Must End With .txt.");
                    System.out.println("Enter: \n");
                } else {
                    return result;
                }
            }
        }
    }

    public static String validateString() {
        while (true) {
            String result = sc.nextLine().trim();
            if (result.isEmpty() || result.isBlank()) {
                System.out.println("\nInput Must Not Be Empty.");
                System.out.println("Enter: \n");
            } else {
                return result;
            }
        }
    }

    public static String inputString() {
        String result = sc.nextLine().trim();
        return result;
    }

    public static boolean checkInputYN() {
        System.out.println("\nDo You Wish To Continue? (Y/n)\n");

        while (true) {
            String result = validateString();
            if (result.equalsIgnoreCase("Y")) {
                return true;
            } else if (result.equalsIgnoreCase("N")) {
                return false;
            } else {
                System.out.println("\nInput Must Be Either Y/y Or N/n.");
                System.out.println("Enter: \n");
            }
        }
    }

    // Load Files
    public static boolean injectionIdExists(ArrayList<Injection> il, String id) {
        for (Injection i : il) {
            if (i.getInjectionId().equalsIgnoreCase(id)) {
                return true;
            }
        }
        return false;
    }

    public static boolean vaccineIdExists(ArrayList<Vaccine> vl, String id) {
        for (Vaccine v : vl) {
            if (v.getVaccineId().equalsIgnoreCase(id)) {
                return true;
            }
        }
        return false;
    }

    public static boolean studentIdExists(ArrayList<Student> sl, String id) {
        for (Student s : sl) {
            if (s.getStudentId().equalsIgnoreCase(id)) {
                return true;
            }
        }
        return false;
    }

    public static String validateInjectionId() {
        while (true) {
            String result = validateString();
            Pattern pattern = Pattern.compile("[a-zA-Z0-9]*");
            Matcher matcher = pattern.matcher(result);
            if (!matcher.matches()) {
                System.out.println("\nId Must Not Contain Special Characters.");
                System.out.println("Enter: \n");
            } else {
                return result;
            }
        }
    }

    public static boolean validateInjectionId(ArrayList<Injection> il, String id) {
        for (Injection i : il) {
            if (i.getInjectionId().equalsIgnoreCase(id)) {
                System.out.println("\nId Must Not Already Exists.");
                System.out.println("Enter: \n");
                return true;
            }
        }
        return false;
    }

    public static boolean validateInjectionIdToUpdate(ArrayList<Injection> il, String id) {
        for (Injection i : il) {
            if (i.getInjectionId().equalsIgnoreCase(id)) {
                return true;
            }
        }
        return false;
    }

    public static boolean validateStudentId(ArrayList<Student> sl, ArrayList<Injection> il, String id) {
        boolean isStudent = false;
        boolean completedInjection = false;

        for (Student s : sl) {
            if (s.getStudentId().equalsIgnoreCase(id)) {
                isStudent = true;
                break;
            }
        }
        if (isStudent) {
            for (Injection i : il) {
                if (i.getStudentId().equalsIgnoreCase(id)) {
                    completedInjection = true;
                    break;
                }
            }
        }

        if (!isStudent) {
            System.out.println("\nId Must Be A Valid FPT University Student Id.");
            System.out.println("Enter: \n");
        }

        if (completedInjection) {
            System.out.println("\nStudent Has Completed 2 Injections.");
            System.out.println("Enter: \n");
        }

        if (isStudent == true && completedInjection == false) {
            return true;
        }

        return false;
    }

    public static String validateLocation() {
        while (true) {
            String result = validateString();
            Pattern pattern = Pattern.compile("[a-zA-Z ]*");
            Matcher matcher = pattern.matcher(result);
            if (!matcher.matches()) {
                System.out.println("\nLocation Must Only Contains Letters.");
                System.out.println("Enter: \n");
            } else {
                return result;
            }
        }
    }

    static String codes[] = {"An Giang",
        "Ba Ria Vung Tau",
        "Bac Lieu",
        "Bac Kan",
        "Bac Giang",
        "Bac Ninh",
        "Ben Tre",
        "Binh Duong",
        "Binh Dinh",
        "Binh Phuoc",
        "Binh Thuan",
        "Ca Mau",
        "Cao Bang",
        "Can Tho",
        "Da Nang",
        "Dak Lak",
        "Dak Nong",
        "Dien Bien",
        "Dong Nai",
        "Dong Thap",
        "Gia Lai",
        "Ha Giang",
        "Ha Nam",
        "Ha Noi",
        "Ha Tay",
        "Ha Tinh",
        "Hai Duong",
        "Hai Phong",
        "Hoa Binh",
        "Ho Chi Minh",
        "Hau Giang",
        "Hung Yen",
        "Khanh Hoa",
        "Kien Giang",
        "Kon Tum",
        "Lai Chau",
        "Lao Cai",
        "Lang Son",
        "Lam Dong",
        "Long An",
        "Nam Dinh",
        "Nghe An",
        "Ninh Binh",
        "Ninh Thuan",
        "Phu Tho",
        "Phu Yen",
        "Quang Binh",
        "Quang Nam",
        "Quang Ngai",
        "Quang Ninh",
        "Quang Tri",
        "Soc Trang",
        "Son La",
        "Tay Ninh",
        "Thai Binh",
        "Thai Nguyen",
        "Thanh Hoa",
        "Thua Thien Hue",
        "Tien Giang",
        "Tra Vinh",
        "Tuyen Quang",
        "Vinh Long",
        "Vinh Phuc",
        "Yen Bai"};

    public static String validateFirstLocation() {
        ArrayList<String> pl = new ArrayList<String>();
        pl.addAll(Arrays.asList(codes));
        boolean match = false;
        ArrayList<String> matches = new ArrayList<String>();

        while (true) {
            String choice = validateLocation();
            if (Arrays.asList(codes).contains(choice)) {
                return choice;
            } else {
                for (int i = 0; i < pl.size(); i++) {
                    if (pl.get(i).contains(choice)) {
                        matches.add(pl.get(i).toString());
                        match = true;
                    }
                }
                if (match == true) {
                    System.out.println("Did You Mean: ");
                    for (String m : matches) {
                        System.out.println(m);
                    }
                    matches.clear();
                }
                if (match == false) {
                    System.out.println("No Match Found.");
                }
                System.out.println("Enter: \n");
            }
        }
    }

    public static boolean validateFirstLocation(String result) {
        if (Arrays.asList(codes).contains(result)) {
            return true;
        }
        return false;
    }

    public static String validateSecondLocation() {
        ArrayList<String> pl = new ArrayList<String>();
        pl.addAll(Arrays.asList(codes));
        boolean match = false;
        ArrayList<String> matches = new ArrayList<String>();
        while (true) {
            String choice = inputString();
            if (Arrays.asList(codes).contains(choice) || choice.isEmpty()) {
                return choice;
            } else {
                for (int i = 0; i < pl.size(); i++) {
                    if (pl.get(i).contains(choice)) {
                        matches.add(pl.get(i).toString());
                        match = true;
                    }
                }
                if (match == true) {
                    System.out.println("Did You Mean: ");
                    for (String m : matches) {
                        System.out.println(m);
                    }
                    matches.clear();
                }
                if (match == false) {
                    System.out.println("No Match Found.");
                }
                System.out.println("Enter: \n");
            }
        }
    }

    public static boolean validateSecondLocation(String result) {
        if (Arrays.asList(codes).contains(result) || result.isEmpty()) {
            return true;
        }
        return false;
    }

    /*public static boolean validateDate(String result) {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        format.setLenient(false);

        try {
            Date date = format.parse(result);
            if (date.before(new Date())) {
                System.out.println("\nDate Must Not Be Expired Today.");
                System.out.println("Please Enter Again.\n");
                return false;
            }
        } catch (ParseException e) {
            System.out.println("\nDate Must Be In Valid Format (dd/MM/yyyy).");
            System.out.println("Please Enter Again.\n");
            return false;
        }
        return true;
    }*/
    public static boolean validateDate(String result) {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        format.setLenient(false);

        try {
            Date date = format.parse(result);
            if (date.before(new SimpleDateFormat("dd/MM/yyyy").parse("01/01/2021"))) {
                System.out.println("\nDate Must Not Preceed 01/01/2021.");
                System.out.println("Please Enter Again.\n");
                return false;
            }
        } catch (ParseException e) {
            System.out.println("\nDate Must Be In Valid Format (dd/MM/yyyy).");
            System.out.println("Please Enter Again.\n");
            return false;
        }
        return true;
    }

    public static boolean validateDate(String result, String firstDate) {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        format.setLenient(false);

        try {
            Date date1 = format.parse(firstDate);
            Date date2 = format.parse(result);
            long diffInMillies = Math.abs(date2.getTime() - date1.getTime());
            long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
            if (date2.before(date1)) {
                System.out.println("Second Injection Date Must Not Preceed First Injection Date.");
                return false;
            } else {
                if (diff < 28 || diff > 84) {
                    System.out.println("Second Injection Date Must Be Between 4 To 12 Weeks After The First Injection Date.");
                    System.out.println("Time Elapsed Since This Student's 1st Injection: " + (diff / 7) + " week(s) and " + (diff % 7) + " day(s).");
                    return false;
                }
            }

        } catch (ParseException e) {
            System.out.println("\nDate Must Be In Valid Format (dd/MM/yyyy).");
            System.out.println("Please Enter Again.\n");
            return false;
        }
        return true;
    }

    public static boolean validateSecondDate(String result) {
        if (!result.isEmpty()) {
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            format.setLenient(false);

            try {
                Date date = format.parse(result);
                if (date.before(new Date())) {
                    System.out.println("\nDate Must Not Be Expired Today.");
                    System.out.println("Please Enter Again.\n");
                    return false;
                }
            } catch (ParseException e) {
                System.out.println("\nDate Must Be In Valid Format (dd/MM/yyyy).");
                System.out.println("Please Enter Again.\n");
                return false;
            }
            return true;
        } else {
            return true;
        }
    }

    public static boolean deleteConfirmation() {
        System.out.println("\nDo You Wish To Delete This Injection? (Y/n)\n");

        while (true) {
            String result = validateString();
            if (result.equalsIgnoreCase("Y")) {
                return true;
            } else if (result.equalsIgnoreCase("N")) {
                return false;
            } else {
                System.out.println("\nInput Must Be Either Y/y Or N/n.");
                System.out.println("Please Enter Again.\n");
            }
        }
    }
}
