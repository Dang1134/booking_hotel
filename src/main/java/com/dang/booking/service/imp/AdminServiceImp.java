package com.dang.booking.service.imp;

import com.dang.booking.dto.HotelDto;
import com.dang.booking.dto.UserDto;
import com.dang.booking.payload.request.UpdateHotelByAdminRequest;
import com.dang.booking.payload.request.UpdateUserByAdminRequest;

public interface AdminServiceImp {
    UserDto getUserById(Long id);
    UserDto getUserByEmail(String email);
    UserDto updateUserByAdmin(UpdateUserByAdminRequest updateUserByAdminRequest);
    String banUserByAdmin(long id);
    String unbanUserByAdmin(long id);
    HotelDto updateHotelByAdmin(UpdateHotelByAdminRequest request);
}
