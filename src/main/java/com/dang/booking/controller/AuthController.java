package com.dang.booking.controller;

import com.dang.booking.model.User;
import com.dang.booking.payload.request.LoginRequest;
import com.dang.booking.payload.request.LogoutRequest;
import com.dang.booking.payload.response.BaseResponse;
import com.dang.booking.security.DataSecurity;
import com.dang.booking.service.AuthService;
import com.dang.booking.utils.JwtHelper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${api.base-path}/auth")
public class AuthController {

    @Autowired
    private JwtHelper jwtHelper;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest) {

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword());
        authenticationManager.authenticate(token);


        // String roleName = authService.getUserByEmail(loginRequest.getEmail()).getRole().getName();
        User user = authService.getUserByEmail(loginRequest.getEmail());

        String roleName = user.getRole().getName();
        Long id = user.getId();

        // Logic (add thêm email của client vào token)
        DataSecurity dataSecurity = new DataSecurity();
        dataSecurity.setEmail(loginRequest.getEmail());
        dataSecurity.setRoleName(roleName);
       dataSecurity.setId(id);

        String authenToken = jwtHelper.generateToken(dataSecurity);
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatusCode(200);
        baseResponse.setMessage("Login success");
        baseResponse.setAccessToken(authenToken);

        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@Valid LogoutRequest logoutRequest) {
        return new ResponseEntity<>("", HttpStatus.OK);
    }

}
