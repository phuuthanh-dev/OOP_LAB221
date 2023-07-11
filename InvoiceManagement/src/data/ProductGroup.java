/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.util.Scanner;
import util.Exceptions;

/**
 *
 * @author Admin
 */
public class ProductGroup {

    public static final String TRANSPORTATION = "Transportation";
    public static final String GARMENTS = "Garments";
    public static final String HOUSEHOLD_APPLIANCES = "Household Appliances";
    public static final String HIGH_TECH_EQUIPMENT = "High-Tech Equipment";
    public static final String FOOD = "Food";
    public Exceptions toy = new Exceptions();

    public ProductGroup() {
    }

    public static String getTRANSPORTATION() {
        return TRANSPORTATION;
    }

    public static String getGARMENTS() {
        return GARMENTS;
    }

    public static String getHOUSEHOLD_APPLIANCES() {
        return HOUSEHOLD_APPLIANCES;
    }

    public static String getHIGH_TECH_EQUIPMENT() {
        return HIGH_TECH_EQUIPMENT;
    }

    public static String getFOOD() {
        return FOOD;
    }

    public String chooseGroup() {
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            printMenu();
            choice = toy.getChoice("Input your choice (1...5): ", "Choose from 1 to 5!!", 1, 5);
            switch (choice) {
                case 1:
                    return TRANSPORTATION;
                case 2:
                    return GARMENTS;
                case 3:
                    return HOUSEHOLD_APPLIANCES;
                case 4:
                    return HIGH_TECH_EQUIPMENT;
                case 5:
                    return FOOD;
            }
        } while (choice != 5);
        return null;
    }

    public static void printMenu() {
        System.out.println("Input product group: ");
        System.out.println("+-------------------------+");
        System.out.println("|1. Transportation        |");
        System.out.println("|2. Garments              |");
        System.out.println("|3. Household Appliances  |");
        System.out.println("|4. High-Tech Equipment   |");
        System.out.println("|5. Food                  |");
        System.out.println("+-------------------------+");
    }
}
