/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data_objects;

import java.util.List;
import java.util.Scanner;
import objects.Crew;
import util.Exceptions;

/**
 *
 * @author Admin
 */
public class Administrators {

    private StaffDao staffDao = new StaffDao();
    Scanner sc = new Scanner(System.in);
    Exceptions validation = new Exceptions();
    private static final String USERNAME = "Admin";
    private static final String PASSWORD = "1";

    public void loginAdmin(PassengersDao passengersDao, FlightManagement flightManagement, CrewManagement crewManagement) {
        String userId = validation.getString("Input username: ", "Do not leave this field blank!!!");
        String password = validation.getString("Input password: ", "Do not leave this field blank!!!");
        if (userId.equalsIgnoreCase(USERNAME) && password.equalsIgnoreCase("1")) {
            System.out.println("Logged in successfully!");
            adminMenu(passengersDao, flightManagement, crewManagement);
        } else if (!userId.equalsIgnoreCase(USERNAME) && password.equalsIgnoreCase("1")) {
            System.out.println("Wrong username!");
        } else if (userId.equalsIgnoreCase(USERNAME) && !password.equalsIgnoreCase("1")) {
            System.out.println("Wrong password!");
        }
    }

    public void adminMenu(PassengersDao passengersDao, FlightManagement flightManagement, CrewManagement crewManagement) {
        while (true) {
            System.out.println("+------------------------------------+");
            System.out.println("|Administrator Menu:                 |");
            System.out.println("|  1. Manage Flight Schedule         |");
            System.out.println("|  2. Manage Crew Assignments        |");
            System.out.println("|  3. Manage Staff Schedule          |");
            System.out.println("|  4. Return to Main Menu            |");
            System.out.println("+------------------------------------+");

            int choice = validation.inputChoiceMain(4);

            switch (choice) {
                case 1:
                    manageFlightSchedule(passengersDao, flightManagement, crewManagement.getCrews());
                    break;
                case 2:
                    crewManagement.manageCrewAssignments(flightManagement, staffDao);
                    break;
                case 3:
                    staffDao.manageStaffAssignments(flightManagement);
                    break;
                case 4:
                    System.out.println("Quitting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    public void manageFlightSchedule(PassengersDao passengersDao, FlightManagement flightManagement, List<Crew> crews) {
        int choice;
        do {
            System.out.println("+------------------------------------+");
            System.out.println("|Flight Schedule Management:         |");
            System.out.println("|  1. Add New Flight                 |");
            System.out.println("|  2. Update Flight Details          |");
            System.out.println("|  3. Delete Flight                  |");
            System.out.println("|  4. View Flight                    |");
            System.out.println("|  5. Return to Administrator Menu!  |");
            System.out.println("+------------------------------------+");

            choice = validation.inputChoiceMain(5);

            switch (choice) {
                case 1:
                    flightManagement.createNewFlight();
                    break;
                case 2:
                    flightManagement.updateFlight(crews);
                    break;
                case 3:
                    flightManagement.deleteFlight(passengersDao);
                    break;
                case 4:
                    flightManagement.printListFlight();
                    break;
                case 5:
                    break;
            }
        } while (choice != 5);
    }

    public StaffDao getStaffDao() {
        return staffDao;
    }

}
