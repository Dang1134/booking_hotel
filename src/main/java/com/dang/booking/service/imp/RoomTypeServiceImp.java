package com.dang.booking.service.imp;

import com.dang.booking.dto.RoomTypeDto;
import com.dang.booking.model.RoomType;
import com.dang.booking.payload.request.InsertRoomTypeRequest;
import com.dang.booking.payload.request.UpdateRoomTypeRequest;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface RoomTypeServiceImp {
    RoomType insertRoomType (HttpServletRequest request, InsertRoomTypeRequest typeRequest);
    List<RoomTypeDto> getAllRoomType(HttpServletRequest request);
    void deleteRoomType( HttpServletRequest request ,int id);
    RoomType updateRoomType (HttpServletRequest request , UpdateRoomTypeRequest updateRoomTypeRequest , int id);
    List<RoomTypeDto> getIdRoomType(HttpServletRequest request, int id);
}
