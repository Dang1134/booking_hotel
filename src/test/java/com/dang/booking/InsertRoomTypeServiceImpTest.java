package com.dang.booking;

import com.dang.booking.model.RoomType;
import com.dang.booking.payload.request.InsertRoomTypeRequest;
import com.dang.booking.repository.RoomTypeRepository;
import com.dang.booking.service.RoomService;
import com.dang.booking.service.RoomTypeService;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)

public class InsertRoomTypeServiceImpTest {
    @Mock
    private RoomTypeRepository roomTypeRepository;
    @InjectMocks
    private RoomTypeService roomTypeService;
    @Mock
    private RoomType roomType;
    private HttpServletRequest httpServletRequest;
    private InsertRoomTypeRequest insertRoomTypeRequest;
    @BeforeEach
    public void setUp() {
        roomType = new RoomType();
        roomType.setId(1);
        roomType.setName("test");

        insertRoomTypeRequest = new InsertRoomTypeRequest();
        insertRoomTypeRequest.setName("Standard Room");
    }
    @Test
    public void testInsertRoomType_Success() {
        when(roomTypeRepository.save(any(RoomType.class))).thenReturn(roomType);
        RoomType insertRoomType = roomTypeService.insertRoomType(httpServletRequest, insertRoomTypeRequest);
        assertNotNull(insertRoomType);
        verify(roomTypeRepository).save(any(RoomType.class));
    }
    @Test
    public void testInsertRoomType_Failure() {
            when(roomTypeRepository.save(any(RoomType.class))).thenThrow(new RuntimeException("Save failed"));

            assertThrows(RuntimeException.class, () -> {
                roomTypeService.insertRoomType(httpServletRequest, insertRoomTypeRequest);
            });

            verify(roomTypeRepository).save(any(RoomType.class));
        }


}
