package com.Thienbao.booking.service;

import com.Thienbao.booking.dto.RoomTypeDto;
import com.Thienbao.booking.exception.InsertRoomTypeException;
import com.Thienbao.booking.model.RoomType;
import com.Thienbao.booking.payload.request.InsertRoomTypeRequest;
import com.Thienbao.booking.payload.request.UpdateRoomTypeRequest;
import com.Thienbao.booking.repository.RoomAmenitiesRepository;
import com.Thienbao.booking.repository.RoomRepository;
import com.Thienbao.booking.repository.RoomTypeRepository;
import com.Thienbao.booking.service.imp.RoomTypeServiceImp;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoomTypeService implements RoomTypeServiceImp {

    @Autowired
    private RoomTypeRepository roomTypeRepository ;
    @Autowired
    private RoomRepository roomRepository ;

    @Autowired
    private RoomAmenitiesRepository roomAmenitiesRepository ;

    @Transactional
    @Override
    public RoomType insertRoomType(HttpServletRequest request, InsertRoomTypeRequest typeRequest) {

            if (typeRequest.getName() == null || typeRequest.getName().isEmpty()){
                throw new InsertRoomTypeException("Tên loại phòng không được để trống");
            }
            RoomType roomType = new RoomType();
            roomType.setName(typeRequest.getName());
            return roomTypeRepository.save(roomType);
    }

    @Override
    public List<RoomTypeDto> getAllRoomType(HttpServletRequest request) {
        return null;
    }

    @Override
    public void deleteRoomType(HttpServletRequest request, int id) {

    }

    @Override
    public RoomType updateRoomType(HttpServletRequest request, UpdateRoomTypeRequest updateRoomTypeRequest, int id) {
        return null;
    }
}
