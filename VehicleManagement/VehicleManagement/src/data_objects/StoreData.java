/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data_objects;

import java.io.FileWriter;
import java.io.IOException;
import object.Vehicle;

/**
 *
 * @author Admin
 */
public class StoreData {
    private static final String VEHICLE_FILE = "src\\database\\vehicle.dat";

    public void storeVehiclesToFile(VehicleDao vehicleDao) {
        try {
            if (vehicleDao.getVehicles().isEmpty()) {
                System.out.println("There are no current vehicles!!");
                return;
            }
            FileWriter writer = new FileWriter(VEHICLE_FILE);
            for (Vehicle x : vehicleDao.getVehicles()) {
                writer.write(x.toString() + "\n");
            }
            writer.close();
            System.out.println("Write succesfully!!! " + VEHICLE_FILE);
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file: " + e.getMessage());
        }
    }
}
