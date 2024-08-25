package com.dang.booking.payload.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateReplyRequest {

    @NotBlank(message = "Reply not null or empty or blank")
    private String reply;

    private int reviewId;
}
