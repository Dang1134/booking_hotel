package com.dang.booking.mapper;

import com.dang.booking.dto.HotelDto;
import com.dang.booking.dto.UserDto;
import com.dang.booking.model.Hotel;
import com.dang.booking.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class UserMapper {

    @Autowired
    private HotelMapper hotelMapper;
    public UserDto userConvertToUserDto(User user){
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setEmail(user.getEmail());
        userDto.setFullName(user.getFullName());
        userDto.setAvatar(user.getAvatar());
        userDto.setPhone(user.getPhone());
        userDto.setSex(user.getSex());
        userDto.setAddress(user.getAddress());
        userDto.setRole(user.getRole());
        userDto.setDeleted(user.isDeleted());
        List<Hotel> hotels = user.getHotels();
        List<HotelDto> hotelDtoList = new ArrayList<>();
        if (hotels != null){
            for(Hotel hotel : hotels){
                hotelDtoList.add(hotelMapper.hotelConvertToHotelDto(hotel));
            }
        }
        userDto.setHotels(hotelDtoList);

        return userDto;
    }
}
