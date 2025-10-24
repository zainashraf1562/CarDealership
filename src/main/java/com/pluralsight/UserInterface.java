package com.pluralsight;

import java.util.Scanner;

public class UserInterface {
    private Dealership dealership;

    private UserInterface(){}

    public void display(){
        Scanner scanner = new Scanner(System.in);

        boolean run = true;

        while(run){
            System.out.println("Welcome!");
        }

    }

    private void init(){
        DealershipFileManager fileManager = new DealershipFileManager();
        dealership = fileManager.getDealership();

    }

    public void processGetByPriceRequest(){}

    public void processGetByMakeModelRequest(){}

    public void processGetByYearRequest(){}

    public void processGetByColorRequest(){}

    public void processGetByMileageRequest(){}

    public void processGetByVehicleTypeRequest(){}

    public void processGetAllVehicleRequest(){}

    public void processAddVehicleRequest(){}

    public void processRemoveVehicleRequest(){}





}
