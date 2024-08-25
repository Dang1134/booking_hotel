package com.dang.booking.payload.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateHotelByAdminRequest {

    @NotNull(message = "Id hotel not null")
    private int hotelId;

    @NotNull(message = "Delete not null")
    private  boolean deleted;
}
