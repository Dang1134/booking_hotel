package com.dang.booking;

import com.dang.booking.exception.InsertHotelException;
import com.dang.booking.exception.InsertRoomTypeException;
import com.dang.booking.model.Hotel;
import com.dang.booking.model.ROOM_STATUS;
import com.dang.booking.model.Room;
import com.dang.booking.model.RoomType;
import com.dang.booking.payload.request.InsertRoomRequest;
import com.dang.booking.repository.HotelRepository;
import com.dang.booking.repository.RoomRepository;
import com.dang.booking.repository.RoomTypeRepository;
import com.dang.booking.service.RoomService;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)

public class InsertRoomServiceImpTest {
    @Mock
    private RoomRepository roomRepository ;
    @Mock
    private RoomTypeRepository roomTypeRepository ;
    @Mock
    private HotelRepository hotelRepository ;
    @InjectMocks
    private RoomService roomService ;
    @Mock
    private HttpServletRequest httpServletRequest ;
    private Room room ;
    private RoomType roomType ;
    private Hotel hotel ;
    private InsertRoomRequest insertRoomRequest ;
    @BeforeEach
    public void setUp(){
        room = new Room();
        room.setId(1);
        room.setRoomNumber(123);

        roomType = new RoomType();
        roomType.setId(1);


        hotel = new Hotel();
        hotel.setId(1);

        insertRoomRequest = new InsertRoomRequest();
        insertRoomRequest.setNumberRoom(123);
        insertRoomRequest.setDescription("hee");
        insertRoomRequest.setPrice(1232.00);
        insertRoomRequest.setStatus(ROOM_STATUS.BOOKED);
        insertRoomRequest.setIdRoomType(1);
        insertRoomRequest.setIdHotel(1);
    }
    @Test
    public void testInsertRoom_Success(){
        when(roomTypeRepository.findById(anyInt())).thenReturn(Optional.of(roomType));
        when(hotelRepository.findById(anyInt())).thenReturn(Optional.of(hotel));
        when(roomRepository.save(any(Room.class))).thenReturn(new Room());

        Room insertRoom = roomService.insertRoom(httpServletRequest, insertRoomRequest);
        assertNotNull(insertRoom);

        verify(roomTypeRepository).findById(insertRoomRequest.getIdRoomType());
        verify(hotelRepository).findById(insertRoomRequest.getIdHotel());
        verify(roomRepository).save(any(Room.class));
    }
    @Test
    public void testInsertRoom_RoomTypeNotFound(){

        when(roomTypeRepository.findById(anyInt())).thenReturn(Optional.empty());

        InsertRoomTypeException exception = assertThrows(InsertRoomTypeException.class, () ->
                roomService.insertRoom(httpServletRequest , insertRoomRequest ));
        assertEquals("Không tìm thấy loại phòng với id: 1" , exception.getMessage());


        verify(roomTypeRepository).findById(insertRoomRequest.getIdRoomType());

        verify(roomRepository, never()).save(any(Room.class));
    }
    @Test
    public void testInsertRoom_HotelNotFound(){
        when(hotelRepository.findById(anyInt())).thenReturn(Optional.empty());
        when(roomTypeRepository.findById(anyInt())).thenReturn(Optional.of(roomType));

        InsertHotelException exception = assertThrows(InsertHotelException.class, () ->
                roomService.insertRoom(httpServletRequest, insertRoomRequest));
        assertEquals("Hotel không tìm thấy với id: 1" , exception.getMessage());

        verify(roomTypeRepository).findById(insertRoomRequest.getIdRoomType());
        verify(hotelRepository).findById(insertRoomRequest.getIdHotel());
        verify(roomRepository, never()).save(any(Room.class));
    }

}
