package com.dang.booking.utils;

import com.dang.booking.security.DataSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class Helper {
    public Long getCurrentUserId(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        DataSecurity dataSecurity = (DataSecurity) authentication.getPrincipal();
        return  dataSecurity.getId();
    }



}
