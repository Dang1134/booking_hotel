package com.dang.booking.dto;

import lombok.Data;

@Data
public class HotelAddressDto {
    private int streetNumber;
    private String streetName;
    private String district;
    private String city;
    private String province;
    private String country;

}
