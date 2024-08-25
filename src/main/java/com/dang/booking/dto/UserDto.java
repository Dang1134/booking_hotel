package com.dang.booking.dto;


import com.dang.booking.model.Role;
import com.dang.booking.model.USER_SEX;
import lombok.Data;

import java.util.List;

@Data
public class UserDto {

    private Long id;

    private String email;

    private String fullName;

    private String avatar;

    private String phone;

    private String address;

    private USER_SEX sex;

    private boolean isDeleted;

    private Role role;

    private List<HotelDto> hotels;

}
