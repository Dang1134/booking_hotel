package com.dang.booking.dto;

import com.dang.booking.model.BOOKING_ROOM_STATUS;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingRoomDto {
    private RoomDto room;
    private BOOKING_ROOM_STATUS status;
    private LocalDate checkinDate;
    private LocalDate checkoutDate;
}
