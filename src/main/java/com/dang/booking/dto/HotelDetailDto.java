package com.dang.booking.dto;

import lombok.Data;

import java.util.List;

@Data
public class HotelDetailDto extends HotelListDto{
    private List<RoomDto> rooms;
    private List<HotelAmenitiesDto> amenitiesOfHotel;
}
