package com.dang.booking.payload.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AdminGetUserByEmailRequest {
    @NotNull(message = "Email not null")
    @NotBlank(message = "Email not blank")
    @Email(message = "Invalid Email")
    private String email;
}
