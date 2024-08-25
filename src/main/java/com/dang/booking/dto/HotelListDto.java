package com.dang.booking.dto;

import lombok.Data;

import java.util.List;

@Data
public class HotelListDto extends HotelDto {
    private List<HotelImageDto> hotelImageDtoList;
    private HotelAddressDto hotelAddressDto;
    private List<HotelReviewDto> hotelReviewDtoList;
}
