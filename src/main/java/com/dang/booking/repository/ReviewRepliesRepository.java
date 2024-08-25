package com.dang.booking.repository;

import com.dang.booking.model.ReviewReplies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepliesRepository extends JpaRepository<ReviewReplies,Integer> {
}
