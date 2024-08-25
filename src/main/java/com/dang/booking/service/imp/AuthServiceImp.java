package com.dang.booking.service.imp;

import com.dang.booking.model.User;
import com.dang.booking.payload.request.LogoutRequest;

public interface AuthServiceImp {
    User getUserByEmail(String email);
    void logout(LogoutRequest logoutRequest);
}
