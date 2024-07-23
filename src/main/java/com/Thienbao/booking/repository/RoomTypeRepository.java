package com.Thienbao.booking.repository;

import com.Thienbao.booking.model.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface RoomTypeRepository extends JpaRepository<RoomType , Integer> {
    List<RoomType> findRoomTypeById(int roomTypeId);
}
