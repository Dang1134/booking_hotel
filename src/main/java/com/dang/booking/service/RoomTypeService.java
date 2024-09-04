package com.dang.booking.service;

import com.dang.booking.dto.RoomTypeDto;
import com.dang.booking.exception.DeleteException;
import com.dang.booking.exception.GetRoomTypeByIdException;
import com.dang.booking.exception.InsertRoomTypeException;
import com.dang.booking.exception.UpdateRoomTypeException;
import com.dang.booking.model.Room;
import com.dang.booking.model.RoomType;
import com.dang.booking.payload.request.InsertRoomTypeRequest;
import com.dang.booking.payload.request.UpdateRoomTypeRequest;
import com.dang.booking.repository.RoomAmenitiesRepository;
import com.dang.booking.repository.RoomRepository;
import com.dang.booking.repository.RoomTypeRepository;
import com.dang.booking.service.imp.RoomTypeServiceImp;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
        List<RoomType> listRoomTypeEntity = roomTypeRepository.findAll();
        List<RoomTypeDto> listDto = new ArrayList<>();
        listRoomTypeEntity.forEach(item ->{
            RoomTypeDto roomTypeDto = new RoomTypeDto();
            roomTypeDto.setId(item.getId());
            roomTypeDto.setName(item.getName());

            listDto.add(roomTypeDto);
        });
        return listDto;
    }

    @Override
    @Transactional
    public void deleteRoomType(HttpServletRequest request, int id) {
        if (!roomTypeRepository.existsById(id)){
            throw new DeleteException("Không tìm thấy loại phòng với id: " +id);
        }
        List<Room> rooms = roomRepository.findByRoomTypeId(id);
        for (Room room : rooms){
            roomAmenitiesRepository.deleteByRoomId(room.getId());
            roomRepository.delete(room);
        }
        roomTypeRepository.deleteById(id);
    }

    @Override
    public RoomType updateRoomType(HttpServletRequest request, UpdateRoomTypeRequest updateRoomTypeRequest, int id) {
        RoomType roomType = roomTypeRepository.findById(id).orElseThrow(()-> new UpdateRoomTypeException("Không tìm thấy loại phòng" +id));
        roomType.setName(updateRoomTypeRequest.getName());
        return roomTypeRepository.save(roomType);
    }

    @Override
    @Transactional
    public List<RoomTypeDto> getIdRoomType(HttpServletRequest request, int id) {

        if (!roomTypeRepository.existsById(id)){
            throw new GetRoomTypeByIdException("Không tìm thấy loại phòng với id: " + id);
        }
        List<RoomType> roomTypes = roomTypeRepository.findRoomTypeById(id);
        List<RoomTypeDto> roomTypeDtos = new ArrayList<>();
        roomTypes.forEach(item ->{
            RoomTypeDto roomTypeDto = new RoomTypeDto();
            roomTypeDto.setId(item.getId());
            roomTypeDto.setName(item.getName());
            roomTypeDtos.add(roomTypeDto);
        });
        return roomTypeDtos;
    }
}
