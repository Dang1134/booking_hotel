package com.dang.booking;

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

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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

}
