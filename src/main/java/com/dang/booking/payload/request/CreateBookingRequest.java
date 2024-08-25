package com.dang.booking.payload.request;

import com.dang.booking.validation.ValidDateTimeFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateBookingRequest {

    @NotNull(message = "Id hotel not null")
    private int hotelId;

    @NotNull(message = "total price not null")
    private double totalPrice;

    @NotNull(message = "Id room not null")
    private int roomId;

    @NotBlank(message = "Checkin Date cannot be blank")
    @ValidDateTimeFormat(pattern = "yyyy-MM-dd", message = "Invalid date-time format. Expected format: yyyy-MM-dd")
    private String checkinDate;

    @NotBlank(message = "Checkout Date cannot be blank")
    @ValidDateTimeFormat(pattern = "yyyy-MM-dd", message = "Invalid date-time format. Expected format: yyyy-MM-dd")
    private String checkoutDate;

    @NotNull(message = "payment Amount not null")
    private double paymentAmount;
}
