/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se160076.vaccinemanagement;
import java.util.ArrayList;

/**
 *
 * @author Tony
 */
public class Main {

    public static void main(String args[]) {
        ArrayList<Injection> il = new ArrayList();
        ArrayList<Student> sl = new ArrayList();
        ArrayList<Vaccine> vl = new ArrayList();

        while (true) {
            Manager.loadInjectionFile(il);
            Manager.loadStudentFile(sl);
            Manager.loadVaccineFile(vl);
            Manager.printMenu();

            int choice = Validator.validateMenuChoice(1, 6);

            switch (choice) {
                case 1:
                    Manager.printInjections(il, sl, vl);
                    break;

                case 2:
                    Manager.addInjections(il, sl, vl);
                    break;

                case 3:
                    Manager.updateInjections(il);
                    break;

                case 4:
                    Manager.deleteInjections(il);
                    break;

                case 5:
                    Manager.searchInjections(il, sl, vl);
                    break;

                case 6:
                    System.exit(0);
                    break;

                default:
                    System.exit(0);
                    break;
            }
        }
    }
}
