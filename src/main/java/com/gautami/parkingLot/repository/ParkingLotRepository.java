package com.gautami.parkingLot.repository;

import com.gautami.parkingLot.Model.Slot;
import com.gautami.parkingLot.Model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface ParkingLotRepository extends JpaRepository<Slot,Long> {

    Slot findBySlotNumber(int slotNumber);

    @Query("SELECT s FROM Slot s WHERE s.available = true ORDER BY s.slotNumber ASC")
    List<Slot> findFirstAvailableSlot();

    @Query("SELECT s FROM Slot s ORDER BY s.slotNumber DESC")
    List<Slot> findLastSlot();


}
