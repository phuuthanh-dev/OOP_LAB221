/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data_objects;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import object.Vehicle;
import util.Validation;

/**
 *
 * @author Admin
 */
public class VehicleDao {

    public String header = String.format("|%-7s|%-27s|%-15s|%-12s|%-15s|%-11s|%-6s|", " Id", " Vehicle Name", " Color", " Price", " Brand", " Type", " Year");
    Scanner sc = new Scanner(System.in);
    List<Vehicle> vehicles = new ArrayList<>();
    Validation validation = new Validation();
    private int lastGeneratedVehicleID = 0;

    public void addNewVehicle() {
        String idVehicle;
        String nameVehicle;
        String colorVehicle;
        long priceVehicle;
        String brandVehicle;
        String type;
        Year year;

        while (true) {

            while (true) {
                idVehicle = generateVehicleId();
                System.out.println("Generated vehicle id: " + idVehicle);
                if (isVehicleIdDuplicate(idVehicle)) {
                    System.out.println("Error: Vehicle id already exists!!!");
                } else {
                    break;
                }
            }

            while (true) {
                System.out.print("Input name vehicle: ");
                nameVehicle = sc.nextLine();
                if (nameVehicle.isEmpty()) {
                    System.out.println("This field cannot be left blank!!!");
                } else {
                    break;
                }
            }

            while (true) {
                System.out.print("Input color vehicle: ");
                colorVehicle = sc.nextLine();
                if (colorVehicle.isEmpty()) {
                    System.out.println("This field cannot be left blank!!!");
                } else {
                    break;
                }
            }

            priceVehicle = validation.getAnLong("Input price vehicle: ", "Price does not exist!");

            while (true) {
                System.out.print("Input brand vehicle: ");
                brandVehicle = sc.nextLine();
                if (brandVehicle.isEmpty()) {
                    System.out.println("This field cannot be left blank!!!");
                } else {
                    break;
                }
            }

            while (true) {
                System.out.print("Input type vehicle: ");
                type = sc.nextLine();
                if (type.isEmpty()) {
                    System.out.println("This field cannot be left blank!!!");
                } else {
                    break;
                }
            }

            year = validation.inputYear("Input year vehicle: ");

            Vehicle vehicle = new Vehicle(idVehicle, nameVehicle, colorVehicle, priceVehicle, brandVehicle, type, year);
            vehicles.add(vehicle);

            System.out.println("Create vehicle successfully!!!");

            boolean ok = validation.confirm("Do you want do create more Vehicle?(No -> 0, Yes -> 1)");

            if (!ok) {
                System.out.println("Quitting!");
                break;
            }
        }
    }

    public String generateVehicleId() {
        lastGeneratedVehicleID++;
        String format = "V%04d";
        String importCode = String.format(format, lastGeneratedVehicleID);
        return importCode;
    }

