package com.dang.booking.payload.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data

public class InsertRoomTypeRequest {
    @NotNull(message = "Name TypeRoom not null")
    @NotBlank(message = "Name TypeRoom not blank")
    private String name ;
}
