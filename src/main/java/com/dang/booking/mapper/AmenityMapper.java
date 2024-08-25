package com.dang.booking.mapper;

import com.dang.booking.dto.AmenitiesDto;
import com.dang.booking.model.Amenities;
import org.springframework.stereotype.Service;

@Service
public class AmenityMapper {
    public AmenitiesDto convertToAmenitiesDto(Amenities amenity){
        AmenitiesDto amenitiesDto = new AmenitiesDto();
        amenitiesDto.setName(amenity.getName());
        amenitiesDto.setIcon(amenity.getIcon());
        return amenitiesDto;
        }
}
