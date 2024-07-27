package com.Thienbao.booking.service;

import com.Thienbao.booking.dto.AmenitiesDto;
import com.Thienbao.booking.dto.RoomDto;
import com.Thienbao.booking.dto.RoomTypeDto;
import com.Thienbao.booking.exception.InsertRoomTypeException;
import com.Thienbao.booking.model.Hotel;
import com.Thienbao.booking.model.Room;
import com.Thienbao.booking.model.RoomAmenities;
import com.Thienbao.booking.model.RoomType;
import com.Thienbao.booking.payload.request.InsertRoomRequest;
import com.Thienbao.booking.repository.AmenitiesRepository;
import com.Thienbao.booking.repository.HotelRepository;
import com.Thienbao.booking.repository.RoomRepository;
import com.Thienbao.booking.repository.RoomTypeRepository;
import com.Thienbao.booking.service.imp.RoomServiceImp;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service

public class RoomService implements RoomServiceImp {
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private HotelRepository hotelRepository;
    @Autowired
    private RoomTypeRepository roomTypeRepository;

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
                .orElseThrow(() -> new InsertRoomTypeException("RoomType không tìm thấy với id: "+ insertRoomRequest.getIdRoomType()));
        room.setRoomType(roomType);
        Hotel hotel = hotelRepository.findById(insertRoomRequest.getIdHotel())
                .orElseThrow(()->new RuntimeException("Hotel không tìm thấy với id: " + insertRoomRequest.getIdHotel()));
        room.setHotel(hotel);
        return roomRepository.save(room);
    }
}
