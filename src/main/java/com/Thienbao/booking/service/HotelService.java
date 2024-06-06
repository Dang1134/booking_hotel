package com.Thienbao.booking.service;

import com.Thienbao.booking.dto.HotelDto;
import com.Thienbao.booking.dto.HotelListDto;
import com.Thienbao.booking.mapper.HotelMapper;
import com.Thienbao.booking.model.Hotel;
import com.Thienbao.booking.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HotelService {

    @Autowired
    HotelRepository hotelRepository;

    @Autowired
    HotelMapper hotelMapper;

    public List<HotelListDto> gethotels(){
        List<Hotel> hotels = hotelRepository.findAll();
        List<HotelListDto> hotelListDto = new ArrayList<>();
        for(Hotel hotel: hotels){
            hotelListDto.add(hotelMapper.hotelConvertToHotelListDto(hotel));
        }
        return hotelListDto;
    }


}
