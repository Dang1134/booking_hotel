package com.dang.booking.payload.request;

import com.dang.booking.model.ROOM_STATUS;
import lombok.Data;

@Data

public class InsertRoomRequest {
    private int numberRoom;
    private String description;
    private int idRoomType;
    private double price ;
    private int idHotel;
    private ROOM_STATUS status;

}
