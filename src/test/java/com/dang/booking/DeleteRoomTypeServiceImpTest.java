package com.dang.booking;

import com.dang.booking.exception.DeleteException;
import com.dang.booking.model.RoomType;
import com.dang.booking.repository.RoomRepository;
import com.dang.booking.repository.RoomTypeRepository;
import com.dang.booking.service.RoomTypeService;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DeleteRoomTypeServiceImpTest {
    @Mock
    private RoomTypeRepository roomTypeRepository ;
    @Mock
    private RoomRepository roomRepository ;
    @InjectMocks
    private RoomTypeService roomTypeService ;
    @Mock
    private HttpServletRequest httpServletRequest ;
    private RoomType roomType ;
    @BeforeEach
    public void setUp(){
        roomType = new RoomType() ;
        roomType.setId(1);
        roomType.setName("hehe");
    }
    @Test
    public void testDeleteRoomType_Success(){
        when(roomTypeRepository.existsById(1)).thenReturn(true);
        when(roomRepository.findByRoomTypeId(roomType.getId())).thenReturn(new ArrayList<>());
        roomTypeService.deleteRoomType(httpServletRequest, 1);
        verify(roomTypeRepository).existsById(1);
        verify(roomTypeRepository).deleteById(1);
    }
    @Test
    public void testDeleteRoomType_RoomTypeNotFound(){
        when(roomTypeRepository.existsById(1)).thenReturn(false);

        DeleteException exception = assertThrows(DeleteException.class, ()->
                roomTypeService.deleteRoomType(httpServletRequest, 1));
        assertEquals("Không tìm thấy loại phòng với id: 1", exception.getMessage());
        verify(roomTypeRepository).existsById(1);
        verify(roomTypeRepository, never()).deleteById(1);
    }

}
