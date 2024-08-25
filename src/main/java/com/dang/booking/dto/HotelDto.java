package com.dang.booking.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalTime;

@Data
public class HotelDto {
    private int id;
    private String name;
    private String avatar;
    private String description;
    private String phone;
    private LocalTime openTime;
    private LocalTime closeTime;
    private LocalTime checkinTime;
    private LocalTime checkoutTime;
    private BigDecimal rating;
    private boolean isDeleted;
}
