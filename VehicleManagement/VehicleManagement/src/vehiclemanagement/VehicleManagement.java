/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vehiclemanagement;

import data_objects.StoreData;
import data_objects.VehicleDao;
import java.util.Scanner;
import util.Validation;

/**
 *
 * @author Admin
 */
public class VehicleManagement {

    public static Scanner sc = new Scanner(System.in);
    public static Validation validation = new Validation();

    public static void main(String[] args) {
        int choice;
        VehicleDao vehicleDao = new VehicleDao();
        StoreData storeData = new StoreData();
        do {
            printMenu();
            choice = validation.inputChoiceMain(10);
            switch (choice) {
                case 1:
                    vehicleDao.addNewVehicle();
                    break;
                case 2:
                    vehicleDao.checkExist("src\\database\\vehicle.dat");
                    break;
                case 3:
                    vehicleDao.updateVehicle();
                    break;
                case 4:
                    vehicleDao.deleteVehicle();
                    break;
                case 5:
                    searchVehicle(vehicleDao);
                    break;
                case 6:
                    displayVehicle(vehicleDao);
                    break;
                case 7:
                    storeData.storeVehiclesToFile(vehicleDao);
                    break;
                case 8:
                    printVehicle(vehicleDao);
                    break;
                case 9:
                    vehicleDao.getVehicleFromFile("src\\database\\vehicle.dat");
                    break;
                case 10:
                    System.out.println("Bye bye, see you next time");
                    break;
            }
        } while (choice != 10);
    }

    public static void printMenu() {
        System.out.println("+---------------------------------------------------------+");
        System.out.println("|                SHOWROOM MANAGEMENT SYSTEM               |");
        System.out.println("|- Choose the following functions to work:                |");
        System.out.println("|1. Add new vehicle .                                     |");
        System.out.println("|2. Check to exist Vehicle.                               |");
        System.out.println("|3. Update vehicle.                                       |");
        System.out.println("|4. Delete vehicle.                                       |");
        System.out.println("|5. Search vehicle.                                       |");
        System.out.println("|6. Display vehicle list.                                 |");
        System.out.println("|7. Save data to file.                                    |");
        System.out.println("|8. Print vehicle list.                                   |");
        System.out.println("|9. Load vehicle from file.                               |");
        System.out.println("|10.Quit.                                                 |");
        System.out.println("+---------------------------------------------------------+");
    }

    public static void searchVehicle(VehicleDao vehicleDao) {
        int choice;
        do {
            menuSearchVehicle();
            choice = validation.inputChoiceMain(3);
            switch (choice) {
                case 1:
                    vehicleDao.searchVehicleByName();
                    break;
                case 2:
                    vehicleDao.searchVehicleById();
                    break;
                case 3:
                    System.out.println("Quitting...");
                    break;
            }
        } while (choice != 3);
    }

    public static void menuSearchVehicle() {
        System.out.println("+-----------------------------------------+");
        System.out.println("|Choose the following functions to work:  |");
        System.out.println("|  5.1. Search by Name.                   |");
        System.out.println("|  5.2. Search by Id.                     |");
        System.out.println("|  5.3. Quitting...                       |");
        System.out.println("+-----------------------------------------+");
    }

    public static void displayVehicle(VehicleDao vehicleDao) {
        int choice;
        do {
            menuDisplayVehicle();
            choice = validation.inputChoiceMain(3);
            switch (choice) {
                case 1:
                    vehicleDao.printAllVehicles();
                    break;
                case 2:
                    vehicleDao.showByPriceVehicle();
                    break;
                case 3:
                    System.out.println("Quitting...");
                    break;
            }
        } while (choice != 3);
    }

    public static void menuDisplayVehicle() {
        System.out.println("+-----------------------------------------+");
        System.out.println("|Choose the following functions to work:  |");
        System.out.println("|  6.1. Show all.                         |");
        System.out.println("|  6.2. Show by price.                    |");
        System.out.println("|  6.3. Quitting...                       |");
        System.out.println("+-----------------------------------------+");
    }
    
    public static void printVehicle(VehicleDao vehicleDao) {
        int choice;
        do {
            menuPrintVehicle();
            choice = validation.inputChoiceMain(3);
            switch (choice) {
                case 1:
                    vehicleDao.printAllVehicles();
                    break;
                case 2:
                    vehicleDao.printByYearVehicle();
                    break;
                case 3:
                    System.out.println("Quitting...");
                    break;
            }
        } while (choice != 3);
    }

    public static void menuPrintVehicle() {
        System.out.println("+-----------------------------------------+");
        System.out.println("|Choose the following functions to work:  |");
        System.out.println("|  8.1. Show all.                         |");
        System.out.println("|  8.2. Show by price.                    |");
        System.out.println("|  8.3. Quitting...                       |");
        System.out.println("+-----------------------------------------+");
    }
}
