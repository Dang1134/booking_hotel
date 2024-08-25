package com.dang.booking.payload.request;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class CreateUserRequest {

    @NotBlank(message = "Name not null or empty or blank")
    private String name;

    @NotBlank(message = "Email not null or empty or blank")
    @Email(message = "Please provide a valid email address")
    private String email;

    @NotBlank(message = "Password not null or empty or blank")
    private String password;

    private int idRole;

}
