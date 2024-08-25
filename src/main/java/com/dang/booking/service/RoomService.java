package com.dang.booking.service;

import com.dang.booking.dto.AmenitiesDto;
import com.dang.booking.dto.RoomDto;
import com.dang.booking.dto.RoomTypeDto;
import com.dang.booking.exception.*;
import com.dang.booking.model.*;
import com.dang.booking.exception.DeleteException;
import com.dang.booking.exception.GetRoomByIdException;
import com.dang.booking.exception.UpdateRoomException;
import com.dang.booking.model.*;
import com.dang.booking.payload.request.InsertRoomRequest;
import com.dang.booking.payload.request.UpdateRoomRequest;
import com.dang.booking.repository.*;
import com.dang.booking.repository.*;
import com.dang.booking.service.imp.RoomServiceImp;
import com.dang.booking.exception.InsertRoomTypeException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service

public class RoomService implements RoomServiceImp {
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private HotelRepository hotelRepository;
    @Autowired
    private RoomTypeRepository roomTypeRepository;
    @Autowired
    private RoomAmenitiesRepository roomAmenitiesRepository;
    @Autowired
    private RoomImageRepository roomImageRepository;

    @Override
    public List<RoomDto> getAllRoom() {

        List<Room> rooms = roomRepository.findAll();
        List<RoomDto> roomDTOS = new ArrayList<>();
        rooms.forEach(item ->{
            RoomDto roomDto = new RoomDto();

            roomDto.setNameHotel((item.getHotel().getName()));

            List<RoomTypeDto> roomTypeDtos = new ArrayList<>();
            for (Room room : item.getRoomType().getRoomList()){
                RoomTypeDto roomTypeDto = new RoomTypeDto();
                roomTypeDto.setId(room.getRoomType().getId());
                roomTypeDto.setName(room.getRoomType().getName());
                roomTypeDtos.add(roomTypeDto);
            };
            roomDto.setRoomType(roomTypeDtos);

            roomDto.setRoomNumber(item.getRoomNumber());
            roomDto.setPrice(item.getPrice());

            List<AmenitiesDto> amenitiesDTOList = new ArrayList<>();
            for (RoomAmenities roomAmenity : item.getRoomAmenitiesList()) {
                AmenitiesDto amenitiesDTO = new AmenitiesDto();
                amenitiesDTO.setId(roomAmenity.getAmenity().getId());
                amenitiesDTO.setName(roomAmenity.getAmenity().getName());
                amenitiesDTO.setIcon(roomAmenity.getAmenity().getIcon());
                amenitiesDTOList.add(amenitiesDTO);
            }
            roomDto.setAmenities(amenitiesDTOList);

            List<String> images = new ArrayList<>();
            item.getRoomImageList().forEach(itemImage ->{
                images.add(itemImage.getImagePath());
            });
            roomDto.setImage(images);
            roomDto.setStatus(item.getStatus());

            roomDTOS.add(roomDto);

        });
        return roomDTOS ;
    }

    @Override
    public Room insertRoom(HttpServletRequest request, InsertRoomRequest insertRoomRequest) {
        Room room = new Room();
        room.setRoomNumber(insertRoomRequest.getNumberRoom());
        room.setDescription(insertRoomRequest.getDescription());
        room.setPrice(insertRoomRequest.getPrice());
        room.setStatus(insertRoomRequest.getStatus());

        RoomType roomType = roomTypeRepository.findById(insertRoomRequest.getIdRoomType())
                .orElseThrow(() -> new InsertRoomTypeException("Không tìm thấy loại phòng với id: "+ insertRoomRequest.getIdRoomType()));
        room.setRoomType(roomType);
        Hotel hotel = hotelRepository.findById(insertRoomRequest.getIdHotel())
                .orElseThrow(()->new InsertHotelException("Hotel không tìm thấy với id: " + insertRoomRequest.getIdHotel()));
        room.setHotel(hotel);
        return roomRepository.save(room);
    }

    @Override
    public Room updateRoom(HttpServletRequest request, UpdateRoomRequest updateRoomRequest, int id) {
        Room room = roomRepository.findById(id).orElseThrow(()
                        -> new UpdateRoomException("Không tìm thấy phòng với id: " + id));
        RoomType roomType = roomTypeRepository.findById(updateRoomRequest.getIdRoomType())
                .orElseThrow(() -> new UpdateRoomException("Không tìm thấy loại phòng với id: " + updateRoomRequest.getIdRoomType()));
        room.setRoomType(roomType);
        Hotel hotel = hotelRepository.findById(updateRoomRequest.getIdHotel())
                .orElseThrow(()->new UpdateRoomException("Hotel không tìm thấy với id: " + updateRoomRequest.getIdHotel()));
        room.setHotel(hotel);
        room.setRoomNumber(updateRoomRequest.getNumberRoom());
        room.setDescription(updateRoomRequest.getDescription());
        room.setPrice(updateRoomRequest.getPrice());
        room.setStatus(updateRoomRequest.getStatus());
        return roomRepository.save(room);
    }

    @Override
    @Transactional
    public void deleteRoom(HttpServletRequest request, int id) {
        if (!roomRepository.existsById(id)){
            throw new DeleteException("Không tìm thấy phòng với id: " +id);
        }
        List<RoomAmenities> roomAmenities = roomAmenitiesRepository.findByRoomId(id);

        for (RoomAmenities roomAmenities1 :roomAmenities){
            roomAmenitiesRepository.delete(roomAmenities1);
        }
        List<RoomImage> roomImages = roomImageRepository.findByRoomId(id);
        for (RoomImage image : roomImages){
            roomImageRepository.delete(image);
        }
        roomRepository.deleteById(id);
    }

    @Override
    public RoomDto getRoomById(HttpServletRequest request, int id) {
        Room room = roomRepository.findById(id)
                .orElseThrow(()-> new GetRoomByIdException("Không tìm thấy phòng với id: " + id));

            RoomDto roomDto = new RoomDto();

        roomDto.setNameHotel(room.getHotel().getName());

        RoomTypeDto roomTypeDto = new RoomTypeDto();
        roomTypeDto.setId(room.getRoomType().getId());
        roomTypeDto.setName(room.getRoomType().getName());
        roomDto.setRoomType(Collections.singletonList(roomTypeDto));

        roomDto.setRoomNumber(room.getRoomNumber());
        roomDto.setPrice(room.getPrice());

        List<AmenitiesDto> amenitiesDTOList = new ArrayList<>();
        for (RoomAmenities roomAmenity : room.getRoomAmenitiesList()) {
            AmenitiesDto amenitiesDTO = new AmenitiesDto();
            amenitiesDTO.setId(roomAmenity.getAmenity().getId());
            amenitiesDTO.setName(roomAmenity.getAmenity().getName());
            amenitiesDTO.setIcon(roomAmenity.getAmenity().getIcon());
            amenitiesDTOList.add(amenitiesDTO);
        }
        roomDto.setAmenities(amenitiesDTOList);
        List<String> images = new ArrayList<>();
        room.getRoomImageList().forEach(itemImage -> {
            images.add(itemImage.getImagePath());
        });
        roomDto.setImage(images);
        roomDto.setStatus(room.getStatus());

        return roomDto;
    }


}
