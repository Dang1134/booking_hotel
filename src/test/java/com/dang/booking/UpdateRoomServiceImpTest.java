package com.dang.booking;

import com.dang.booking.exception.UpdateRoomException;
import com.dang.booking.model.Hotel;
import com.dang.booking.model.Room;
import com.dang.booking.model.RoomType;
import com.dang.booking.payload.request.UpdateRoomRequest;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UpdateRoomServiceImpTest {
    @Mock
    private RoomRepository roomRepository;
    @Mock
    private RoomTypeRepository roomTypeRepository;
    @Mock
    private HotelRepository hotelRepository;
    @InjectMocks
    private RoomService roomService ;
    @Mock
    private HttpServletRequest httpServletRequest ;

    private Room room ;
    private RoomType roomType ;
    private Hotel hotel ;
    private UpdateRoomRequest updateRoomRequest ;

    @BeforeEach
    public void setUp(){
        room = new Room();
        room.setId(1);
        room.setRoomNumber(123);

        roomType = new RoomType();
        roomType.setId(1);

        hotel = new Hotel();
        hotel.setId(1);

        updateRoomRequest = new UpdateRoomRequest();
        updateRoomRequest.setNumberRoom(123);
        updateRoomRequest.setDescription("da");
        updateRoomRequest.setPrice(150.0);
        updateRoomRequest.setStatus(com.dang.booking.model.ROOM_STATUS.BOOKED);
        updateRoomRequest.setIdRoomType(1);
        updateRoomRequest.setIdHotel(1);
    }

    @Test
    public void testUpdateRoom_Success() {
        when(roomRepository.findById(anyInt())).thenReturn(Optional.of(room));
        when(roomTypeRepository.findById(anyInt())).thenReturn(Optional.of(roomType));
        when(hotelRepository.findById(anyInt())).thenReturn(Optional.of(hotel));
        when(roomRepository.save(any(Room.class))).thenReturn(room);

        Room updatedRoom = roomService.updateRoom(httpServletRequest, updateRoomRequest,1);

        assertEquals(updateRoomRequest.getNumberRoom(), updatedRoom.getRoomNumber());
        assertEquals(updateRoomRequest.getDescription(), updatedRoom.getDescription());
        assertEquals(updateRoomRequest.getPrice(), updatedRoom.getPrice());
        assertEquals(updateRoomRequest.getStatus(), updatedRoom.getStatus());


        verify(roomRepository).findById(anyInt());
        verify(roomTypeRepository).findById(anyInt());
        verify(hotelRepository).findById(anyInt());
        verify(roomRepository).save(any(Room.class));
    }

    @Test
    public void testUpdateRoom_RoomNotFound() {
        when(roomRepository.findById(anyInt())).thenReturn(Optional.empty());

        UpdateRoomException exception = assertThrows(UpdateRoomException.class, () ->
                roomService.updateRoom(httpServletRequest, updateRoomRequest,1));

        assertEquals("Không tìm thấy phòng với id: 1", exception.getMessage());
        verify(roomRepository).findById(anyInt());
        verify(roomRepository, never()).save(any(Room.class));
    }

    @Test
    public void testUpdateRoom_RoomTypeNotFound() {
        when(roomRepository.findById(anyInt())).thenReturn(Optional.of(room));
        when(roomTypeRepository.findById(anyInt())).thenReturn(Optional.empty());

        UpdateRoomException exception = assertThrows(UpdateRoomException.class, () ->
                roomService.updateRoom(httpServletRequest, updateRoomRequest,1));

        assertEquals("Không tìm thấy loại phòng với id: 1" , exception.getMessage());
        verify(roomRepository).findById(anyInt());
        verify(roomTypeRepository).findById(anyInt());
        verify(roomRepository, never()).save(any(Room.class));
    }

    @Test
    public void testUpdateRoom_HotelNotFound() {
        when(roomRepository.findById(anyInt())).thenReturn(Optional.of(room));
        when(roomTypeRepository.findById(anyInt())).thenReturn(Optional.of(roomType));
        when(hotelRepository.findById(anyInt())).thenReturn(Optional.empty());

        UpdateRoomException exception = assertThrows(UpdateRoomException.class, () ->
                roomService.updateRoom(httpServletRequest, updateRoomRequest, 1));

        assertEquals("Hotel không tìm thấy với id: 1", exception.getMessage());
        verify(roomRepository).findById(anyInt());
        verify(roomTypeRepository).findById(anyInt());
        verify(hotelRepository).findById(anyInt());
        verify(roomRepository, never()).save(any(Room.class));
    }

}
