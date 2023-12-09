
package application;

import data_objects.Administrators;
import data_objects.CrewManagement;
import data_objects.FlightManagement;
import data_objects.PassengersDao;
import data_objects.Service;
import data_objects.StoreData;
import util.Exceptions;
import java.util.Scanner;

public class FlightManagementSystem {

    public static Scanner sc = new Scanner(System.in);
    public static Exceptions validation = new Exceptions();

    public static void main(String[] args) {
        int choice;
        FlightManagement flightManagement = new FlightManagement();
        PassengersDao passengersDao = new PassengersDao();
        Service service = new Service(passengersDao.getPassengers(), flightManagement.getFlights());
        CrewManagement crewManagement = new CrewManagement();
        Administrators admin = new Administrators();
        do {
            printMenu();
            choice = validation.inputChoiceMain(7);
            switch (choice) {
                case 1:
                    flightManage(flightManagement);
                    break;
                case 2:
                    passenger(flightManagement, passengersDao);
                    break;
                case 3:
                    service(passengersDao, service);
                    break;
                case 4:
                    admin.loginAdmin(passengersDao, flightManagement, crewManagement);
                    break;
                case 5:
                    storeFile(admin, flightManagement, passengersDao, crewManagement);
                    break;
                case 6:
                    flightManagement.getFlightFromFile("src\\database\\loadflights.txt");
                    break;
                case 7:
                    System.out.println("Bye bye, see you next time");
                    break;
            }
        } while (choice != 7);
    }

    public static void printMenu() {
        System.out.println("+------------------------------------------------------------------+");
        System.out.println("|                  FLIGHT MANAGEMENT SYSTEM                        |");
        System.out.println("|- Choose the following functions to work:                         |");
        System.out.println("|1. Flight schedule management.                                    |");
        System.out.println("|2. Passenger Reservation and Booking.                             |");
        System.out.println("|3. Passenger Check-In and Seat Allocation and Availability.       |");
        System.out.println("|4. Crew Management and Administrator Access.                      |");
        System.out.println("|5. Save to file.                                                  |");
        System.out.println("|6. Print all lists from file.                                     |");
        System.out.println("|7. Quit.                                                          |");
        System.out.println("+------------------------------------------------------------------+");
    }

    public static void service(PassengersDao passengersDao, Service service) {
        int choice;
        do {
            printMenuService();
            choice = validation.inputChoiceMain(4);
            switch (choice) {
                case 1:
                    service.displayAvailableSeats();
                    break;
                case 2:
                    service.selectSeat();
                    break;
                case 3:
                    service.generateBoardingPasses();
                    break;
                case 4:
                    System.out.println("Quitting...");
                    break;
            }
        } while (choice != 4);
    }

    public static void printMenuService() {
        System.out.println("+--------------------------------------------------+");
        System.out.println("|Choose the following functions to work:           |");
        System.out.println("|  3.1. Display the availability of seats.         |");
        System.out.println("|  3.2. Choose available seats.                    |");
        System.out.println("|  3.3. Print ticket.                              |");
        System.out.println("|  3.4. Quitting...                                |");
        System.out.println("+--------------------------------------------------+");
    }

    public static void storeFile(Administrators admin, FlightManagement flightManagement, PassengersDao passengersDao, CrewManagement crewManagement) {
        int choice;
        do {
            printMenuSaveFile();
            choice = validation.inputChoiceMain(5);
            StoreData storeData = new StoreData();
            switch (choice) {
                case 1:
                    storeData.storeFlightsToFile(flightManagement);
                    break;
                case 2:
                    storeData.storePassengersToFile(passengersDao);
                    break;
                case 3:
                    storeData.storeCrewsToFile(crewManagement);
                    break;
                case 4:
                    storeData.storeStaffsToFile(admin.getStaffDao());
                    break;
                case 5:
                    System.out.println("Quitting...");
                    break;
            }
        } while (choice != 5);
    }

    public static void printMenuSaveFile() {
        System.out.println("+-------------------------------------------------+");
        System.out.println("|Choose the following functions to work:          |");
        System.out.println("|  5.1. Save flight information                   |");
        System.out.println("|  5.2. Save passenger reservations               |");
        System.out.println("|  5.3. Save crew assignments                     |");
        System.out.println("|  5.4. Save staff infomation                     |");
        System.out.println("|  5.5. Quitting...                               |");
        System.out.println("+-------------------------------------------------+");
    }

    public static void passenger(FlightManagement flightManagement, PassengersDao passengersDao) {
        int choice;
        do {
            printMenuPassenger();
            choice = validation.inputChoiceMain(5);
            switch (choice) {
                case 1:
                    passengersDao.flightSearch(flightManagement.getFlights());
                    break;
                case 2:
                    passengersDao.booking(flightManagement.getFlights());
                    break;
                case 3:
                    passengersDao.generateTicket();
                    break;
                case 4:
                    passengersDao.printAllPassengers();
                    break;
                case 5:
                    System.out.println("Quitting...");
                    break;
            }
        } while (choice != 5);
    }

    public static void printMenuPassenger() {
        System.out.println("+-------------------------------------------+");
        System.out.println("|Choose the following functions to work:    |");
        System.out.println("|  2.1. Search flight                       |");
        System.out.println("|  2.2. Booking                             |");
        System.out.println("|  2.3. Print ticket                        |");
        System.out.println("|  2.4. Print all passengers                |");
        System.out.println("|  2.5. Quitting...                         |");
        System.out.println("+-------------------------------------------+");
    }

    public static void flightManage(FlightManagement flightManagement) {
        int choice;
        do {
            printMenuFlight();
            choice = validation.inputChoiceMain(3);
            switch (choice) {
                case 1:
                    flightManagement.createNewFlight();
                    break;
                case 2:
                    flightManagement.printListFlight();
                    break;
                case 3:
                    System.out.println("Quitting...");
                    break;
            }
        } while (choice != 3);
    }

    public static void printMenuFlight() {
        System.out.println("+-------------------------------------------+");
        System.out.println("|Choose the following functions to work:    |");
        System.out.println("|  1.1. Create flight                       |");
        System.out.println("|  1.2. Print all flight                    |");
        System.out.println("|  1.3. Quitting...                         |");
        System.out.println("+-------------------------------------------+");
    }

}
