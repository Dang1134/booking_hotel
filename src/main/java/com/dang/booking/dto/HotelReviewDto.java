package com.dang.booking.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class HotelReviewDto {
    private int id;
    private String comment;
    private LocalDateTime reviewDate;
    private String nameUser;
}
