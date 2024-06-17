package com.Thienbao.booking.dto;

import com.Thienbao.booking.model.PAYMENT_STATUS;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class BookingListOfHotelierDto {
    private UserDto user;
    private LocalDateTime bookingDate;
    private double totalPrice;
    private double paymentAmount;
    private PAYMENT_STATUS paymentStatus;
    private LocalDateTime paymentDate;
    private List<BookingRoomDto> bookingRoomList;
}
