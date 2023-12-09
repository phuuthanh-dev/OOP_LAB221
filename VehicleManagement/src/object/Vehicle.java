/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package object;

import java.time.Year;

public class Vehicle {
    private String idVehicle;
    private String nameVehicle;
    private String colorVehicle;
    private long priceVehicle;
    private String brandVehicle;
    private String type;
    private Year year;

    public Vehicle(String idVehicle, String nameVehicle, String colorVehicle, long priceVehicle, String brandVehicle, String type, Year year) {
        this.idVehicle = idVehicle;
        this.nameVehicle = nameVehicle;
        this.colorVehicle = colorVehicle;
        this.priceVehicle = priceVehicle;
        this.brandVehicle = brandVehicle;
        this.type = type;
        this.year = year;
    }

    public String getIdVehicle() {
        return idVehicle;
    }

    public void setIdVehicle(String idVehicle) {
        this.idVehicle = idVehicle;
    }

    public String getNameVehicle() {
        return nameVehicle;
    }

    public void setNameVehicle(String nameVehicle) {
        this.nameVehicle = nameVehicle;
    }

    public String getColorVehicle() {
        return colorVehicle;
    }

    public void setColorVehicle(String colorVehicle) {
        this.colorVehicle = colorVehicle;
    }

    public long getPriceVehicle() {
        return priceVehicle;
    }

    public void setPriceVehicle(long priceVehicle) {
        this.priceVehicle = priceVehicle;
    }

    public String getBrandVehicle() {
        return brandVehicle;
    }

    public void setBrandVehicle(String brandVehicle) {
        this.brandVehicle = brandVehicle;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Year getYear() {
        return year;
    }

    public void setYear(Year year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return idVehicle + "," + nameVehicle + "," + colorVehicle + "," + priceVehicle + "," + brandVehicle + "," + type + "," + year;
    }
}
