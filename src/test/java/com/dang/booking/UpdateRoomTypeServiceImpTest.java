package com.dang.booking;

import com.dang.booking.exception.DeleteException;
import com.dang.booking.exception.UpdateRoomTypeException;
import com.dang.booking.model.RoomType;
import com.dang.booking.payload.request.UpdateRoomTypeRequest;
import com.dang.booking.repository.RoomTypeRepository;
import com.dang.booking.service.RoomTypeService;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)

public class UpdateRoomTypeServiceImpTest {
    @Mock
    private RoomTypeRepository roomTypeRepository;
    @InjectMocks
    private RoomTypeService roomTypeService;
    @Mock
    private HttpServletRequest httpServletRequest;
    @Mock
    private UpdateRoomTypeRequest updateRoomTypeRequest;
    @Mock
    private RoomType roomType ;

    @BeforeEach
    public void setUp() {
        roomType = new RoomType();
        roomType.setId(1);
        roomType.setName("test");

        updateRoomTypeRequest = new UpdateRoomTypeRequest();
        updateRoomTypeRequest.setId(1);
        updateRoomTypeRequest.setName("test2");
    }
    @Test
    public void testUpdateRoomType_Success() {
        when(roomTypeRepository.findById(anyInt())).thenReturn(Optional.of(roomType));
        when(roomTypeRepository.save(any(RoomType.class))).thenReturn(roomType);

        RoomType updateRoomType = roomTypeService.updateRoomType(httpServletRequest , updateRoomTypeRequest , 1);
        assertEquals(updateRoomTypeRequest.getName(), updateRoomType.getName());

        verify(roomTypeRepository).findById(anyInt());
        verify(roomTypeRepository).save(any(RoomType.class));
    }
    @Test
    public void testUpdateRoomType_Failure() {
        when(roomTypeRepository.findById(anyInt())).thenReturn(Optional.empty());
        UpdateRoomTypeException exception = assertThrows(UpdateRoomTypeException.class, () ->
                roomTypeService.updateRoomType(httpServletRequest , updateRoomTypeRequest , 1));
        assertEquals(exception.getMessage(), "Không tìm thấy loại phòng với id: 1" , exception.getMessage());
        verify(roomTypeRepository).findById(anyInt());
        verify(roomTypeRepository , never()).save(any(RoomType.class));

    }




}
