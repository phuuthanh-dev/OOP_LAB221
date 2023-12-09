/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data_objects;

import java.io.FileNotFoundException;
import java.io.FileReader;
import util.Exceptions;
import objects.Flight;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import objects.Crew;

/**
 *
 * @author Admin
 */
public class FlightManagement {

    public String header = String.format("|%-15s|%-13s|%-12s|%-18s|%-18s|%-11s|%-14s|", " Flight Number", " From", " To", " Departure Time", " Arrival Time", " Seats Vip", " Seats Normal");
    public static final String VIP = "Vip";
    public static final String NORMAL = "Normal";
    Scanner sc = new Scanner(System.in);
    List<Flight> flights = new ArrayList<>();
    Exceptions validation = new Exceptions();

    public void createNewFlight() {
        String flightNumber;
        String departureCity;
        String destinationCity;
        LocalDateTime departureTime, flightTime, arrivalTime;
        List<Integer> availableSeats = null;
        while (true) {

            while (true) {
                flightNumber = validation.inputFlightNumber("Input flight number (Fxyzt): ", "Your input must be under "
                        + "the format of Fxyzt, xyzt stands for a digit",
                        "^[F|d]\\d{4}$");
                if (isFlightNumberDuplicate(flightNumber)) {
                    System.out.println("Flight number " + flightNumber + " already exists!");
                } else {
                    break;
                }
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

            departureTime = inputDateTime("Departure time (dd/MM/yyyy HH:mm): ");
            flightTime = inputTime("Flight time (HH:mm): ");
            arrivalTime = departureTime.plusHours(flightTime.getHour()).plusMinutes(flightTime.getMinute());
            System.out.println("Arrival time: " + printDate(arrivalTime));

            int totalSeat = validation.getAnInteger("Input number seat: ", "Seat number does not exist!");

            Flight flight = new Flight(flightNumber, departureCity, destinationCity, departureTime, arrivalTime, totalSeat);
            flights.add(flight);

            System.out.println("Create flight successfully");

            System.out.println("Do you want do create more Flight?");
            boolean ok = confirm();

            if (!ok) {
                System.out.println("Quitting!");
                break;
            }
        }
    }

    public boolean confirm() {
        System.out.println("Enter your choice(No -> 0, Yes -> 1)");
        int result = Integer.parseInt(sc.nextLine());
        if (result == 1) {
            return true;
        } else {
            return false;
        }
    }

    public LocalDateTime inputDateTime(String msg) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime dateTime = null;
        String dateTimeString;
        while (dateTime == null) {
            try {
                System.out.print(msg);
                dateTimeString = sc.nextLine().trim();
                dateTime = LocalDateTime.parse(dateTimeString, formatter);
            } catch (Exception e) {
                System.out.println("Invalid date and time format. Please use dd/MM/yyyy HH:mm.");
            }
        }
        return dateTime;
    }

    public LocalDateTime inputTime(String msg) {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalDateTime currentDateTime = LocalDateTime.now();
        LocalTime time = null;
        String timeString;
        while (time == null) {
            try {
                System.out.print(msg);
                timeString = sc.nextLine().trim();
                time = LocalTime.parse(timeString, timeFormatter);
            } catch (Exception e) {
                System.out.println("Invalid time format. Please use HH:mm.");
            }
        }
        LocalDateTime resultDateTime = currentDateTime.with(time);
        return resultDateTime;
    }

