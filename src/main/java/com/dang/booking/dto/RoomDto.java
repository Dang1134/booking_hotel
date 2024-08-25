package com.dang.booking.dto;

import com.dang.booking.model.ROOM_STATUS;
import lombok.Data;

import java.util.List;

@Data
public class RoomDto {

    private int id;
    private String nameHotel;
    private int roomNumber;
    private List<RoomTypeDto> roomType;
    private double price;
    private List<AmenitiesDto> amenities ;
    private List<String> image ;
    private ROOM_STATUS status;



}
