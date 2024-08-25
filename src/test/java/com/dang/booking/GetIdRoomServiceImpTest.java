package com.dang.booking;

import com.dang.booking.dto.AmenitiesDto;
import com.dang.booking.dto.HotelDto;
import com.dang.booking.dto.RoomDto;
import com.dang.booking.dto.RoomTypeDto;
import com.dang.booking.exception.GetRoomByIdException;
import com.dang.booking.model.*;
import com.dang.booking.model.key.RoomAmenitiesId;
import com.dang.booking.repository.RoomRepository;
import com.dang.booking.service.RoomService;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GetIdRoomServiceImpTest {
    @Mock
    private RoomRepository roomRepository ;
    @Mock
    private HttpServletRequest httpServletRequest ;

    @InjectMocks
    private RoomService roomService ;
    @Mock
    private Room room ;
    private Amenities amenities ;
    private RoomType roomType ;
    private Hotel hotel ;
    private RoomImage roomImage ;

    @BeforeEach
    public void setUp(){
        room = new Room();
        room.setId(1);
        room.setPrice(123.000);
        room.setRoomNumber(123);
        room.setStatus(ROOM_STATUS.BOOKED);

        roomType = new RoomType();
        roomType.setId(1);
        roomType.setName("loai phong 1");
        room.setRoomType(roomType);

        hotel = new Hotel();
        hotel.setId(1);
        hotel.setName("Ngan sao");
        room.setHotel(hotel);


        amenities = new Amenities();
        amenities.setId(1);
        amenities.setName("Co giuong");
        amenities.setIcon("Dep.png");
        room.setRoomAmenitiesList(List.of(new RoomAmenities(new RoomAmenitiesId(1, 1), room, amenities)));

        roomImage = new RoomImage();
        roomImage.setId(1);
        roomImage.setImagePath("image/path.jpg");
        room.setRoomImageList(List.of(roomImage));

    }
    @Test
    public void testGetIdRoom_Success(){
        when(roomRepository.findById(1)).thenReturn(Optional.of(room));

        RoomDto roomDto = roomService.getRoomById(httpServletRequest , 1);
        assertNotNull(roomDto);
        assertEquals(123.000 , roomDto.getPrice());
        assertEquals(123 , roomDto.getRoomNumber());
        assertEquals(ROOM_STATUS.BOOKED , roomDto.getStatus());
        assertEquals("Ngan sao" , roomDto.getNameHotel());

        assertEquals(1 , roomDto.getRoomType().get(0).getId());
        assertEquals("loai phong 1" , roomDto.getRoomType().get(0).getName());

        assertEquals(1, roomDto.getAmenities().size());
        assertEquals("Co giuong" , roomDto.getAmenities().get(0).getName());

        assertEquals(1, roomDto.getImage().size());
        assertEquals("image/path.jpg" , roomDto.getImage().get(0));

        verify(roomRepository).findById(1);

    }
    @Test
    public void testGetIdRoom_RoomNotFound (){
        when(roomRepository.findById(1)).thenReturn(Optional.empty());
        GetRoomByIdException exception = assertThrows(GetRoomByIdException.class , () ->
                roomService.getRoomById(httpServletRequest,1));
        assertEquals("Không tìm thấy phòng với id: 1" , exception.getMessage());
       verify(roomRepository).findById(1);
       // verify(roomRepository, never()).findById(1);

    }






}
