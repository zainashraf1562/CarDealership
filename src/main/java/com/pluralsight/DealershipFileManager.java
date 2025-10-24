package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;

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

    }
}
