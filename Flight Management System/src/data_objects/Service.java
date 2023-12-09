/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data_objects;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;
import objects.Flight;
import objects.Passenger;
import util.Exceptions;

/**
 *
 * @author Admin
 */
public class Service {

    Scanner sc = new Scanner(System.in);
    Exceptions validation = new Exceptions();
    List<Passenger> passengers = null;
    List<Flight> flights = null;

    public Service(List<Passenger> passengers, List<Flight> flights) {
        this.passengers = passengers;
        this.flights = flights;
    }

    public Passenger checkIn() {
        String reservationId = validation.getString("Input reservation ID: ", "Reservation id is not valid!");
        Passenger passenger = null;
        for (Passenger x : passengers) {
            if (x.getReservationId().equalsIgnoreCase(reservationId)) {
                passenger = x;
            }
        }
        return passenger;
    }

    public void generateBoardingPasses() {
        Passenger passenger = checkIn();
        if (passenger == null) {
            System.out.println("No passengers found!");
            return;
        }
        Flight flight = passenger.getFlight();
        System.out.println("+---------------------------------------------------------------------------------+");
        System.out.println("|                                BOARDING PASSES                                  |");
        System.out.printf("|Passenger Name: %65s|\n", passenger.getName());
        System.out.printf("|Passenger Phone: %64s|\n", passenger.getPhone());
        System.out.printf("|Passenger Reservation Id: %55s|\n", passenger.getReservationId());
        System.out.printf("|Passenger Local Seat: %59s|\n", passenger.getLocalSeat());
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

    public void displayAvailableSeats() {
        System.out.println("Available Seats for Flights:");
        for (Flight flight : flights) {
            System.out.println("Flight: " + flight.getFlightNumber());
            List<Integer> availableSeatsNormal = flight.getAvailableSeatsNormal();
            System.out.println("Available Seats Normal: " + availableSeatsNormal);
            List<Integer> availableSeatsVip = flight.getAvailableSeatsVip();
            System.out.println("Available Seats Vip: " + availableSeatsVip);
        }
    }

    public void displaySeats(Flight flight, String reservationId) {
        System.out.println("Available Seats for Flights:");
        System.out.println("Flight: " + flight.getFlightNumber());
        if (reservationId.startsWith("N")) {
            List<Integer> availableSeatsNormal = flight.getAvailableSeatsNormal();
            System.out.println("Available Seats Normal: " + availableSeatsNormal);
        } else {
            List<Integer> availableSeatsVip = flight.getAvailableSeatsVip();
            System.out.println("Available Seats Vip: " + availableSeatsVip);
        }
    }

    public void selectSeat() {
        Passenger passenger = checkIn();
        if (passenger == null) {
            System.out.println("Reservation not found!!!");
            return;
        } else if (passenger.getLocalSeat() != -1) {
            System.out.println("This passenger has booked a seat");
            return;
        }

        Flight flight = passenger.getFlight();
        displaySeats(flight, passenger.getReservationId());

        List<Integer> availableSeatsNormal = flight.getAvailableSeatsNormal();
        List<Integer> availableSeatsVip = flight.getAvailableSeatsVip();

        if (availableSeatsNormal.isEmpty() && availableSeatsVip.isEmpty()) {
            System.out.println("No available seats for this flight.");
        } else {
            int chosenSeat = validation.getAnInteger("Enter the seat number you want to choose:", "Invalid value!!");

            if (passenger.getReservationId().startsWith("N") && availableSeatsNormal.contains(chosenSeat)) {
                availableSeatsNormal.remove(Integer.valueOf(chosenSeat));
                passenger.setLocalSeat(chosenSeat);
                System.out.println("Seat " + chosenSeat + " allocated for Reservation ID: " + passenger.getReservationId());
                return;
            } else if (passenger.getReservationId().startsWith("V") && availableSeatsVip.contains(chosenSeat)) {
                availableSeatsVip.remove(Integer.valueOf(chosenSeat));
                passenger.setLocalSeat(chosenSeat);
                System.out.println("Seat " + chosenSeat + " allocated for Reservation ID: " + passenger.getReservationId());
                return;
            } else {
                System.out.println("Invalid seat selection.");
            }
        }
    }
}
