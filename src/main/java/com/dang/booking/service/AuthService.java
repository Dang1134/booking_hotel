package com.dang.booking.service;

import com.dang.booking.exception.NotFoundException;
import com.dang.booking.model.User;
import com.dang.booking.payload.request.LogoutRequest;
import com.dang.booking.repository.UserRepository;
import com.dang.booking.service.imp.AuthServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService implements AuthServiceImp {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User getUserByEmail(String email){
       Optional<User> user = userRepository.findByEmail(email);
       if (user.isPresent()){
           return user.get();
       }else {
           throw new NotFoundException("User not found with email");
       }
    }

    @Override
    public void logout(LogoutRequest logoutRequest){
        try{

        }catch(RuntimeException e){
            System.out.println("Error logout : " + e.getMessage());
        }
    }
}
