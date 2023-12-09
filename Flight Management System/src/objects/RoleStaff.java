/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

import java.util.Scanner;
import util.Exceptions;

/**
 *
 * @author Admin
 */
public class RoleStaff {
    public static final String PILOTS = "Pilots";
    public static final String STEWARDESS = "Flight attendants";
    public static final String GROUNDSTAFF = "Ground staff";
    public static final String DOCTOR = "Doctor";
    public static final String ENGINEER = "Engineer";
    Exceptions validation = new Exceptions();

    public RoleStaff() {
    }

    public static String getPILOTS() {
        return PILOTS;
    }

    public static String getSTEWARDESS() {
        return STEWARDESS;
    }

    public static String getGROUNDSTAFF() {
        return GROUNDSTAFF;
    }

    public static String getENGINEER() {
        return ENGINEER;
    }

    public static String getDOCTOR() {
        return DOCTOR;
    }

    public String chooseRole() {
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            printMenu();
            choice = validation.inputChoiceMain(5);
            switch (choice) {
                case 1:
                    return PILOTS;
                case 2:
                    return STEWARDESS;
                case 3:
                    return GROUNDSTAFF;
                case 4:
                    return DOCTOR;
                case 5:
                    return ENGINEER;
            }
        } while (choice != 5);
        return null;
    }

    public static void printMenu() {
        System.out.println("Choose crew role: ");
        System.out.println("+----------------------+");
        System.out.println("|       Role           |");
        System.out.println("|1.Pilots.             |");
        System.out.println("|2.Flight attendants.  |");
        System.out.println("|3.Ground staff.       |");
        System.out.println("|4.Doctor              |");
        System.out.println("|5.Engineer.           |");
        System.out.println("+----------------------+");
    }
}
