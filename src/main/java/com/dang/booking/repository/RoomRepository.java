package com.dang.booking.repository;

import com.dang.booking.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {
    List<Room> findByRoomTypeId(int roomTypeId);
   // List<Room> findById(int roomId);
}
