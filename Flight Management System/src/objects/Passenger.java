/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

/**
 *
 * @author Admin
 */
public class Passenger {
    private String name;
    private String phone;
    private Flight flight;
    private String reservationId;
    private int localSeat = -1;

    public Passenger(String name, String phone, Flight flight, String reservationId) {
        this.name = name;
        this.phone = phone;
        this.flight = flight;
        this.reservationId = reservationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String Name) {
        this.name = Name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String Phone) {
        this.phone = Phone;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public String getReservationId() {
        return reservationId;
    }

    public void setReservationId(String reservationId) {
        this.reservationId = reservationId;
    }

    public int getLocalSeat() {
        return localSeat;
    }

    public void setLocalSeat(int localSeat) {
        this.localSeat = localSeat;
    }

    @Override
    public String toString() {
        return name + "," + phone + "," + flight + "," + reservationId + "," + localSeat;
    }
    
    
    
    

}
