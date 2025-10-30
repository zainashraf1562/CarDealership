package com.pluralsight;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class DealershipFileManager {

    public static final String FILE_NAME = "dealership.csv";

    public Dealership getDealership(){

        try {

            BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME));

            String line;
            line = reader.readLine();
            String[] parts = line.split("\\|");
            Dealership dealership = new Dealership(parts[0],parts[1],parts[2]);

            while ((line =reader.readLine()) !=null) {

                String[] tokens = line.split(("\\|"));
                int vin = Integer.parseInt(tokens[0]);
                int year = Integer.parseInt(tokens[1]);
                String make = tokens[2];
                String model = tokens[3];
                String vehicleType = tokens[4];
                String color = tokens[5];
                int odometer = Integer.parseInt(tokens[6]);
                double price = Double.parseDouble(tokens[7]);

                Vehicle newVehicle = new Vehicle(vin,year,make,model,vehicleType,color,odometer,price);
                dealership.addVehicle(newVehicle);
            }
            reader.close();
            return dealership;
        } catch (Exception e) {
            System.out.println("Error Reading File: " + FILE_NAME);
            return null;
        }

    }

    public void saveDealership(Dealership dealership){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME));
            writer.write(dealership.getName() + "|" + dealership.getAddress() + "|" + dealership.getPhone());
            writer.newLine();

            for (Vehicle vehicle1 : dealership.getAllVehicles()) {
                writer.write(vehicle1.getVin() + "|" + vehicle1.getYear() + "|" + vehicle1.getMake()
                        + "|" + vehicle1.getModel() + "|" + vehicle1.getVehicleType() + "|" + vehicle1.getColor()
                        + "|" + vehicle1.getOdometer() + "|" + vehicle1.getPrice());
                writer.newLine();
            }
            writer.close();

        } catch (Exception ex){
            System.err.println("ERROR SAVING....");
        }
    }
}
