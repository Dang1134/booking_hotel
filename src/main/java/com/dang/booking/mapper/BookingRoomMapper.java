package com.dang.booking.mapper;

import com.dang.booking.dto.BookingRoomDto;
import com.dang.booking.model.BookingRoom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingRoomMapper {

    @Autowired
    private RoomMapper roomMapper;
    public BookingRoomDto convertToBookingRoomDto(BookingRoom bookingRoom){

        BookingRoomDto bookingRoomDto = new BookingRoomDto();
        bookingRoomDto.setStatus(bookingRoom.getStatus());
        bookingRoomDto.setCheckinDate(bookingRoom.getCheckinDate());
        bookingRoomDto.setCheckoutDate(bookingRoom.getCheckoutDate());
        bookingRoomDto.setRoom(roomMapper.roomConvertToRoomDto(bookingRoom.getRoom()));
        return  bookingRoomDto;
    };
}
