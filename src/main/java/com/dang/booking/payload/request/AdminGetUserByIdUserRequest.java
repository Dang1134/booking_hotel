package com.dang.booking.payload.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class AdminGetUserByIdUserRequest {
    @NotNull(message = "ID not null")
    @Positive(message = "ID is a positive number")
    private Long id;
}
