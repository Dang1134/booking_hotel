package com.dang.booking.mapper;

import com.dang.booking.dto.*;
import com.dang.booking.model.Booking;
import com.dang.booking.model.BookingRoom;
import com.dang.booking.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookingMapper {

    @Autowired
    private BookingRoomMapper bookingRoomMapper;

    @Autowired
    private HotelMapper hotelMapper;

    @Autowired
    private UserMapper userMapper;




    public BookingDto convertToBookingDto(Booking booking, BookingRoom bookingRoom){
        BookingDto bookingDto = new BookingDto();
        bookingDto.setHotelId(booking.getHotel().getId());
        bookingDto.setBookingDate(booking.getBookingDate());
        bookingDto.setTotalPrice(booking.getTotalPrice());
        bookingDto.setPaymentAmount(booking.getPaymentAmount());
        bookingDto.setPaymentStatus(booking.getPaymentStatus());
        bookingDto.setPaymentDate(booking.getPaymentDate());
        bookingDto.setBookingRoom(bookingRoomMapper.convertToBookingRoomDto(bookingRoom));
        return bookingDto;
    }

    public BookingListDto convertToBookingListDto(Booking booking){
        BookingListDto bookingListDto = new BookingListDto();
        bookingListDto.setBookingDate(booking.getBookingDate());
        bookingListDto.setTotalPrice(booking.getTotalPrice());
        bookingListDto.setPaymentAmount(booking.getPaymentAmount());
        bookingListDto.setPaymentStatus(booking.getPaymentStatus());
        bookingListDto.setPaymentDate(booking.getPaymentDate());
        bookingListDto.setHotel(hotelMapper.hotelConvertToHotelDto(booking.getHotel()));

        List<BookingRoom> bookingRooms = booking.getBookingRoomList();
        List<BookingRoomDto> bookingRoomDtos = new ArrayList<>();

        bookingRooms.forEach(item -> {
            bookingRoomDtos.add(bookingRoomMapper.convertToBookingRoomDto(item));
        });
        bookingListDto.setBookingRoomList(bookingRoomDtos);

        return  bookingListDto;
    }

    public BookingListOfHotelierDto convertToBookingListOfHotelierDto(Booking booking){
        BookingListOfHotelierDto bookingListOfHotelierDto = new BookingListOfHotelierDto();
        bookingListOfHotelierDto.setUser(userMapper.userConvertToUserDto(booking.getUser()));
        bookingListOfHotelierDto.setBookingDate(booking.getBookingDate());
        bookingListOfHotelierDto.setTotalPrice(booking.getTotalPrice());
        bookingListOfHotelierDto.setPaymentAmount(booking.getPaymentAmount());
        bookingListOfHotelierDto.setPaymentStatus(booking.getPaymentStatus());
        bookingListOfHotelierDto.setPaymentDate(booking.getPaymentDate());

        List<BookingRoom> bookingRooms = booking.getBookingRoomList();
        List<BookingRoomDto> bookingRoomDtos = new ArrayList<>();

        bookingRooms.forEach(item -> {
            bookingRoomDtos.add(bookingRoomMapper.convertToBookingRoomDto(item));
        });
        bookingListOfHotelierDto.setBookingRoomList(bookingRoomDtos);

        return bookingListOfHotelierDto;
    }
    public BookingDetailDto convertToBookingDetailDto(Booking booking){
        BookingDetailDto bookingDetailDto = new BookingDetailDto();
        bookingDetailDto.setUser(userMapper.userConvertToUserDto(booking.getUser()));
        bookingDetailDto.setHotel(hotelMapper.hotelConvertToHotelDto(booking.getHotel()));
        bookingDetailDto.setBookingDate(booking.getBookingDate());
        bookingDetailDto.setTotalPrice(booking.getTotalPrice());
        bookingDetailDto.setPaymentAmount(booking.getPaymentAmount());
        bookingDetailDto.setPaymentStatus(booking.getPaymentStatus());
        bookingDetailDto.setPaymentDate(booking.getPaymentDate());

        List<BookingRoom> bookingRooms = booking.getBookingRoomList();
        List<BookingRoomDto> bookingRoomDtos = new ArrayList<>();

        bookingRooms.forEach(item -> {
            bookingRoomDtos.add(bookingRoomMapper.convertToBookingRoomDto(item));
        });
        bookingDetailDto.setBookingRoomList(bookingRoomDtos);

        return bookingDetailDto;
    }
}
