package com.dang.booking.payload.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CancelBookingRequest {
    @NotNull(message = "booking id not null")
    private long bookingId;

    @NotNull(message = "room id not null")
    private int roomId;

}
