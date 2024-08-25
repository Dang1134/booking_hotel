package com.dang.booking.controller;

import com.dang.booking.dto.RoomTypeDto;
import com.dang.booking.model.RoomType;
import com.dang.booking.payload.request.InsertRoomTypeRequest;
import com.dang.booking.payload.request.UpdateRoomTypeRequest;
import com.dang.booking.payload.response.BaseResponse;
import com.dang.booking.service.imp.RoomTypeServiceImp;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.base-path}/roomtype")


public class RoomTypeController {

    @Autowired
    private RoomTypeServiceImp roomTypeServiceImp ;
    @GetMapping
    public ResponseEntity<?> getAllRoomType(HttpServletRequest request){
        List<RoomTypeDto> data = roomTypeServiceImp.getAllRoomType(request);
        BaseResponse baseResponse = BaseResponse.successBaseResponse(data, "Thành công");
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getIdRoomType(HttpServletRequest request , @PathVariable int id ){
        List<RoomTypeDto> data = roomTypeServiceImp.getIdRoomType(request, id);
        BaseResponse baseResponse = BaseResponse.successBaseResponse(data, "Thành công");
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<?> insertRoomType (HttpServletRequest request ,@Valid @RequestBody InsertRoomTypeRequest typeRequest) {

        RoomType roomType = roomTypeServiceImp.insertRoomType(request, typeRequest);
        BaseResponse baseResponse = BaseResponse.successBaseResponse(roomType, "Thêm mới thành công");
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);

    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateRoomType(HttpServletRequest request , @RequestBody UpdateRoomTypeRequest updateRoomTypeRequest , @PathVariable int id){

            RoomType roomType = roomTypeServiceImp.updateRoomType(request, updateRoomTypeRequest, id);
            BaseResponse baseResponse = BaseResponse.successBaseResponse(updateRoomTypeRequest , "Cập nhật thành công");
            return new ResponseEntity<>(baseResponse, HttpStatus.OK);
        }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRoomType(HttpServletRequest request ,@PathVariable int id) {

            roomTypeServiceImp.deleteRoomType(request, id);
            BaseResponse baseResponse = BaseResponse.successBaseResponse(null ," Xóa loại phòng thành công ");
            return new ResponseEntity<>(baseResponse, HttpStatus.OK);

    }
}
