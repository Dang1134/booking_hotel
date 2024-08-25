package com.dang.booking.repository;

import com.dang.booking.model.BookingRoom;
import com.dang.booking.model.key.BookingRoomId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRoomRepository extends JpaRepository<BookingRoom, BookingRoomId> {
    BookingRoom findByBookingIdAndRoomId(long booking_id, int room_id);
}
