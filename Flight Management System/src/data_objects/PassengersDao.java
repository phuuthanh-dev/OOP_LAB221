/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data_objects;

import static data_objects.FlightManagement.NORMAL;
import static data_objects.FlightManagement.VIP;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import objects.Flight;
import objects.Passenger;
import util.Exceptions;

/**
 *
 * @author Admin
 */
public class PassengersDao {

    Scanner sc = new Scanner(System.in);
    Exceptions validation = new Exceptions();
    private List<Passenger> passengers = new ArrayList<>();
    private int lastGeneratedReservationVipID = 0;
    private int lastGeneratedReservationNormalID = 0;
    public String headerFlight = String.format("|%-15s|%-13s|%-12s|%-18s|%-18s|%-11s|%-14s|", " Flight Number", " From", " To", " Departure Time", " Arrival Time", " Seats Vip", " Seats Normal");
    public String headerPassenger = String.format("|%-15s|%-12s|%-8s|%-16s|%-12s|", " Name", " Phone", " Flight", " Reservation Id", " Local seat");

    public List<Flight> flightSearch(List<Flight> flights) {
        String departureCity;
        String destinationCity;
        LocalDateTime departureTime;
        List<Flight> listFlight = new ArrayList<>();

        if (flights.isEmpty()) {
            System.out.println("List flight is empty!!");
            return null;
        }

        while (true) {
            System.out.print("Input departure city: ");
            departureCity = sc.nextLine();
            if (departureCity.isEmpty()) {
                System.out.println("This field cannot be left blank!!!");
            } else {
                break;
            }
        }

        while (true) {
            System.out.print("Input destination city: ");
            destinationCity = sc.nextLine();
            if (destinationCity.isEmpty()) {
                System.out.println("This field cannot be left blank!!!");
            } else {
                break;
            }
        }

        departureTime = inputDate("Departure time (dd/MM/yyyy): ");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        for (Flight flight : flights) {
            if (flight.getDepartureCity().equalsIgnoreCase(departureCity)
                    && flight.getDestinationCity().equalsIgnoreCase(destinationCity)
                    && flight.getDepartureTime().toLocalDate().isEqual(departureTime.toLocalDate())) {
                listFlight.add(flight);
            }
        }
        if (listFlight.isEmpty()) {
            System.out.println("No flight found!!!");
            return null;
        } else {
            printListFlight(listFlight, departureCity, destinationCity);
        }
        return listFlight;
    }

