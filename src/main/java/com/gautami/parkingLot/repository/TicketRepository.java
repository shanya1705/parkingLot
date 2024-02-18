package com.gautami.parkingLot.repository;

import com.gautami.parkingLot.Model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket,Long> {
    Ticket findByRegistrationNumber(String registrationNumber);

    Ticket findBySlotNumber(int slotNumber);

    List<Ticket> findByColorIgnoreCase(String color);
}
