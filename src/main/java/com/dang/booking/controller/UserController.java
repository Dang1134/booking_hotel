package com.dang.booking.controller;


import com.dang.booking.payload.request.CreateUserRequest;
import com.dang.booking.payload.request.UpdateUserRequest;
import com.dang.booking.payload.response.BaseResponse;
import com.dang.booking.dto.UserDto;
import com.dang.booking.security.DataSecurity;
import com.dang.booking.service.imp.UserServiceImp;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("${api.base-path}/user")
public class UserController {

    @Autowired
    private UserServiceImp userServiceImp;


    @CrossOrigin
    @GetMapping("/me")
    public ResponseEntity<?> getMe() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        DataSecurity dataSecurity = (DataSecurity) authentication.getPrincipal();
        String email = dataSecurity.getEmail();

        UserDto userDto = userServiceImp.getUserDetail(email);

        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatusCode(200);
        baseResponse.setMessage("Get Current User Successful");
        baseResponse.setData(userDto);
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    // Create user

    @CrossOrigin
    @PostMapping("/signup")
    public ResponseEntity<?> createUser(@RequestBody @Valid CreateUserRequest createUserRequest) {

       UserDto newUser = userServiceImp.createUser(createUserRequest);

        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatusCode(200);
        baseResponse.setMessage("Create User Successful");
        baseResponse.setData(newUser);


        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    // update user
    @PutMapping("/update")
    public ResponseEntity<?> updateUser(@Valid UpdateUserRequest updateUserRequest){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        DataSecurity dataSecurity = (DataSecurity) authentication.getPrincipal();
        Long currentUserId = dataSecurity.getId();

        UserDto userIsUpdate = userServiceImp.updateUser(updateUserRequest,currentUserId);
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatusCode(HttpStatus.OK.value());
        baseResponse.setMessage("update user success");
        baseResponse.setData(userIsUpdate);

        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }
}
