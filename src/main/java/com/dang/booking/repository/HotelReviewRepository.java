package com.dang.booking.repository;

import com.dang.booking.model.HotelReviews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelReviewRepository extends JpaRepository<HotelReviews,Integer> {
}
