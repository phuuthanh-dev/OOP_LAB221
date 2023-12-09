/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class Flight {

    private String flightNumber;
    private String departureCity;
    private String destinationCity;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private List<Integer> availableSeatsVip = new ArrayList<>();
    private List<Integer> availableSeatsNormal = new ArrayList<>();
    private Crew crew = null;

    public Flight(String flightNumber, String departureCity, String destinationCity, LocalDateTime departureTime, LocalDateTime arrivalTime, int totalSeats) {
        this.flightNumber = flightNumber;
        this.departureCity = departureCity;
        this.destinationCity = destinationCity;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        if (totalSeats % 2 == 0) {
            for (int i = 1; i < totalSeats / 2; i++) {
                this.availableSeatsVip.add(i);
            }
            for (int i = totalSeats / 2; i <= totalSeats; i++) {
                this.availableSeatsNormal.add(i);
            }
        } else if (totalSeats % 2 != 0) {
           for (int i = 1; i <= totalSeats / 2; i++) {
                this.availableSeatsVip.add(i);
            }
            for (int i = totalSeats - availableSeatsVip.size(); i <= totalSeats; i++) {
                this.availableSeatsNormal.add(i);
            } 
        }
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getDepartureCity() {
        return departureCity;
    }

    public void setDepartureCity(String departureCity) {
        this.departureCity = departureCity;
    }

    public String getDestinationCity() {
        return destinationCity;
    }

    public void setDestinationCity(String destinationCity) {
        this.destinationCity = destinationCity;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public List<Integer> getAvailableSeatsVip() {
        return availableSeatsVip;
    }

    public List<Integer> getAvailableSeatsNormal() {
        return availableSeatsNormal;
    }

    public void setAvailableSeatsVip(int availableSeats) {
        this.availableSeatsVip.clear();
        for (int i = 1; i <= availableSeats; i++) {
            this.availableSeatsVip.add(i);
        }
    }

    public void setAvailableSeatsNormal(int availableSeats) {
        this.availableSeatsNormal.clear();
        for (int i = 1; i <= availableSeats; i++) {
            this.availableSeatsNormal.add(i);
        }
    }

    public Crew getCrew() {
        return crew;
    }

    public void setCrew(Crew crew) {
        this.crew = crew;
    }

    @Override
    public String toString() {
        return flightNumber + "," + departureCity
                + "," + destinationCity + "," + departureTime
                + "," + arrivalTime + "," + availableSeatsVip.size()
                + "," + availableSeatsNormal.size();
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
