package com.dang.booking.model;

import com.dang.booking.model.key.BookingRoomId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity(name = "booking_room")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingRoom {

    @EmbeddedId
    private BookingRoomId bookingRoomId;

    @ManyToOne
    @JoinColumn(name = "booking_id", referencedColumnName = "id",insertable = false,updatable = false)
    private Booking booking;

    @ManyToOne
    @JoinColumn(name = "room_id",referencedColumnName = "id",insertable = false,updatable = false)
    private Room room;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", columnDefinition = "ENUM('PENDING','CONFIRMED','CHECKIN','CHECKOUT','CANCELLED') DEFAULT 'PENDING'")
    private BOOKING_ROOM_STATUS status;

    @Column(name = "checkin_date", columnDefinition = "TIMESTAMP")
    private LocalDate checkinDate;

    @Column(name = "checkout_date",columnDefinition = "TIMESTAMP")
    private LocalDate checkoutDate;

}
