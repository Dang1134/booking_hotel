package com.dang.booking.mapper;

import com.dang.booking.dto.RoomDto;
import com.dang.booking.model.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomMapper {
    @Autowired
    private RoomTypeMapper roomTypeMapper;
    public RoomDto roomConvertToRoomDto(Room room){
        RoomDto roomDto = new RoomDto();
        roomDto.setId(room.getId());
        roomDto.setRoomNumber(room.getRoomNumber());
        roomDto.setPrice(room.getPrice());
        roomDto.setStatus(room.getStatus());
       // roomDto.setRoomType(roomTypeMapper.roomTypeConvertToRoomTypeDto(room.getRoomType()));
        return roomDto;
    }
}
