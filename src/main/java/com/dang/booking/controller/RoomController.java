package com.dang.booking.controller;

import com.dang.booking.dto.RoomDto;
import com.dang.booking.model.Room;
import com.dang.booking.payload.request.InsertRoomRequest;
import com.dang.booking.payload.request.UpdateRoomRequest;
import com.dang.booking.payload.response.BaseResponse;
import com.dang.booking.service.imp.RoomServiceImp;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("${api.base-path}/room")

public class RoomController {
    @Autowired
    private RoomServiceImp roomServiceImp;
    @GetMapping
    public ResponseEntity<?> getAllRoom(){
        List<RoomDto> listRoomDTO = roomServiceImp.getAllRoom();
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatusCode(200);
        baseResponse.setMessage("Thanh cong");
        baseResponse.setData(listRoomDTO);

        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<?> insertRoom(HttpServletRequest request, @RequestBody InsertRoomRequest insertRoomRequest){
        Room room = roomServiceImp.insertRoom(request, insertRoomRequest);
        BaseResponse baseResponse = BaseResponse.successBaseResponse(room, "Thêm mới thành công");
        return new ResponseEntity<>(baseResponse,HttpStatus.OK);

    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateRoom(HttpServletRequest request, @RequestBody UpdateRoomRequest updateRoomRequest , @PathVariable int id) {
        Room room = roomServiceImp.updateRoom(request, updateRoomRequest, id);
        BaseResponse baseResponse = BaseResponse.successBaseResponse(room, "Update thành công");
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRoom(HttpServletRequest request, @PathVariable int id ){
        roomServiceImp.deleteRoom(request, id);
        BaseResponse baseResponse = BaseResponse.successBaseResponse(null , "Xoá phòng thành công");
        return new ResponseEntity<>(baseResponse,HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getRoomById (HttpServletRequest request , @PathVariable int id ){
        RoomDto roomDto = roomServiceImp.getRoomById(request, id);
        BaseResponse baseResponse = BaseResponse.successBaseResponse(roomDto , "Thành công ");
        return new ResponseEntity<>(baseResponse,HttpStatus.OK);
    }
}

