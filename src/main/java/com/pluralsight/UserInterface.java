package com.pluralsight;

import java.util.List;
import java.util.Scanner;

public class UserInterface {
    private Dealership dealership;
    public static final String DEFAULT = "\u001B[0m";
    public static final String BLUE = "\u001B[34m";

    public UserInterface() {
    }

    private void init() {
        DealershipFileManager dealershipCars = new DealershipFileManager();
        dealership = dealershipCars.getDealership();
    }

    public void display() {
        Scanner scanner = new Scanner(System.in);
        init();

        boolean run = true;

        while (run) {
            System.out.println(BLUE +
                            "▄     ▜       ▌ ▘  \n" +
                            "▌▌█▌▀▌▐ █▌▛▘▛▘▛▌▌▛▌\n" +
                            "▙▘▙▖█▌▐▖▙▖▌ ▄▌▌▌▌▙▌\n" +
                            "                 ▌ " + DEFAULT
            );
            System.out.println("1- Find vehicles within a price range");
            System.out.println("2- Find vehicles by make/model");
            System.out.println("3- Find vehicles by year range");
            System.out.println("4- Find vehicles by color");
            System.out.println("5- Find vehicles by mileage range");
            System.out.println("6- Find vehicles by type (sedan, truck, SUV, van)");
            System.out.println("7- List ALL vehicles");
            System.out.println("8- Add a vehicle");
            System.out.println("9- Remove a vehicle");
            System.out.println("99- Quit");
            System.out.print("Your Input: ");

            int userInput = scanner.nextInt();
            scanner.nextLine();

            switch (userInput) {
                case 1 -> processGetByPriceRequest(scanner);
                case 2 -> processGetByMakeModelRequest(scanner);
                case 3 -> processGetByYearRequest(scanner);
                case 4 -> processGetByColorRequest(scanner);
                case 5 -> processGetByMileageRequest(scanner);
                case 6 -> processGetByVehicleTypeRequest(scanner);
                case 7 -> processGetAllVehiclesRequest();
                case 8 -> processAddVehicleRequest(scanner);
                case 9 -> processRemoveVehicleRequest(scanner);
                case 99 -> {
                    System.out.println("Exiting.. Goodbye.");
                    run = false;
                }
                default -> System.out.println("Try Again..");
            }
        }
        System.out.println(" ");
    }


    public void processGetByPriceRequest(Scanner scanner) {
        System.out.print("Enter the minimum value: ");
        double userMin = scanner.nextDouble();
        System.out.print("Enter the maximum value: ");
        double userMax = scanner.nextDouble();

        List<Vehicle> sorted = dealership.getVehiclesByPrice(userMin, userMax);
        displayVehicles(sorted);
    }

    public void processGetByMakeModelRequest(Scanner scanner){
        System.out.print("Enter the make: ");
        String make = scanner.nextLine();
        System.out.print("Enter the model: ");
        String model = scanner.nextLine();

        List<Vehicle> sorted = dealership.getVehiclesByMakeModel(make, model);
        displayVehicles(sorted);
    }

    public void processGetByYearRequest(Scanner scanner){
        System.out.print("Enter the minimum year: ");
        int minYear = scanner.nextInt();
        System.out.print("Enter the maximum year: ");
        int maxYear = scanner.nextInt();

        List<Vehicle> sorted = dealership.getVehiclesByYear(minYear, maxYear);
        displayVehicles(sorted);
    }

    public void processGetByColorRequest(Scanner scanner){
        System.out.print("Enter the color: ");
        String color = scanner.nextLine();

        List<Vehicle> sorted = dealership.getVehiclesByColor(color);
        displayVehicles(sorted);
    }

    public void processGetByMileageRequest(Scanner scanner){
        System.out.print("Enter the minimum mileage: ");
        int minMileage = scanner.nextInt();
        System.out.print("Enter the maximum mileage: ");
        int maxMileage = scanner.nextInt();

        List<Vehicle> sorted = dealership.getVehiclesByMileage(minMileage, maxMileage);
        displayVehicles(sorted);
    }

    public void processGetByVehicleTypeRequest(Scanner scanner){
        System.out.print("Enter the Vehicle Type: ");
        String type = scanner.nextLine();

        List<Vehicle> sorted = dealership.getVehiclesByType(type);
        displayVehicles(sorted);
    }

    public void processGetAllVehiclesRequest(){
        List<Vehicle> vehicle = dealership.getAllVehicles();
        displayVehicles(vehicle);
    }

    public void processAddVehicleRequest(Scanner scanner){
        System.out.print("Enter the vin: ");
        int vin = scanner.nextInt();
        System.out.print("Enter the year: ");
        int year = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter the make: ");
        String make = scanner.nextLine();
        System.out.print("Enter the model: ");
        String model = scanner.nextLine();
        System.out.print("Enter the Vehicle Type: ");
        String vehicleType = scanner.nextLine();
        System.out.print("Enter the Color: ");
        String color = scanner.nextLine();
        System.out.print("Enter the odometer: ");
        int odometer = scanner.nextInt();
        System.out.print("Enter the Price: ");
        double price = scanner.nextDouble();

        Vehicle vehicle = new Vehicle(vin, year, make, model, vehicleType, color, odometer, price);
        dealership.addVehicle(vehicle);
        DealershipFileManager dealershipFileManager = new DealershipFileManager();
        dealershipFileManager.saveDealership(dealership);

        System.out.println("Vehicle Successfully Added");
    }

    public void processRemoveVehicleRequest(Scanner scanner){
        DealershipFileManager dealershipFileManager = new DealershipFileManager();
        System.out.print("Enter the vin: ");
        int removeVin = scanner.nextInt();

        for (Vehicle vehicle : dealership.getAllVehicles()) {
            if (removeVin == vehicle.getVin()){
                dealership.removeVehicle(vehicle);
                dealershipFileManager.saveDealership(dealership);
                return;
            }
        }
        System.out.println("Invalid VIN");
    }

    private void displayVehicles(List<Vehicle> vehicle) {
        System.out.println();
        System.out.println(BLUE+"Year |  Make  |  Model  |  Color  |  Price  |  Miles");
        System.out.println("======================================================"+DEFAULT);
        for (Vehicle vehicle1 : vehicle) {
            System.out.println(vehicle1.getYear() + " | " + vehicle1.getMake() + " | " + vehicle1.getModel() + " | " + vehicle1.getColor() + " | $" + vehicle1.getPrice() + " | " + vehicle1.getOdometer() +
                    " miles");
        }
        System.out.println();
    }

}

