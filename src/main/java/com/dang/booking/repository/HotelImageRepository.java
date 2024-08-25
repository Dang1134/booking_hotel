package com.dang.booking.repository;

import com.dang.booking.model.Hotel;
import com.dang.booking.model.HotelImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HotelImageRepository extends JpaRepository<HotelImage, Hotel> {
    Optional<HotelImage>  findByHotel(Hotel hotel);
}
