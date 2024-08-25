package com.dang.booking.payload.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
public class UpdateHotelRequest {
    //Hotel
    private String name;
    private String description;
    private String phone;
    private LocalTime checkInTime;
    private LocalTime checkOutTime;
    private LocalTime closeTime;
    private LocalTime openTime;
    private BigDecimal rating;
    private boolean isDeleted;
    private MultipartFile avatar ;
    private Long userID;

    //Hotel_Adress
    private int hotelID;
    private String city;
    private String district;
    private String country;
    private String province;
    private String streetName;
    private int streetNumber;

    //Hotel_Image
    private String imageDesc;
    private String imagePath;
    private String imageTitle;
    private LocalDateTime uploadDate;
}
