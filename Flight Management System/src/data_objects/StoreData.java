/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data_objects;

import java.io.FileWriter;
import java.io.IOException;
import objects.Crew;
import objects.Flight;
import objects.Passenger;
import objects.Staff;

/**
 *
 * @author Admin
 */
public class StoreData {

    private static final String FLIGHT_FILE = "src\\database\\flights.txt";
    private static final String PASSENGER_FILE = "src\\database\\passengers.txt";
    private static final String CREW_FILE = "src\\database\\crews.txt";
    private static final String STAFF_FILE = "src\\database\\staffs.txt";

    public void storeFlightsToFile(FlightManagement flightManagement) {
        try {
            if (flightManagement.getFlights().isEmpty()) {
                System.out.println("There are no current flights!!");
                return;
            }
            FileWriter writer = new FileWriter(FLIGHT_FILE);
            for (Flight x : flightManagement.getFlights()) {
                writer.write(x.toString() + "\n");
            }
            writer.close();
            System.out.println("Write succesfully!!! " + FLIGHT_FILE);
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file: " + e.getMessage());
        }
    }

    public void storePassengersToFile(PassengersDao passengersDao) {
        if (passengersDao.getPassengers().isEmpty()) {
            System.out.println("There are no current passenger!!");
            return;
        }
        try {
            FileWriter writer = new FileWriter(PASSENGER_FILE);
            for (Passenger x : passengersDao.getPassengers()) {
                writer.write(x.toString() + "\n");
            }
            writer.close();
            System.out.println("Write succesfully!!! " + PASSENGER_FILE);
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file: " + e.getMessage());
        }
    }

    public void storeCrewsToFile(CrewManagement crewManagement) {
        if (crewManagement.getCrews().isEmpty()) {
            System.out.println("There are no current crew!!");
            return;
        }
        try {
            FileWriter writer = new FileWriter(CREW_FILE);
            for (Crew x : crewManagement.getCrews()) {
                writer.write(x.toString() + "\n");
            }
            writer.close();
            System.out.println("Write succesfully!!! " + CREW_FILE);
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file: " + e.getMessage());
        }
    }

    public void storeStaffsToFile(StaffDao staffDao) {
        if (staffDao.getStaffs().isEmpty()) {
            System.out.println("There are no current staff!!");
            return;
        }
        try {
            FileWriter writer = new FileWriter(STAFF_FILE);
            for (Staff x : staffDao.getStaffs()) {
                writer.write(x.toString() + "\n");
            }
            writer.close();
            System.out.println("Write succesfully!!! " + STAFF_FILE);
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file: " + e.getMessage());
        }
    }
}
