package com.dang.booking.service.imp;

import com.dang.booking.dto.UserDto;
import com.dang.booking.payload.request.CreateUserRequest;
import com.dang.booking.payload.request.UpdateUserRequest;

public interface UserServiceImp {
    UserDto getUserDetail(String email);
    UserDto createUser(CreateUserRequest createUserRequest);

    UserDto updateUser(UpdateUserRequest updateUserRequest,Long currentUserId);
}
