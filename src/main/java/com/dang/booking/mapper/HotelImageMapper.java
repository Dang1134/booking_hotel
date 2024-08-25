package com.dang.booking.mapper;

import com.dang.booking.dto.HotelImageDto;

import com.dang.booking.model.HotelImage;
import org.springframework.stereotype.Service;

@Service
public class HotelImageMapper {

    public HotelImageDto HotelImageConvertToHotelImageDto (HotelImage hotelImage){
        HotelImageDto hotelImageDto = new HotelImageDto();
        hotelImageDto.setId(hotelImage.getId());
        hotelImageDto.setImageTitle(hotelImage.getImageTitle());
        hotelImageDto.setImageDescription(hotelImage.getImageDescription());
        hotelImageDto.setImagePath(hotelImage.getImagePath());
        hotelImageDto.setUploadDate(hotelImage.getUploadDate());
        return hotelImageDto;
    }
}