    public LocalDateTime inputDate(String msg) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDateTime dateTime = null;
        String dateString;
        while (dateTime == null) {
            try {
                System.out.print(msg);
                dateString = sc.nextLine().trim();
                dateTime = LocalDate.parse(dateString, formatter).atTime(LocalTime.MIDNIGHT);
            } catch (Exception e) {
                System.out.println("Invalid date format. Please use dd/MM/yyyy.");
            }
        }
        return dateTime;
    }

    public void printListFlight(List<Flight> flights, String departureCity, String destinationCity) {
        System.out.println("Flight from " + departureCity + " to " + destinationCity);
        System.out.println(headerFlight);
        for (Flight flight : flights) {
            System.out.printf("| %-14s| %-12s| %-11s| %-17s| %-17s| %-10s| %-13s|\n",
                    flight.getFlightNumber(), flight.getDepartureCity(), flight.getDestinationCity(), flight.getDepartureTime(), flight.getArrivalTime(), flight.getAvailableSeatsVip().size(), flight.getAvailableSeatsNormal().size());

        }
    }

    public void printAllPassengers() {
        if (passengers.isEmpty()) {
            System.out.println("Passenger list is empty!");
            return;

        } else {
            System.out.println("Here is passengers list: ");
            System.out.println(headerPassenger);
            for (Passenger passenger : passengers) {
                if (passenger.getLocalSeat() == -1) {
                    System.out.printf("| %-14s| %-11s| %-7s| %-15s| %-11s|\n",
                            passenger.getName(), passenger.getPhone(), passenger.getFlight().getFlightNumber(), passenger.getReservationId(), "NONE");
                    continue;
                }
                System.out.printf("| %-14s| %-11s| %-7s| %-15s| %-11s|\n",
                        passenger.getName(), passenger.getPhone(), passenger.getFlight().getFlightNumber(), passenger.getReservationId(), passenger.getLocalSeat());

            }
        }
    }

    public void booking(List<Flight> flights) {
        String name, phone, national;
        String flightNumber;
        String reservationId = null;

        if (flights.isEmpty()) {
            System.out.println("List flight is empty!!");
            return;
        }
        name = validation.getString("Input name: ", "Name is not valid!");
        phone = validation.getString("Input phone: ", "Phone is not valid!");
        List<Flight> flightSearch = flightSearch(flights);

        if (flightSearch == null) {
            System.out.println("No suitable flight found!!");
            return;
        }

        flightNumber = validation.getString("Input flight number: ", "This field cannot be left blank!");
        Flight flight = getFlight(flightSearch, flightNumber);

        if (flight != null) {
            String reservationCode = chooseTypeOfSeat();
            System.out.println("Generated Reservation Code: " + reservationCode);

            Passenger passenger = new Passenger(name, phone, flight, reservationCode);
            passengers.add(passenger);
        } else {
            System.out.println("Flight not found!");
            return;
        }
    }

    public Flight getFlight(List<Flight> flightSearch, String flightNumber) {
        for (int i = 0; i < flightSearch.size(); i++) {
            Flight flight = flightSearch.get(i);
            if (flight.getFlightNumber().equalsIgnoreCase(flightNumber)) {
                return flight;
            }
        }
        return null;
    }

    public boolean checkDuplicateReservationId(String reservationId) {
        for (int i = 0; i < passengers.size(); i++) {
            if (passengers.get(i).getReservationId().equalsIgnoreCase(reservationId)) {
                return true;
            }
        }
        return false;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public void generateTicket() {
        String reservationID = validation.getString("Input reservation id you want to generate ticket:", "This field cannot be left blank!!!");
        Passenger passenger = getPassengerByReservationId(reservationID);
        if (passenger == null) {
            System.out.println("No passengers found!");
            return;
        }
        Flight flight = passenger.getFlight();
        System.out.println("+---------------------------------------------------------------------------------+");
        System.out.println("|                                FLIGHT TICKET                                    |");
        System.out.printf("|Passenger Name: %65s|\n", passenger.getName());
        System.out.printf("|Passenger Phone: %64s|\n", passenger.getPhone());
        System.out.printf("|Passenger Reservation Id: %55s|\n", passenger.getReservationId());
        System.out.println("+---------------------------------------------------------------------------------+");

        System.out.println("| Flight Number | From         | To          | Departure Time   | Arrival Time    |");
        System.out.printf("| %-14s| %-13s| %-12s| %-17s| %-15s|\n",
                flight.getFlightNumber(), flight.getDepartureCity(),
                flight.getDestinationCity(),
                flight.getDepartureTime(),
                flight.getArrivalTime()
        );
        System.out.println("+---------------------------------------------------------------------------------+");
    }

    public Passenger getPassengerByReservationId(String reservationId) {
        if (passengers.isEmpty()) {
            System.out.println("Passenger list is empty");
            return null;
        }
        for (int i = 0; i < passengers.size(); i++) {
            if (passengers.get(i).getReservationId().equalsIgnoreCase(reservationId)) {
                return passengers.get(i);
            }
        }
        return null;
    }

    public String printDate(LocalDateTime dateTime) {
        String date;
        if (dateTime != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            date = dateTime.format(formatter);
        } else {
            date = "N/A";
        }
        return date;
    }

    public String generateReservationCode(String type) {
        if (type.equalsIgnoreCase("Vip")) {
            lastGeneratedReservationVipID++;
            String format = "V%05d";
            String importCode = String.format(format, lastGeneratedReservationVipID);
            return importCode;
        } else if (type.equalsIgnoreCase("Normal")) {
            lastGeneratedReservationNormalID++;
            String format = "N%05d";
            String importCode = String.format(format, lastGeneratedReservationNormalID);
            return importCode;
        }
        return null;
    }

    public boolean checkPassengerBooked(Flight flight) {
        for (Passenger passenger : passengers) {
            if (passenger.getFlight().getFlightNumber().equalsIgnoreCase(flight.getFlightNumber())) {
                return true;
            }
        }
        return false;
    }

    public String chooseTypeOfSeat() {
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            printMenu();
            choice = validation.inputChoiceMain(2);
            switch (choice) {
                case 1:
                    return generateReservationCode(VIP);
                case 2:
                    return generateReservationCode(NORMAL);
            }
        } while (choice != 2);
        return null;
    }

    public static void printMenu() {
        System.out.println("Choose type of seat: ");
        System.out.println("+-------------------+");
        System.out.println("|       Type        |");
        System.out.println("|1.Vip.             |");
        System.out.println("|2.Normal.          |");
        System.out.println("+-------------------+");
    }

}
