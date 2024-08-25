package com.dang.booking.service;

import com.dang.booking.dto.UserDto;
import com.dang.booking.exception.NotFoundException;
import com.dang.booking.exception.UpdateException;
import com.dang.booking.exception.UserAlreadyExistsException;
import com.dang.booking.mapper.UserMapper;
import com.dang.booking.model.Role;
import com.dang.booking.model.User;
import com.dang.booking.payload.request.CreateUserRequest;
import com.dang.booking.payload.request.UpdateUserRequest;
import com.dang.booking.repository.UserRepository;
import com.dang.booking.service.imp.FileServiceImp;
import com.dang.booking.service.imp.UserServiceImp;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@Service
public class UserService implements UserServiceImp {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private FileServiceImp fileServiceImp;

    @Override
    public UserDto getUserDetail(String email) {
        UserDto userDto = new UserDto();
        try {
            Optional<User> user = userRepository.findByEmail(email);

            if (user.isPresent()) {
                return userDto = userMapper.userConvertToUserDto(user.get());
            } else {
                throw new NotFoundException("User not found with email " + email);
            }
        } catch (Exception ex) {
            throw new RuntimeException("Not get User Detail");
        }
    }


    @Override
    public UserDto createUser(@Valid CreateUserRequest createUserRequest) {

        Optional<User> user = userRepository.findByEmail(createUserRequest.getEmail());
        if (user.isPresent())
            throw new UserAlreadyExistsException("User already exists with email " + createUserRequest.getEmail());
        try{
            User newUser = new User();
            newUser.setFullName(createUserRequest.getName());
            newUser.setEmail(createUserRequest.getEmail());

            String password = passwordEncoder.encode(createUserRequest.getPassword());
            newUser.setPassword(password);

            Role role = new Role();
            role.setId(createUserRequest.getIdRole());
            newUser.setRole(role);

             newUser = userRepository.save(newUser);
            return userMapper.userConvertToUserDto(newUser);
        }catch (Exception ex){
            throw new RuntimeException("Error create user " + ex.getMessage());
        }
    }

    @Override
    public UserDto updateUser(UpdateUserRequest updateUserRequest, Long currentUserId) {
       boolean isSuccessSaveFile =  fileServiceImp.saveFile(updateUserRequest.getFileAvatar());
       User user = userRepository.findById(currentUserId).orElseThrow(()-> new NotFoundException("Not found user with Id: " + currentUserId));
       try {
           if(isSuccessSaveFile){
               user.setFullName(updateUserRequest.getFullName());
               user.setAddress(updateUserRequest.getAddress());
               user.setPhone(updateUserRequest.getPhone());
               user.setSex(updateUserRequest.getSex());
               user.setAvatar(updateUserRequest.getFileAvatar().getOriginalFilename());
           }
           User newUser = userRepository.save(user);
           return userMapper.userConvertToUserDto(newUser);
       }catch (Exception ex){
            throw new UpdateException("Error update User " + ex.getMessage());
       }
    }
}
