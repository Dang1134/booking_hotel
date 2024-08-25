package com.dang.booking.payload.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LogoutRequest {

    @NotNull(message = "token not null")
    @NotBlank(message = "token not blank")
    private String token;
};
