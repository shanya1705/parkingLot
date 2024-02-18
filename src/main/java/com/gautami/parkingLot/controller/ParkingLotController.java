package com.gautami.parkingLot.controller;

import com.gautami.parkingLot.Model.Ticket;
import com.gautami.parkingLot.service.ParkingLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parking")
public class ParkingLotController {

    @Autowired
    ParkingLotService parkingLotService;

    @PostMapping("/createSlots/{capacity}")
    public void createParkingLot(@PathVariable int capacity) {
        parkingLotService.createParkingSlots(capacity);
    }

    @PostMapping("/increaseParkingSlot/{value}")
    public void increaseParkingSlot(@PathVariable int value){
        parkingLotService.increaseParkingSlot(value);
    }

    @PostMapping("/parkCar/{registrationNumber}/{colour}/{slotNumber}")
    public boolean parkCar(@PathVariable String registrationNumber, @PathVariable String colour,@PathVariable int slotNumber) {
        return parkingLotService.parkCar(registrationNumber,colour,slotNumber);
    }

    @PostMapping("/freeSlots/{slotNumber}")
    public boolean leave(@PathVariable int slotNumber) {
         return parkingLotService.leave(slotNumber);
    }



    public void getStatus() {
        parkingLotService.getStatus();
    }

    @GetMapping("/registrationNumber/{colour}")
    public List<String> getRegistrationNumbersByColour(@PathVariable String colour) {
        return parkingLotService.getRegistrationNumbersByColour(colour);
    }

    @GetMapping("/slotsNumber/{registrationNo}")
    public Integer getSlotNumberOfCarWithRegistrationNumber(@PathVariable String registrationNo) {
        return parkingLotService.getSlotNumberOfCarWithRegistrationNumber(registrationNo);
    }

    @GetMapping("/slotNumbers/{color}")
    public List<Integer> slotNumberOfAllCarsWithColor(@PathVariable String color) {
        return parkingLotService.slotNumberOfAllCarsWithColor(color);
    }
}
