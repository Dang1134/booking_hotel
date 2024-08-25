package com.dang.booking.mapper;

import com.dang.booking.dto.HotelAddressDto;

import com.dang.booking.model.HotelAddress;
import org.springframework.stereotype.Service;

@Service
public class HotelAddressMapper {
    public HotelAddressDto hotelAddressConvertTohotelAddressDto(HotelAddress hotelAddress){
        HotelAddressDto hotelAddressDto = new HotelAddressDto();

        hotelAddressDto.setStreetNumber(hotelAddress.getStreetNumber());
        hotelAddressDto.setStreetName(hotelAddress.getStreetName());
        hotelAddressDto.setDistrict(hotelAddress.getDistrict());
        hotelAddressDto.setProvince(hotelAddress.getProvince());
        hotelAddressDto.setCity(hotelAddress.getCity());
        hotelAddressDto.setCountry(hotelAddress.getCountry());
        return hotelAddressDto;
    }
}
