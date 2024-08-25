package com.dang.booking.repository;

import com.dang.booking.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking,Integer> {
    List<Booking> findByUserId(Long userId);
    List<Booking> findByHotelId(int hotelId);

}
