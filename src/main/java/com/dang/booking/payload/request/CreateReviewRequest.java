package com.dang.booking.payload.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateReviewRequest {

    private int hotelId;

    @NotBlank(message = "Review not null or empty or blank")
    private String comment;

}
