package com.gautami.parkingLot.shell;

import com.gautami.parkingLot.controller.ParkingLotController;
import com.gautami.parkingLot.service.ParkingLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class InteractiveShell implements CommandLineRunner {

    @Autowired
    private ParkingLotController parkingLotController;

    @Autowired
    ParkingLotService parkingLotService;

    @Override
    public void run(String... args) {
        Scanner scanner = new Scanner(System.in);
        String command;

        System.out.println("Welcome to the Parking Lot System!");

        while (true) {
            System.out.print("Enter command: ");
            command = scanner.nextLine();

            if (command.equals("exit")) {
                System.out.println("Exiting the Parking Lot System. Goodbye!");
                break;
            }

            processCommand(command);
        }

        scanner.close();
    }

    private void processCommand(String command) {
        String[] tokens = command.split(" ");

        if (tokens.length == 0) {
            System.out.println("Invalid command. Please try again.");
            return;
        }

        String action = tokens[0].toLowerCase();

        switch (action) {
            case "create_parking_lot":
                if (tokens.length == 2) {
                    int capacity = Integer.parseInt(tokens[1]);
                    parkingLotController.createParkingLot(capacity);
                } else {
                    System.out.println("Invalid command. Usage: create_parking_lot <capacity>");
                }
                break;

            case "park":
                if (tokens.length == 3) {
                    String registrationNumber = tokens[1];
                    String colour = tokens[2];
                    int availableSlot=parkingLotService.findFirstAvailableSlotHelper();
                    if(availableSlot<1) System.out.println("Sorry, parking lot is full ");
                    else {
                        boolean parked=parkingLotController.parkCar(registrationNumber, colour,availableSlot);
                        if(parked) System.out.println("Allocated slot number: "+availableSlot );
                    }
                } else {
                    System.out.println("Invalid command. Usage: park <registration_number> <colour>");
                }
                break;

            case "leave":
                if (tokens.length == 2) {
                    int slotNumber = Integer.parseInt(tokens[1]);
                    boolean leave=parkingLotController.leave(slotNumber);
                    if(leave) System.out.println("Slot number "+slotNumber+" is free");
                } else {
                    System.out.println("Invalid command. Usage: leave <slot_number>");
                }
                break;

            case "status":
                parkingLotController.getStatus();
                break;

            case "registration_numbers_for_cars_with_colour":
                if (tokens.length == 2) {
                    String colour = tokens[1];
                    parkingLotController.getRegistrationNumbersByColour(colour);
                } else {
                    System.out.println("Invalid command. Usage: registration_numbers_for_cars_with_colour <colour>");
                }
                break;

            case "slot_number_of_car_with_registration":
                if (tokens.length == 2) {
                    String registrationNo = tokens[1];
                    int slotNumber= parkingLotController.getSlotNumberOfCarWithRegistrationNumber(registrationNo);
                    System.out.println(registrationNo+": "+slotNumber);
                } else {
                    System.out.println("Invalid command. Usage: registration_numbers_for_cars_with_colour <colour>");
                }
                break;

            case "slot_numbers_of_all_cars_of_color":
                if (tokens.length == 2) {
                    String color = tokens[1];
                     parkingLotController.slotNumberOfAllCarsWithColor(color);
                } else {
                    System.out.println("Invalid command. Usage: registration_numbers_for_cars_with_colour <colour>");
                }
                break;

            default:
                System.out.println("Invalid command. Please try again.");
        }
    }
}
