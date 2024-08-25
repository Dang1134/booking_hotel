package com.dang.booking.payload.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class UpdateUserByAdminRequest {

    @NotNull(message = "Id User Not Null")
    private Long userId;

    @NotNull(message = "delete cannot be null")
    private boolean deleted;
}
