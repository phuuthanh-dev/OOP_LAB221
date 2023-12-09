/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data_objects;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import objects.Crew;
import objects.Flight;
import objects.Staff;
import util.Exceptions;

/**
 *
 * @author Admin
 */
public class CrewManagement {

    Scanner sc = new Scanner(System.in);
    Exceptions validation = new Exceptions();
    List<Crew> crews = new ArrayList<>();
    private int lastGeneratedCrewID = 0;
    public String headerCrew = String.format("|%-9s|%-32s|", " Crew Id", " List Id Staff");
    public String headerStaff = String.format("|%-10s|%-13s|%-19s|", " Staff Id", " Name", " Role");

    public void manageCrewAssignments(FlightManagement flightManagement, StaffDao staffDao) {
        int choice;
        do {
            printMenu();
            choice = validation.inputChoiceMain(6);

            switch (choice) {
                case 1:
                    createNewCrew(staffDao);
                    break;
                case 2:
                    assignCrewToFlight(flightManagement);
                    break;
                case 3:
                    viewAssignCrewForFlight(flightManagement);
                    break;
                case 4:
                    printAllCrews();
                    break;
                case 5:
                    getCrewFromFile("src\\database\\loadcrews.txt", staffDao);
                    break;
                case 6:
                    break;
            }
        } while (choice != 6);
    }

    public void printMenu() {
        System.out.println("+--------------------------------------------+");
        System.out.println("|Crew Management Menu:                       |");
        System.out.println("|1. Add new Crew                             |");
        System.out.println("|2. Assign Crew to Flight                    |");
        System.out.println("|3. View Crew Assignments for a Flight       |");
        System.out.println("|4. Print All Crews                          |");
        System.out.println("|5. Load Crew From File                      |");
        System.out.println("|6. Return to Administrator Menu!            |");
        System.out.println("+--------------------------------------------+");
    }

    public void assignCrewToFlight(FlightManagement flightManagement) {
        if (crews.isEmpty()) {
            System.out.println("Crew list is empty!");
            return;
        }
        if (flightManagement.getFlights().isEmpty()) {
            System.out.println("There are currently no flights!!!");
            return;
        }
        flightManagement.printListFlight();
        String flightNumber = validation.getString("Enter Flight Number: ", "This field cannot be left blank!");

        Flight flight = flightManagement.findFlightByNumber(flightNumber);

        if (flight != null) {
            printAllCrews();
            String crewId = validation.getString("Enter the id of the crew member you want to assign: ", "This field cannot be left blank!");

            Crew crew = findCrewById(crewId);
            if (crew != null) {
                flight.setCrew(crew);
                System.out.println("Crew assigned successfully!");
            } else {
                System.out.println("Crew doesn't exist!!!");
            }
        } else {
            System.out.println("Flight not found!");
        }
    }

    public String generateCrewId() {
        lastGeneratedCrewID++;
        String format = "C%02d";
        String importCode = String.format(format, lastGeneratedCrewID);
        return importCode;
    }

    public void printAllCrews() {
        if (crews.isEmpty()) {
            System.out.println("Crew list is empty!");
            return;
        }
        System.out.println("Here is crews list: ");
        System.out.println(headerCrew);
        for (Crew crew : crews) {
            System.out.printf("| %-8s| %-31s|\n",
                        crew.getCrewId(), crew.toStringIdStaff());

        }
    }

    public Crew findCrewById(String crewID) {
        if (crews.isEmpty()) {
            System.out.println("Crew list is empty!");
            return null;
        }
        for (Crew crew : crews) {
            if (crew.getCrewId().equalsIgnoreCase(crewID)) {
                return crew;
            }
        }
        return null;
    }

    public boolean isCrewIdDuplicate(String crewID) {
        for (Crew crew : crews) {
            if (crew.getCrewId().equalsIgnoreCase(crewID)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkStaffIdDuplicateInList(List<Staff> staffs, String staffId) {
        for (Staff staff : staffs) {
            if (staff.getIdStaff().equalsIgnoreCase(staffId)) {
                return true;
            }
        }
        return false;
    }

    public void viewAssignCrewForFlight(FlightManagement flightManagement) {
        if (flightManagement.getFlights().isEmpty()) {
            System.out.println("There are currently no flights!!!");
            return;
        }
        flightManagement.printListFlight();
        String flightNumberToView = validation.getString("Enter Flight Number: ", "This field cannot be left blank!");
        Flight flightToView = flightManagement.findFlightByNumber(flightNumberToView);

        if (flightToView != null && flightToView.getCrew() != null) {
            Crew crew = flightToView.getCrew();
            System.out.println("Crew Assignments for Flight " + flightNumberToView + ":");
            System.out.println(headerStaff);
            for (Staff staff : crew.getListStaff()) {
                System.out.printf("| %-9s| %-12s| %-18s|\n",
                    staff.getIdStaff(), staff.getName(), staff.getRole());
            }
        } else if (flightToView != null && flightToView.getCrew() == null) {
            System.out.println("The flight does not have a crew!");
        } else {
            System.out.println("Flight not found!");
        }
    }

    public List<Crew> getCrews() {
        return crews;
    }

    public void createNewCrew(StaffDao staffDao) {
        String crewId;
        List<Staff> staffs = new ArrayList<>();
        while (true) {

            while (true) {
                crewId = generateCrewId();
                System.out.println("Generated crew id: " + crewId);
                if (isCrewIdDuplicate(crewId)) {
                    System.out.println("Crew id " + crewId + " already exists!");
                } else {
                    break;
                }
            }

            while (true) {
                String staffId = validation.getString("Enter the staff id you want to add to the crew: (0 to finish!)", " Invalid id!!");
                if (staffId.equalsIgnoreCase("0")) {
                    break;
                }
                Staff staff = staffDao.findStaffById(staffId);
                if (staff == null) {
                    System.out.println("Staff not found!!");
                } else if (checkStaffIdDuplicateInList(staffs, staffId)) {
                    System.out.println("This employee was in the crew!!!");
                } else {
                    staffs.add(staff);
                    System.out.println("Add successfully!!");
                }
            }

            Crew crew = new Crew(crewId, staffs);
            crews.add(crew);

            System.out.println("Create crew successfully!");

            boolean ok = validation.confirm("Do you want do create more Crew??(No -> 0, Yes -> 1)");

            if (!ok) {
                System.out.println("Quitting!");
                break;
            }
        }
    }

    public void getCrewFromFile(String path, StaffDao staffDao) {
        try {

            Scanner scanner = new Scanner(new FileReader(path));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] data = line.split(",");

                if (data.length > 1) {
                    String crewId = data[0];
                    if (isCrewIdDuplicate(crewId)) {
                        continue;
                    }
                    List<Staff> listStaff = new ArrayList<>();

                    for (int i = 1; i < data.length; i++) {
                        Staff staff = staffDao.findStaffById(data[i]);
                        if (staff != null) {
                            listStaff.add(staff);
                        }
                    }

                    if (listStaff.isEmpty()) {
                        System.out.println("Staff in File is empty for crew: " + crewId);
                        continue;
                    }

                    Crew crew = new Crew(crewId, listStaff);
                    crews.add(crew);
                    System.out.println("Add successfully!");
                }
            }
            printAllCrews();
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
