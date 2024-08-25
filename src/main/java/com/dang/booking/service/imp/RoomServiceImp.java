package com.dang.booking.service.imp;

import com.dang.booking.dto.RoomDto;
import com.dang.booking.model.Room;
import com.dang.booking.payload.request.InsertRoomRequest;
import com.dang.booking.payload.request.UpdateRoomRequest;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface RoomServiceImp {
    List<RoomDto> getAllRoom();
    Room insertRoom(HttpServletRequest request, InsertRoomRequest insertRoomRequest);

    Room updateRoom (HttpServletRequest request , UpdateRoomRequest updateRoomRequest , int id );
    void deleteRoom (HttpServletRequest request , int id );
    RoomDto getRoomById(HttpServletRequest request , int id);

}
