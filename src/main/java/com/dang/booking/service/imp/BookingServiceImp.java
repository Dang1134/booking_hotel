package com.dang.booking.service.imp;

import com.dang.booking.dto.*;
import com.dang.booking.dto.BookingDetailDto;
import com.dang.booking.dto.BookingDto;
import com.dang.booking.dto.BookingListDto;
import com.dang.booking.dto.BookingListOfHotelierDto;
import com.dang.booking.payload.request.CancelBookingRequest;
import com.dang.booking.payload.request.CreateBookingRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookingServiceImp {
    BookingDto createBooking(CreateBookingRequest request, Long currentUserId);
    List<BookingListDto> getBookingsByUser(Long currentUserId);
    List<BookingListOfHotelierDto> getBookingsByHotelier(Long currentUserId, int hotelId);

    BookingDetailDto getDetailBooking(Long currentUserId, int bookingId);

    boolean cancelBooking(CancelBookingRequest request);
}
