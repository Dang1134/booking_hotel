package com.dang.booking.dto;

import com.dang.booking.model.PAYMENT_STATUS;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingDto {
    private int hotelId;
    private LocalDateTime bookingDate;
    private double totalPrice;
    private double paymentAmount;
    private PAYMENT_STATUS paymentStatus;
    private LocalDateTime paymentDate;
    private BookingRoomDto bookingRoom;

}
