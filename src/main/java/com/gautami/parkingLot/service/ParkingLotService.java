package com.gautami.parkingLot.service;

import com.gautami.parkingLot.Model.Slot;
import com.gautami.parkingLot.Model.Ticket;
import com.gautami.parkingLot.exceptions.AlreadyExists;
import com.gautami.parkingLot.exceptions.BadRequest;
import com.gautami.parkingLot.exceptions.NotFound;
import com.gautami.parkingLot.repository.ParkingLotRepository;
import com.gautami.parkingLot.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ParkingLotService {
    @Autowired
    ParkingLotRepository parkingLotRepository;

    @Autowired
    TicketRepository ticketRepository;


    public void createParkingSlots(int capacity) {
        for (int i = 1; i <= capacity; i++) {
            Slot current = parkingLotRepository.findBySlotNumber(i);
            if (current != null) {
                throw new AlreadyExists("Parking slot with the slot number :" + i + " already exists");
            }
            Slot slot = new Slot();
            slot.setSlotNumber(i);
            slot.setAvailable(true);
            try {
                parkingLotRepository.save(slot);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public boolean parkCar(String registrationNumber, String colour, int slotNumber) {
        if(findFirstAvailableSlot()<1)throw new AlreadyExists("Slot Number: "+slotNumber+" not available");
        Ticket existing = ticketRepository.findByRegistrationNumber(registrationNumber);
        if (existing != null) {
            throw new AlreadyExists("Car with registration number already parked");
        }
        Ticket ticket = new Ticket();
        ticket.setColor(colour);
        ticket.setRegistrationNumber(registrationNumber);
        ticket.setSlotNumber(slotNumber);
        try {
            ticketRepository.save(ticket);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public int findFirstAvailableSlot() {
        List<Slot> slots = parkingLotRepository.findFirstAvailableSlot();
        if (!slots.isEmpty()) {
            Slot first = slots.get(0);
            first.setAvailable(false);
            parkingLotRepository.save(first);
            return first.getSlotNumber();
        }
        return -1;
    }
    public int findFirstAvailableSlotHelper() {
        List<Slot> slots = parkingLotRepository.findFirstAvailableSlot();
        if (!slots.isEmpty()) {
            Slot first = slots.get(0);
            first.setAvailable(false);
            return first.getSlotNumber();
        }
        return -1;
    }

    public boolean leave(int slotNumber) {
        Ticket currentTicket = ticketRepository.findBySlotNumber(slotNumber);
        Slot currentSlot = parkingLotRepository.findBySlotNumber(slotNumber);
        if (currentSlot.isAvailable())
            throw new BadRequest("The parking slot numbered :" + slotNumber + " is already free");
        currentSlot.setAvailable(true);
        try {
            parkingLotRepository.save(currentSlot);
            ticketRepository.delete(currentTicket);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    public void getStatus() {
        System.out.println("Slot No. Registration No Color");
        List<Ticket> allTickets = ticketRepository.findAll();
        for (Ticket i : allTickets) {
            System.out.println(i.getSlotNumber() + " " + i.getRegistrationNumber() + " " + i.getColor());
        }
    }

    public List<String> getRegistrationNumbersByColour(String colour) {
        List<Ticket> allTickets = ticketRepository.findByColorIgnoreCase(colour);
        List<String> regNo=new ArrayList<>();
        int count = 0;
        for (Ticket ticket : allTickets) {
            if (ticket.getColor().equalsIgnoreCase(colour)) {
                regNo.add(ticket.getRegistrationNumber());
                System.out.print(ticket.getRegistrationNumber());

                if (count < allTickets.size() - 1) {
                    System.out.print(",");
                }
                System.out.print(" ");
                count++;
            }
        }
        System.out.println();
        return regNo;
    }

    public int getSlotNumberOfCarWithRegistrationNumber(String registrationNo) {
        Ticket current=ticketRepository.findByRegistrationNumber(registrationNo);
        if(current!=null)return current.getSlotNumber();
        else throw new NotFound("Car with the registration number "+registrationNo+" not found in the parking");
    }

    public List<Integer> slotNumberOfAllCarsWithColor(String color) {
        List<Ticket> allTicket=ticketRepository.findByColorIgnoreCase(color);
        List<Integer> allSlotNumber=new ArrayList<>();
        int count = 0;
        for (Ticket ticket : allTicket) {
            allSlotNumber.add(ticket.getSlotNumber());
                System.out.print(ticket.getSlotNumber());

                if (count < allTicket.size() - 1) {
                    System.out.print(",");
                }
                System.out.print(" ");
                count++;
        }
        System.out.println();
        return allSlotNumber;
    }

    public void increaseParkingSlot(int value) {
        int lastSlotNumber=findLastAvailableSlot();
        for(int i=1;i<=value;i++){
            Slot slot=new Slot();
            slot.setAvailable(true);
            slot.setSlotNumber(lastSlotNumber);
            parkingLotRepository.save(slot);
            lastSlotNumber++;
        }
    }

    public int findLastAvailableSlot() {
        List<Slot> slots = parkingLotRepository.findLastSlot();
        if (!slots.isEmpty()) {
            return slots.get(0).getSlotNumber();
        }
        return -1;
    }
}
