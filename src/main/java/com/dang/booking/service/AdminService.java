package com.dang.booking.service;

import com.dang.booking.dto.HotelDto;
import com.dang.booking.dto.UserDto;
import com.dang.booking.exception.NotFoundException;
import com.dang.booking.exception.UpdateException;
import com.dang.booking.mapper.HotelMapper;
import com.dang.booking.mapper.UserMapper;
import com.dang.booking.model.Hotel;
import com.dang.booking.model.User;
import com.dang.booking.payload.request.UpdateHotelByAdminRequest;
import com.dang.booking.payload.request.UpdateUserByAdminRequest;
import com.dang.booking.repository.HotelRepository;
import com.dang.booking.repository.UserRepository;
import com.dang.booking.service.imp.AdminServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminService implements AdminServiceImp {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private HotelMapper hotelMapper;

    @Override
    public UserDto getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("Not found user with id " + id));
        return userMapper.userConvertToUserDto(user);
    }

    @Override
    public UserDto getUserByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        User userFound = user.get();
        return userMapper.userConvertToUserDto(userFound);
    }

    @Override
    public UserDto updateUserByAdmin(UpdateUserByAdminRequest updateUserByAdminRequest) {
        User user = userRepository.findById(updateUserByAdminRequest.getUserId()).orElseThrow(() -> new NotFoundException("Not found user with id " + updateUserByAdminRequest.getUserId()));
        try {
            user.setDeleted(updateUserByAdminRequest.isDeleted());
            return userMapper.userConvertToUserDto(userRepository.save(user));
        } catch (Exception ex) {
            throw new UpdateException("Error update User By Admin: " + ex.getMessage());
        }
    }

    @Override
    public HotelDto updateHotelByAdmin(UpdateHotelByAdminRequest request) {
        Hotel hotel = hotelRepository.findById(request.getHotelId()).orElseThrow(() -> new NotFoundException("Not found hotel with Id: " + request.getHotelId()));
        try {
            hotel.setDeleted(request.isDeleted());
            return hotelMapper.hotelConvertToHotelDto(hotelRepository.save(hotel));
        } catch (Exception ex) {
            throw new UpdateException("Error update Hotel By Admin: " + ex.getMessage());
        }
    };

    @Override
    public String banUserByAdmin(long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()){
            User userBanned = user.get();
            userBanned.setDeleted(true);
            userRepository.save(userBanned);
            return "UserID " + id + " is banned!";
        } else {
            return "UserID " + id + " is not found.";
        }
    }

    @Override
    public String unbanUserByAdmin(long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()){
            User unbanUser = user.get();
            if (unbanUser.isDeleted()){
                unbanUser.setDeleted(false);
                userRepository.save(unbanUser);
                return "UserID " + id + " is available now.";
            } else {
                return "UserID " + id + " is not banned.";
            }
        } else {
            return "UserID " + id + " is not found.";
        }
    }
}