    public boolean isVehicleIdDuplicate(String vehicleId) {
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getIdVehicle().equalsIgnoreCase(vehicleId)) {
                return true;
            }
        }
        return false;
    }

    public void printAllVehicles() {
        if (vehicles.isEmpty()) {
            System.out.println("List vehicles is empty!!");
            return;
        }
        System.out.println("Here is vehicles list: ");
        System.out.println(header);
        for (Vehicle vehicle : vehicles) {
            System.out.printf("| %-6s| %-26s| %-14s| %-11d| %-14s| %-10s| %-5s|\n",
                    vehicle.getIdVehicle(), vehicle.getNameVehicle(), vehicle.getColorVehicle(),
                    vehicle.getPriceVehicle(), vehicle.getBrandVehicle(), vehicle.getType(), vehicle.getYear());

        }
    }

    public void checkExist(String path) {
        try {
            Scanner scanner = new Scanner(new FileReader(path));
            String idVehicle = validation.getString("Input vehicle id you want to check: ", "This field cannot be left blank!!!");
            List<String> listIdVehicle = new ArrayList<>();
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] data = line.split(",");

                if (data.length > 1) {
                    String idVehicleFile = data[0];
                    if (idVehicle.equalsIgnoreCase(idVehicleFile)) {
                        listIdVehicle.add(data[0]);
                    }
                }
            }
            if (listIdVehicle.isEmpty()) {
                System.out.println("No Vehicle Found!");
                return;
            }
            System.out.println("Existed Vehicle");
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Vehicle getVehicleById(String vehicleId) {
        if (vehicles.isEmpty()) {
            System.out.println("List vehicles is empty!!");
            return null;
        }
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getIdVehicle().equalsIgnoreCase(vehicleId)) {
                return vehicle;
            }
        }
        return null;
    }

    public void updateVehicle() {
        String idVehicle = validation.getString("Input vehicle id you want to update: ", "This field cannot be left blank!!!");
        Vehicle vehicleUpdate = getVehicleById(idVehicle);
        if (vehicleUpdate == null) {
            System.out.println("Vehicle does not exist");
            return;
        }

        System.out.println("Current vehicle information!");
        System.out.println(header);
        System.out.printf("| %-6s| %-26s| %-14s| %-11d| %-14s| %-10s| %-5s|\n",
                vehicleUpdate.getIdVehicle(), vehicleUpdate.getNameVehicle(), vehicleUpdate.getColorVehicle(),
                vehicleUpdate.getPriceVehicle(), vehicleUpdate.getBrandVehicle(), vehicleUpdate.getType(), vehicleUpdate.getYear());

        menuUpdateFlight(vehicleUpdate);

    }

    public void menuUpdateFlight(Vehicle vehicleUpdate) {
        int choice;
        boolean ok;
        do {
            System.out.println("+---------------------------+");
            System.out.println("|Update Vehicle:            |");
            System.out.println("|1. Update name vehicle.    |");
            System.out.println("|2. Update color vehicle.   |");
            System.out.println("|3. Update price vehicle.   |");
            System.out.println("|4. Update brand vehicle.   |");
            System.out.println("|5. Update type.            |");
            System.out.println("|6. Update year.            |");
            System.out.println("|7. Return to Main Menu!    |");
            System.out.println("+---------------------------+");

            choice = validation.inputChoiceMain(7);

            switch (choice) {
                case 1:
                    System.out.print("Enter the new name: ");
                    String nameVehicle = sc.nextLine();
                    if (nameVehicle.isEmpty()) {
                        System.out.println("Information does not change!!");
                        break;
                    }
                    ok = validation.confirm("Confirm you want to update?(No -> 0, Yes -> 1)");
                    if (ok) {
                        vehicleUpdate.setNameVehicle(nameVehicle);
                        System.out.println("Update successfully!");
                    }
                    break;
                case 2:
                    System.out.print("Enter the new color: ");
                    String colorVehicle = sc.nextLine();
                    if (colorVehicle.isEmpty()) {
                        System.out.println("Information does not change!!");
                        break;
                    }
                    ok = validation.confirm("Confirm you want to update?(No -> 0, Yes -> 1)");
                    if (ok) {
                        vehicleUpdate.setColorVehicle(colorVehicle);
                        System.out.println("Update successfully!");
                    }
                    break;
                case 3:
                    long price = validation.getAnLongCanEmpty("Enter the new price: ", "Invalid input. Please enter a valid number.");
                    if (price == 0) {
                        System.out.println("Information does not change!!");
                        break;
                    }
                    ok = validation.confirm("Confirm you want to update?(No -> 0, Yes -> 1)");
                    if (ok) {
                        vehicleUpdate.setPriceVehicle(price);
                        System.out.println("Update successfully!");
                    }
                    break;
                case 4:
                    System.out.print("Enter the new brand: ");
                    String brand = sc.nextLine();
                    if (brand.isEmpty()) {
                        System.out.println("Information does not change!!");
                        break;
                    }
                    ok = validation.confirm("Confirm you want to update?(No -> 0, Yes -> 1)");
                    if (ok) {
                        vehicleUpdate.setBrandVehicle(brand);
                        System.out.println("Update successfully!");
                    }
                    break;
                case 5:
                    System.out.print("Enter the new type: ");
                    String type = sc.nextLine();
                    if (type.isEmpty()) {
                        System.out.println("Information does not change!!");
                        break;
                    }
                    ok = validation.confirm("Confirm you want to update?(No -> 0, Yes -> 1)");
                    if (ok) {
                        vehicleUpdate.setType(type);
                        System.out.println("Update successfully!");
                    }
                    break;
                case 6:
                    Year year = validation.inputYearCanEmpty("Enter the new vehicle year: ");
                    if (year == null) {
                        System.out.println("Information does not change!!");
                        break;
                    }
                    ok = validation.confirm("Confirm you want to update?(No -> 0, Yes -> 1)");
                    if (ok) {
                        vehicleUpdate.setYear(year);
                        System.out.println("Update successfully!");
                    }
                    break;
                case 7:
                    System.out.println("Quitting..");
                    break;
            }
        } while (choice != 7);
    }

    public void deleteVehicle() {
        if (vehicles.isEmpty()) {
            System.out.println("List vehicles is empty!!");
            return;
        }
        printAllVehicles();
        String vehicleId = validation.getString("Enter the vehicle id you want to delete: ", "This field cannot be left blank!");
        Vehicle vehicleDelete = getVehicleById(vehicleId);

        boolean ok = validation.confirm("Confirm you want to delete?(No -> 0, Yes -> 1)");
        if (ok) {
            vehicles.remove(vehicleDelete);
            System.out.println("Delete successfully!");
        } else {
            System.out.println("Delete fail!!");
        }
    }

    public void printListVehicles(List<Vehicle> vehiclesSearch) {
        if (vehicles == null) {
            return;
        }
        System.out.println(header);
        for (Vehicle vehicle : vehiclesSearch) {
            System.out.printf("| %-6s| %-26s| %-14s| %-11d| %-14s| %-10s| %-5s|\n",
                    vehicle.getIdVehicle(), vehicle.getNameVehicle(), vehicle.getColorVehicle(),
                    vehicle.getPriceVehicle(), vehicle.getBrandVehicle(), vehicle.getType(), vehicle.getYear());
        }
    }

    public void searchVehicleByName() {
        if (vehicles.isEmpty()) {
            System.out.println("List vehicles is empty!!");
            return;
        }
        String vehicleName = validation.getString("Enter the vehicle name you want to search: ", "This field cannot be left blank!");
        List<Vehicle> listVehicleSearch = new ArrayList<>();
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getNameVehicle().toLowerCase().contains(vehicleName.toLowerCase())) {
                listVehicleSearch.add(vehicle);
            }
        }
        if (listVehicleSearch.isEmpty()) {
            System.out.println("No vehicle found with the given name " + vehicleName);
            return;
        }

        for (int i = 0; i < listVehicleSearch.size() - 1; i++) {
            for (int j = i + 1; j < listVehicleSearch.size(); j++) {
                if (listVehicleSearch.get(i).getNameVehicle().compareToIgnoreCase(listVehicleSearch.get(j).getNameVehicle()) < 0) {
                    Vehicle tmp = listVehicleSearch.get(i);
                    listVehicleSearch.set(i, listVehicleSearch.get(j));
                    listVehicleSearch.set(j, tmp);
                }
            }
        }
        System.out.println("Vehicle list sorted by name descending: ");
        printListVehicles(listVehicleSearch);
    }

    public void searchVehicleById() {
        if (vehicles.isEmpty()) {
            System.out.println("List vehicles is empty!!");
            return;
        }
        String vehicleId = validation.getString("Enter the vehicle id you want to search: ", "This field cannot be left blank!");
        Vehicle vehicle = getVehicleById(vehicleId);

        if (vehicle == null) {
            System.out.println("Vehicle not found!!");
            return;
        } else {
            System.out.println("Vehicle with id " + vehicleId + " found!");
            System.out.printf("| %-6s| %-26s| %-14s| %-11d| %-14s| %-9s| %-5s|\n",
                    vehicle.getIdVehicle(), vehicle.getNameVehicle(), vehicle.getColorVehicle(),
                    vehicle.getPriceVehicle(), vehicle.getBrandVehicle(), vehicle.getType(), vehicle.getYear());
        }
    }

    public void showByPriceVehicle() {
        if (vehicles.isEmpty()) {
            System.out.println("List vehicles is empty!!");
            return;
        }

        int price = validation.getAnInteger("Enter the price: ", "Price does not exist!");
        List<Vehicle> listVehicleResult = new ArrayList<>();
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getPriceVehicle() < price) {
                listVehicleResult.add(vehicle);
            }
        }
        if (listVehicleResult.isEmpty()) {
            System.out.println("Currently there aren't vehicle priced less than " + price);
            return;
        }

        for (int i = 0; i < listVehicleResult.size() - 1; i++) {
            for (int j = i + 1; j < listVehicleResult.size(); j++) {
                if (listVehicleResult.get(i).getPriceVehicle() < (listVehicleResult.get(j).getPriceVehicle())) {
                    Vehicle tmp = listVehicleResult.get(i);
                    listVehicleResult.set(i, listVehicleResult.get(j));
                    listVehicleResult.set(j, tmp);
                }
            }
        }
        System.out.println("Vehicle list sorted by price descending: ");
        printListVehicles(listVehicleResult);
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void printByYearVehicle() {
        if (vehicles.isEmpty()) {
            System.out.println("List vehicles is empty!!");
            return;
        }

        Year year = validation.inputYear("Enter the year: ");
        List<Vehicle> listVehicleResult = new ArrayList<>();
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getYear().isAfter(year) || vehicle.getYear().equals(year)) {
                listVehicleResult.add(vehicle);
            }
        }
        if (listVehicleResult.isEmpty()) {
            System.out.println("Currently there aren't vehicle year higher than " + year);
            return;
        }

        for (int i = 0; i < listVehicleResult.size() - 1; i++) {
            for (int j = i + 1; j < listVehicleResult.size(); j++) {
                if (listVehicleResult.get(i).getYear().isBefore(listVehicleResult.get(j).getYear())) {
                    Vehicle tmp = listVehicleResult.get(i);
                    listVehicleResult.set(i, listVehicleResult.get(j));
                    listVehicleResult.set(j, tmp);
                }
            }
        }
        System.out.println("Vehicle list sorted by year descending: ");
        printListVehicles(listVehicleResult);
    }

    public void getVehicleFromFile(String path) {
        try {
            DateTimeFormatter yearFormatter = DateTimeFormatter.ofPattern("yyyy");
            Scanner scanner = new Scanner(new FileReader(path));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] data = line.split(",");

                if (data.length > 1) {
                    String idVehicle = data[0];
                    if (isVehicleIdDuplicate(idVehicle)) {
                        continue;
                    }
                    String nameVehicle = data[1];
                    String colorVehicle = data[2];
                    long priceVehicle = Long.parseLong(data[3]);
                    String brandVehicle = data[4];
                    String type = data[5];
                    Year year = Year.parse(data[6].trim(), yearFormatter);

                    Vehicle vehicle = new Vehicle(idVehicle, nameVehicle, colorVehicle, priceVehicle, brandVehicle, type, year);
                    vehicles.add(vehicle);
                }
            }
            System.out.println("Add successfully!");
            printAllVehicles();
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
