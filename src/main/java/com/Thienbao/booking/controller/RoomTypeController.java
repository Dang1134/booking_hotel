package com.Thienbao.booking.controller;

import com.Thienbao.booking.model.RoomType;
import com.Thienbao.booking.payload.request.InsertRoomTypeRequest;
import com.Thienbao.booking.payload.response.BaseResponse;
import com.Thienbao.booking.service.imp.RoomTypeServiceImp;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${api.base-path}/roomtype")


public class RoomTypeController {

    @Autowired
    private RoomTypeServiceImp roomTypeServiceImp ;

    @PostMapping
    public ResponseEntity<?> insertRoomType (HttpServletRequest request ,@Valid @RequestBody InsertRoomTypeRequest typeRequest) {

        RoomType roomType = roomTypeServiceImp.insertRoomType(request, typeRequest);
        BaseResponse baseResponse = BaseResponse.successBaseResponse(roomType, "Thêm mới thành công");
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);

    }

}
