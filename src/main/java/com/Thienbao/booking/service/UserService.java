package com.Thienbao.booking.service;

import com.Thienbao.booking.dto.UserDto;
import com.Thienbao.booking.exception.NotFoundException;
import com.Thienbao.booking.exception.UserAlreadyExistsException;
import com.Thienbao.booking.mapper.UserMapper;
import com.Thienbao.booking.model.Role;
import com.Thienbao.booking.model.User;
import com.Thienbao.booking.payload.request.CreateUserRequest;
import com.Thienbao.booking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;


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

    public boolean createUser(CreateUserRequest createUserRequest) {

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

            userRepository.save(newUser);
            return true;
        }catch (Exception ex){
            throw new RuntimeException("Error create user " + ex.getMessage());
        }
    }
}
