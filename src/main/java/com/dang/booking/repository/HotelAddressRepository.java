package com.dang.booking.repository;

import com.dang.booking.model.Hotel;
import com.dang.booking.model.HotelAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HotelAddressRepository extends JpaRepository<HotelAddress, Hotel> {
    Optional<HotelAddress> findByHotel(Hotel hotel);
}
