package com.dang.booking.repository;

import com.dang.booking.model.Amenities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface AmenitiesRepository extends JpaRepository<Amenities , Integer> {
}
