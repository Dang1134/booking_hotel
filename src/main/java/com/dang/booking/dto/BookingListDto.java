package com.dang.booking.dto;

import com.dang.booking.model.PAYMENT_STATUS;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class BookingListDto{
    private HotelDto hotel;
    private List<BookingRoomDto> bookingRoomList;
    private LocalDateTime bookingDate;
    private double totalPrice;
    private double paymentAmount;
    private PAYMENT_STATUS paymentStatus;
    private LocalDateTime paymentDate;
}
