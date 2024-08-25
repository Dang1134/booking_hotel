package com.dang.booking.service.imp;

import com.dang.booking.dto.HotelDetailDto;
import com.dang.booking.dto.HotelListDto;
import com.dang.booking.model.Hotel;
import com.dang.booking.payload.request.InsertHotelRequest;
import com.dang.booking.payload.request.UpdateHotelRequest;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface HotelServiceImp {
    List<HotelListDto> getHotels();
    HotelDetailDto getHotelDetail(int id);
    List<HotelDetailDto> getHotelsByUserId(Long userId);
    Hotel insertHotel(HttpServletRequest request, InsertHotelRequest hotelRequest);
    Hotel updateHotel(HttpServletRequest request, UpdateHotelRequest updateHotelRequest);

}
