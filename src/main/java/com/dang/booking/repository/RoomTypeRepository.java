package com.dang.booking.repository;

import com.dang.booking.model.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public interface RoomTypeRepository extends JpaRepository<RoomType , Integer> {
    List<RoomType> findRoomTypeById(int roomTypeId);


}
