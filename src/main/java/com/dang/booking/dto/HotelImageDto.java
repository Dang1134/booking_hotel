package com.dang.booking.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class HotelImageDto {
    private int id;
    private String imageTitle;
    private String imageDescription;
    private String imagePath;
    private LocalDateTime uploadDate;
}
