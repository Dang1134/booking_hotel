package com.dang.booking;

import com.dang.booking.exception.DeleteException;
import com.dang.booking.model.Amenities;
import com.dang.booking.model.Room;
import com.dang.booking.model.RoomAmenities;
import com.dang.booking.model.RoomImage;
import com.dang.booking.model.key.RoomAmenitiesId;
import com.dang.booking.payload.request.InsertRoomRequest;
import com.dang.booking.repository.RoomAmenitiesRepository;
import com.dang.booking.repository.RoomImageRepository;
import com.dang.booking.repository.RoomRepository;
import com.dang.booking.service.RoomService;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)

public class DeleteRoomServiceImpTest {
    @Mock
    private RoomRepository roomRepository ;
    @Mock
    private RoomAmenitiesRepository roomAmenitiesRepository ;
    @Mock
    private RoomImageRepository roomImageRepository ;
    @InjectMocks
    private RoomService roomService ;
    @Mock
    private HttpServletRequest httpServletRequest;
    private Room room ;

    @BeforeEach
    public void setUp(){
        room = new Room();
        room.setId(1);
        room.setRoomNumber(123);
        room.setDescription("haaa");
        room.setPrice(123.000);
        room.setStatus(com.dang.booking.model.ROOM_STATUS.BOOKED);
    }
    @Test
    public void testDeleteRoom_Success(){
        when(roomRepository.existsById(1)).thenReturn(true);
        when(roomAmenitiesRepository.findByRoomId(room.getId())).thenReturn(new ArrayList<>());
       // when(roomAmenitiesRepository.findByRoomId())
        when(roomImageRepository.findByRoomId(room.getId())).thenReturn(new ArrayList<>());
        roomService.deleteRoom(httpServletRequest,1);
        verify(roomRepository).existsById(1);
        verify(roomRepository).deleteById(1);
    }
    @Test
    public void testDeleteRoom_RoomNotFound(){
        when(roomRepository.existsById(1)).thenReturn(false);

        DeleteException exception = assertThrows(DeleteException.class, () ->
                roomService.deleteRoom(httpServletRequest,1));

        assertEquals("Không tìm thấy phòng với id: 1" , exception.getMessage());
        verify(roomRepository).existsById(1);
        verify(roomRepository, never()).deleteById(1);
    }
    @Test
    public void testDeleteRoom_DeleteImageAndAmenities(){
        when(roomRepository.existsById(1)).thenReturn(true);

        List<RoomAmenities> roomAmenities = Arrays.asList(new RoomAmenities(new RoomAmenitiesId(1, 1), room, new Amenities()));
        List<RoomImage> roomImageList = Arrays.asList(new RoomImage(1, room, "tille 1" , "descrip 1 " , "path 1" , LocalDateTime.now()));
        when(roomImageRepository.findByRoomId(1)).thenReturn(roomImageList);
        when(roomAmenitiesRepository.findByRoomId(1)).thenReturn(roomAmenities);

        roomService.deleteRoom(httpServletRequest , 1);

        for (RoomAmenities amenities : roomAmenities){
            verify(roomAmenitiesRepository).delete(amenities);
        }
        for (RoomImage roomImage1 : roomImageList){
            verify(roomImageRepository).delete(roomImage1);
        }
        verify(roomRepository).deleteById(1);

    }



}
