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
import objects.RoleStaff;
import objects.Staff;
import util.Exceptions;

public class StaffDao {

    private int lastGeneratedStaffID = 0;
    Exceptions validation = new Exceptions();
    List<Staff> staffs = new ArrayList<>();
    public String headerStaff = String.format("|%-10s|%-13s|%-19s|", " Staff Id", " Name", " Role");
    public void manageStaffAssignments(FlightManagement flightManagement) {
        int choice;
        do {
            printMenu();
            choice = validation.inputChoiceMain(7);

            switch (choice) {
                case 1:
                    addNewStaff();
                    break;
                case 2:
                    updateStaff();
                    break;
                case 3:
                    String idDelete = validation.getString("Enter staff id you want to delete: ", "Invalid id!");
                    deleteStaffById(idDelete);
                    break;
                case 4:
                    String staffId = validation.getString("Enter staff id you want to find: ", "Invalid id!");
                    Staff x = findStaffById(staffId);
                    printStaff(x);
                    break;
                case 5:
                    printAllStaffs();
                    break;
                case 6:
                    getStaffFromFile("src\\database\\loadstaffs.txt");
                    break;
                case 7:
                    System.out.println("Quitting...");
                    break;
            }
        } while (choice != 7);
    }

    public void printMenu() {
        System.out.println("+---------------------------------+");
        System.out.println("|Staff Management Menu:           |");
        System.out.println("|1. Add new Staff                 |");
        System.out.println("|2. Update Staff                  |");
        System.out.println("|3. Delete Staff                  |");
        System.out.println("|4. Find staff                    |");
        System.out.println("|5. Print all staffs              |");
        System.out.println("|6. Load on File                  |");
        System.out.println("|7. Return to Administrator Menu! |");
        System.out.println("+---------------------------------+");
    }

    public void addNewStaff() {
        RoleStaff role = new RoleStaff();
        String idStaff;
        while (true) {
            idStaff = generateStaffId();
            System.out.println("Generated crew id: " + idStaff);
            if (isStaffIdDuplicate(idStaff)) {
                System.out.println("Error: Staff id already exists!!!");
            } else {
                break;
            }
        }

        String staffName = validation.getString("Enter staff Name: ", "Invalid name!");

        System.out.println("Enter staff Role (e.g., Pilot, Flight Attendant, Ground Staff): ");
        String staffRole = role.chooseRole();

        Staff staff = new Staff(idStaff, staffRole, staffName);
        staffs.add(staff);
        System.out.println("Add staff successfully!");
    }

    public Staff findStaffById(String staffID) {
        if (staffs.isEmpty()) {
            System.out.println("There are no current staffs!!");
            return null;
        }
        for (Staff staff : staffs) {
            if (staff.getIdStaff().equalsIgnoreCase(staffID)) {
                return staff;
            }
        }
        return null;
    }

    public void printStaff(Staff staff) {
        if (staffs.isEmpty()) {
            System.out.println("There are no current staffs!!");
            return;
        }
        if (staff != null) {
            System.out.println("Staff information: ");
            System.out.println(headerStaff);
            System.out.printf("| %-9s| %-12s| %-18s|\n",
                    staff.getIdStaff(), staff.getName(), staff.getRole());
        } else {
            System.out.println("Staff not found!");
        }
    }

    public boolean isStaffIdDuplicate(String staffID) {
        for (Staff staff : staffs) {
            if (staff.getIdStaff().equalsIgnoreCase(staffID)) {
                return true;
            }
        }
        return false;
    }

    public String generateStaffId() {
        lastGeneratedStaffID++;
        String format = "S%03d";
        String importCode = String.format(format, lastGeneratedStaffID);
        return importCode;
    }

    public void printAllStaffs() {
        if (staffs.isEmpty()) {
            System.out.println("List is empty!!");
            return;
        }
        System.out.println("Here is staffs list: ");
        System.out.println(headerStaff);
        for (Staff staff : staffs) {
            System.out.printf("| %-9s| %-12s| %-18s|\n",
                    staff.getIdStaff(), staff.getName(), staff.getRole());

        }
    }

    public void getStaffFromFile(String path) {
        try {
            Scanner scanner = new Scanner(new FileReader(path));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] data = line.split(",");

                if (data.length > 1) {
                    String staffId = data[0];
                    if (isStaffIdDuplicate(staffId)) {
                        continue;
                    }
                    String name = data[1];
                    String role = data[2];

                    Staff staff = new Staff(staffId, role, name);
                    staffs.add(staff);
                }
            }
            System.out.println("Add successfully!");
            printAllStaffs();
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void deleteStaffById(String staffId) {
        if (staffs.isEmpty()) {
            System.out.println("List is empty!!");
            return;
        }
        for (Staff staff : staffs) {
            if (staff.getIdStaff().equalsIgnoreCase(staffId)) {
                staffs.remove(staff);
                System.out.println("Delete successfully!");
                return;
            }
        }
        System.out.println("Staff not found!!");
    }

    public void menuUpdateStaff(Staff staff) {

        int choice;
        do {
            System.out.println("+--------------------------+");
            System.out.println("|Update Staff :            |");
            System.out.println("|1. Update name            |");
            System.out.println("|2. Update role            |");
            System.out.println("|3. Return to Main Menu!   |");
            System.out.println("+--------------------------+");

            choice = validation.inputChoiceMain(3);

            switch (choice) {
                case 1:
                    String name = validation.getString("Enter the new name: ", "This field cannot be left blank!");
                    boolean ok = validation.confirm("Confirm you want to update?(No -> 0, Yes -> 1)");
                    if (ok) {
                        staff.setName(name);
                        System.out.println("Update successfully!");
                    } else {
                        System.out.println("Update failed!");
                    }
                    break;
                case 2:
                    RoleStaff role = new RoleStaff();
                    System.out.println("Choose staff Role");
                    String staffRole = role.chooseRole();
                    boolean ok1 = validation.confirm("Confirm you want to update?(No -> 0, Yes -> 1)");
                    if (ok1) {
                        staff.setRole(staffRole);
                        System.out.println("Update failed!");
                    } else {

                    }
                    break;
                case 3:
                    break;
            }
        } while (choice != 3);
    }

    public void updateStaff() {
        if(staffs.isEmpty()) {
            System.out.println("There are no current staffs!!");
            return;
        }
        String idUpdate = validation.getString("Enter staff id you want to update: ", "Invalid id!");
        Staff x = findStaffById(idUpdate);
        if (x == null) {
            System.out.println("Staff not found!");
            return;
        } else {
            menuUpdateStaff(x);
        }
    }

    public List<Staff> getStaffs() {
        return staffs;
    }

}
