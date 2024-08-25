package com.dang.booking.service;

import com.dang.booking.dto.*;
import com.dang.booking.exception.BadRequestException;
import com.dang.booking.exception.CreateException;
import com.dang.booking.exception.NotFoundException;
import com.dang.booking.exception.UserAlreadyReviewException;
import com.dang.booking.mapper.BookingMapper;
import com.dang.booking.model.*;
import com.dang.booking.model.key.BookingRoomId;
import com.dang.booking.payload.request.CancelBookingRequest;
import com.dang.booking.payload.request.CreateBookingRequest;
import com.dang.booking.repository.*;
import com.dang.booking.service.imp.BookingServiceImp;
import com.dang.booking.utils.DateUtils;
import com.dang.booking.dto.BookingDetailDto;
import com.dang.booking.dto.BookingDto;
import com.dang.booking.dto.BookingListDto;
import com.dang.booking.dto.BookingListOfHotelierDto;
import com.dang.booking.model.*;
import com.dang.booking.repository.BookingRepository;
import com.dang.booking.repository.BookingRoomRepository;
import com.dang.booking.repository.HotelRepository;
import com.dang.booking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService implements BookingServiceImp {

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private BookingRoomRepository bookingRoomRepository;

    @Autowired
    private DateUtils dateUtils;

    @Autowired
    private BookingMapper bookingMapper;



    @Transactional
    @Override
    public BookingDto createBooking(CreateBookingRequest request, Long currentUserId) {

        LocalDate checkinDate = dateUtils.convertStringToLocalDate(request.getCheckinDate());
        LocalDate checkoutDate = dateUtils.convertStringToLocalDate(request.getCheckoutDate());
        if(!checkoutDate.isAfter(checkinDate)) throw new BadRequestException("check out date must be after check in date");

        User user = userRepository.findById(currentUserId).orElseThrow(() -> new NotFoundException("Not found user with id: " + currentUserId));
        Hotel hotel = hotelRepository.findById(request.getHotelId()).orElseThrow(() -> new NotFoundException("Not found hotel with id: " + request.getHotelId()));
        List<Room> rooms = hotel.getRoomList();
//        if (rooms.stream().noneMatch(room -> room.getId() == request.getRoomId()))
//            throw new NotFoundException("Not found room of hotel with id_room :" + request.getRoomId());
        Optional<Room> room = rooms.stream().filter(item -> item.getId() == request.getRoomId()).findFirst();
        if(room.isEmpty()) throw new NotFoundException("Not found room of hotel with id_room :" + request.getRoomId());
        if(request.getTotalPrice() < room.get().getPrice()) throw new BadRequestException("The total amount cannot be less than the room rental price");

        try {
            Booking booking = new Booking();
            booking.setUser(user);
            booking.setHotel(hotel);
            booking.setTotalPrice(request.getTotalPrice());
            booking.setPaymentAmount(request.getPaymentAmount());
            booking.setBookingDate(LocalDateTime.now());
            booking.setPaymentDate(LocalDateTime.now());
            booking.setPaymentStatus(PAYMENT_STATUS.TRANSFERRED);

            Booking newBooking = bookingRepository.save(booking);

            BookingRoom bookingRoom = new BookingRoom();

            BookingRoomId bookingRoomId = new BookingRoomId();
            bookingRoomId.setBookingId(newBooking.getId());
            bookingRoomId.setRoomId(request.getRoomId());

            bookingRoom.setBookingRoomId(bookingRoomId);
            bookingRoom.setRoom(room.get());
            bookingRoom.setStatus(BOOKING_ROOM_STATUS.PENDING);
            bookingRoom.setCheckinDate(checkinDate);
            bookingRoom.setCheckoutDate(checkoutDate);

            BookingRoom newBookingRoom = bookingRoomRepository.save(bookingRoom);

            return bookingMapper.convertToBookingDto(newBooking,newBookingRoom);

        } catch (Exception ex) {
            throw new CreateException("Error create booking: " + ex.getMessage());
        }

    }

    @Override
    public List<BookingListDto> getBookingsByUser(Long currentUserId) {
       List<Booking> bookings = bookingRepository.findByUserId(currentUserId);
       if(bookings.isEmpty()) throw new NotFoundException("The user has not made any reservations");

       List<BookingListDto> bookingListDtoList = new ArrayList<>();
       bookings.forEach(item -> {
            bookingListDtoList.add(bookingMapper.convertToBookingListDto(item));
       });

       return bookingListDtoList;
    }

    @Override
    public List<BookingListOfHotelierDto> getBookingsByHotelier(Long currentUserId, int hotelId) {
        Hotel hotel = hotelRepository.findById(hotelId).orElseThrow(() -> new NotFoundException("Not found hotel with id: " + hotelId));
        if(!currentUserId.equals(hotel.getUser().getId())) throw new UserAlreadyReviewException("The user must be the owner of the hotel");
        List<Booking> bookings = bookingRepository.findByHotelId(hotelId);
        if(bookings.isEmpty()) throw new NotFoundException("The hotel has no bookings yet");
        List<BookingListOfHotelierDto> bookingListOfHotelierDtos  = new ArrayList<>();
        bookings.forEach(item -> {
           bookingListOfHotelierDtos.add(bookingMapper.convertToBookingListOfHotelierDto(item));
        });

        return bookingListOfHotelierDtos;
    }

    @Override
    public BookingDetailDto getDetailBooking(Long currentUserId, int bookingId) {
        Booking booking = bookingRepository.findById(bookingId).orElseThrow(()-> new NotFoundException("Not found booking with booking Id: "+ bookingId));
        return bookingMapper.convertToBookingDetailDto(booking);
    }

    @Override
    public boolean cancelBooking(CancelBookingRequest request) {

        BookingRoom bookingRoom = bookingRoomRepository.findByBookingIdAndRoomId(request.getBookingId(), request.getRoomId());
        if(bookingRoom == null) throw new NotFoundException("Not found booking room with booking id and roomId");

        bookingRoom.setStatus(BOOKING_ROOM_STATUS.CANCELLED);

       bookingRoomRepository.save(bookingRoom);

        return true;
    };


}