    public void getFlightFromFile(String path) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            Scanner scanner = new Scanner(new FileReader(path));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] data = line.split(",");

                if (data.length > 1) {
                    String flightNumber = data[0];
                    if (isFlightNumberDuplicate(flightNumber)) {
                        continue;
                    }
                    String departureCity = data[1];
                    String destinationCity = data[2];
                    LocalDateTime departureTime = LocalDateTime.parse(data[3].trim(), formatter);
                    LocalDateTime arrivalTime = LocalDateTime.parse(data[4].trim(), formatter);
                    int totalSeats = Integer.parseInt(data[5]);

                    Flight flight = new Flight(flightNumber, departureCity, destinationCity, departureTime, arrivalTime, totalSeats);
                    flights.add(flight);
                }
            }
            System.out.println("Add successfully!");
            sortFlightByDateDes();
            printListFlight();
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public boolean isFlightNumberDuplicate(String flightNumber) {
        for (Flight x : flights) {
            if (x.getFlightNumber().equalsIgnoreCase(flightNumber)) {
                return true;
            }
        }
        return false;
    }

    public void sortFlightByDateDes() {
        for (int i = 0; i < flights.size(); i++) {
            for (int j = i + 1; j < flights.size() - 1; j++) {
                if (flights.get(i).getDepartureTime().isBefore(flights.get(j).getDepartureTime())) {
                    Flight tmp = flights.get(i);
                    flights.set(i, flights.get(j));
                    flights.set(j, tmp);
                }
            }
        }
    }

    public void printListFlight() {
        if (flights.isEmpty()) {
            System.out.println("Flight list is empty!");
            return;
        }
        System.out.println("Here is the list of flights");
        System.out.println(header);

        for (Flight flight : flights) {
            System.out.printf("| %-14s| %-12s| %-11s| %-17s| %-17s| %-10s| %-13s|\n",
                    flight.getFlightNumber(), flight.getDepartureCity(), flight.getDestinationCity(), flight.getDepartureTime(), flight.getArrivalTime(), flight.getAvailableSeatsVip().size(), flight.getAvailableSeatsNormal().size());

        }
    }

    public List<Flight> getFlights() {
        return flights;
    }

    public Flight findFlightByNumber(String flightNumber) {
        for (int i = 0; i < flights.size(); i++) {
            if (flights.get(i).getFlightNumber().equalsIgnoreCase(flightNumber)) {
                return flights.get(i);
            }
        }
        return null;
    }

    public void updateFlight(List<Crew> crews) {
        String flightNumber = validation.getString("Enter the flight number you want to update: ", "This field cannot be left blank!");
        Flight flightUpdate = findFlightByNumber(flightNumber);
        if (flightUpdate != null) {
            menuUpdateFlight(flightUpdate, crews);
            return;
        } else {
            System.out.println("Flight not found!");
        }
    }

    public void menuUpdateFlight(Flight flightUpdate, List<Crew> crews) {
        int choice;
        do {
            System.out.println("+----------------------------------+");
            System.out.println("|Update Flight :                   |");
            System.out.println("|1. Update departureCity           |");
            System.out.println("|2. Update destinationCity         |");
            System.out.println("|3. Update departureTime           |");
            System.out.println("|4. Update arrivalTime             |");
            System.out.println("|5. Update availableSeats Normal   |");
            System.out.println("|6. Update availableSeats Vip      |");
            System.out.println("|7. Delete crew                    |");
            System.out.println("|8. Return to Main Menu!           |");
            System.out.println("+----------------------------------+");

            choice = validation.inputChoiceMain(8);

            switch (choice) {
                case 1:
                    String departureCity = validation.getString("Enter the new departure city: ", "This field cannot be left blank!");
                    boolean ok = validation.confirm("Confirm you want to update?(No -> 0, Yes -> 1)");
                    if (ok) {
                        flightUpdate.setDepartureCity(departureCity);
                        System.out.println("Update successfully!");
                    } else {
                        System.out.println("Update fail!!");
                    }
                    break;
                case 2:
                    String destinationCity = validation.getString("Enter the new destination city: ", "This field cannot be left blank!");
                    boolean ok1 = validation.confirm("Confirm you want to update?(No -> 0, Yes -> 1)");
                    if (ok1) {
                        flightUpdate.setDestinationCity(destinationCity);
                        System.out.println("Update successfully!");
                    } else {
                        System.out.println("Update fail!!");
                    }
                    break;
                case 3:
                    LocalDateTime departureTime = inputDateTime("Enter the new departure time (dd/MM/yyyy HH:mm): ");
                    boolean ok2 = validation.confirm("Confirm you want to update?(No -> 0, Yes -> 1)");
                    if (ok2) {
                        flightUpdate.setDepartureTime(departureTime);
                        System.out.println("Update successfully!");
                    } else {
                        System.out.println("Update fail!!");
                    }
                    break;
                case 4:
                    LocalDateTime flightTime = inputTime("Flight time (HH:mm): ");
                    LocalDateTime arrivalTime = flightUpdate.getDepartureTime().plusHours(flightTime.getHour()).plusMinutes(flightTime.getMinute());
                    boolean ok4 = validation.confirm("Confirm you want to update?(No -> 0, Yes -> 1)");
                    if (ok4) {
                        flightUpdate.setArrivalTime(arrivalTime);
                        System.out.println("Update successfully!");
                    } else {
                        System.out.println("Update fail!!");
                    }
                    break;
                case 5:
                    int availableSeatsNormal = validation.getAnInteger("Enter the new available seats normal: ", "Seat number does not exist!");
                    boolean ok5 = validation.confirm("Confirm you want to update?(No -> 0, Yes -> 1)");
                    if (ok5) {
                        flightUpdate.setAvailableSeatsNormal(availableSeatsNormal);
                        System.out.println("Update successfully!");
                    }
                    break;

                case 6:
                    int availableSeatsVip = validation.getAnInteger("Enter the new available seats vip: ", "Seat number does not exist!");
                    boolean ok6 = validation.confirm("Confirm you want to update?(No -> 0, Yes -> 1)");
                    if (ok6) {
                        flightUpdate.setAvailableSeatsVip(availableSeatsVip);
                        System.out.println("Update successfully!");
                    }
                    break;

                case 7:
                    if (flightUpdate.getCrew() == null) {
                        System.out.println("Don't have crew");
                        break;
                    }
                    String crewId = validation.getString("Input crew id: ", "This field cannot be left blank!");
                    for (Crew crew : crews) {
                        if (crew.getCrewId().equalsIgnoreCase(crewId)) {
                            System.out.println("Crew id: " + crewId);
                            System.out.println(crew.toString());
                            boolean ok7 = validation.confirm("Confirm you want to delete?(No -> 0, Yes -> 1)");
                            if (ok7) {
                                flightUpdate.setCrew(null);
                                System.out.println("Delete successfully!");
                            }
                        }
                    }
                    break;
                case 8:
                    break;
            }
        } while (choice != 8);
    }

    public void deleteFlight(PassengersDao passengersDao) {
        printListFlight();
        String flightNumber = validation.getString("Enter the flight number you want to delete: ", "This field cannot be left blank!");
        Flight flightDelete = findFlightByNumber(flightNumber);
        boolean hasBooked = passengersDao.checkPassengerBooked(flightDelete);
        if (!hasBooked) {
            boolean ok = validation.confirm("Confirm you want to delete?(No -> 0, Yes -> 1)");
            if (ok) {
                flights.remove(flightDelete);
                System.out.println("Delete successfully!");
            } else {
                System.out.println("Delete fail!!");
            }
        } else {
            System.out.println("Flights that have been booked cannot be deleted!");
        }
    }

    public String printDate(LocalDateTime dateTime) {
        String date;
        if (dateTime != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            date = dateTime.format(formatter);
        } else {
            date = "N/A";
        }
        return date;
    }

}
