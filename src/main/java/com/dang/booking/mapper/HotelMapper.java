package com.dang.booking.mapper;


import com.dang.booking.dto.*;
import com.dang.booking.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HotelMapper {

    @Autowired
    private HotelImageMapper hotelImageMapper;

    @Autowired
    private HotelAddressMapper hotelAddressMapper;

    @Autowired
    private HotelReviewMapper hotelReviewMapper;

    @Autowired
    private RoomMapper roomMapper;

    @Autowired
    private AmenityMapper amenityMapper;

    public HotelDto hotelConvertToHotelDto(Hotel hotel) {
        HotelDto hotelDto = new HotelDto();
        hotelDto.setId(hotel.getId());
        hotelDto.setName(hotel.getName());
        hotelDto.setAvatar(hotel.getAvatar());
        hotelDto.setDescription(hotel.getDescription());
        hotelDto.setPhone(hotel.getPhone());
        hotelDto.setOpenTime(hotel.getOpenTime());
        hotelDto.setCloseTime(hotel.getCloseTime());
        hotelDto.setCheckinTime(hotel.getCheckinTime());
        hotelDto.setCheckoutTime(hotel.getCheckoutTime());
        hotelDto.setRating(hotel.getRating());
        hotelDto.setDeleted(hotel.isDeleted());
        return hotelDto;
    }

    public HotelListDto hotelConvertToHotelListDto(Hotel hotel) {
        HotelListDto hotelListDto = new HotelListDto();
        hotelListDto.setId(hotel.getId());
        hotelListDto.setName(hotel.getName());
        hotelListDto.setAvatar(hotel.getAvatar());
        hotelListDto.setDescription(hotel.getDescription());
        hotelListDto.setPhone(hotel.getPhone());
        hotelListDto.setOpenTime(hotel.getOpenTime());
        hotelListDto.setCloseTime(hotel.getCloseTime());
        hotelListDto.setCheckinTime(hotel.getCheckinTime());
        hotelListDto.setCheckoutTime(hotel.getCheckoutTime());
        hotelListDto.setRating(hotel.getRating());
        hotelListDto.setDeleted(hotel.isDeleted());

        List<HotelImage> hotelImageList = hotel.getHotelImages();
        List<HotelImageDto> hotelImageDtoList = new ArrayList<>();
        if (hotelImageList != null) {
            for (HotelImage hotelImage : hotelImageList) {
                hotelImageDtoList.add(hotelImageMapper.HotelImageConvertToHotelImageDto(hotelImage));
            }
        }
        hotelListDto.setHotelImageDtoList(hotelImageDtoList);

        hotelListDto.setHotelAddressDto(hotelAddressMapper.hotelAddressConvertTohotelAddressDto(hotel.getHotelAddress()));

        List<HotelReviews> hotelReviewsList = hotel.getHotelReviews();
        List<HotelReviewDto> hotelReviewDtoList = new ArrayList<>();
        if (hotelReviewsList != null) {
            for (HotelReviews hotelReview : hotelReviewsList) {
                hotelReviewDtoList.add(hotelReviewMapper.hotelReviewConvertToHotelReviewDto(hotelReview));
            }
        }
        hotelListDto.setHotelReviewDtoList(hotelReviewDtoList);


        return hotelListDto;
    }

    public HotelDetailDto hotelConvertHotelDetailDto(Hotel hotel, HotelDetailDto hotelDetailDto) {

        hotelDetailDto.setId(hotel.getId());
        hotelDetailDto.setName(hotel.getName());
        hotelDetailDto.setAvatar(hotel.getAvatar());
        hotelDetailDto.setDescription(hotel.getDescription());
        hotelDetailDto.setPhone(hotel.getPhone());
        hotelDetailDto.setOpenTime(hotel.getOpenTime());
        hotelDetailDto.setCloseTime(hotel.getCloseTime());
        hotelDetailDto.setCheckinTime(hotel.getCheckinTime());
        hotelDetailDto.setCheckoutTime(hotel.getCheckoutTime());
        hotelDetailDto.setRating(hotel.getRating());
        hotelDetailDto.setDeleted(hotel.isDeleted());

        List<HotelImage> hotelImageList = hotel.getHotelImages();
        List<HotelImageDto> hotelImageDtoList = new ArrayList<>();
        if (hotelImageList != null) {
            for (HotelImage hotelImage : hotelImageList) {
                hotelImageDtoList.add(hotelImageMapper.HotelImageConvertToHotelImageDto(hotelImage));
            }
        }
        hotelDetailDto.setHotelImageDtoList(hotelImageDtoList);

        hotelDetailDto.setHotelAddressDto(hotelAddressMapper.hotelAddressConvertTohotelAddressDto(hotel.getHotelAddress()));

        List<HotelReviews> hotelReviewsList = hotel.getHotelReviews();
        List<HotelReviewDto> hotelReviewDtoList = new ArrayList<>();
        if (hotelReviewsList != null) {
            for (HotelReviews hotelReview : hotelReviewsList) {
                hotelReviewDtoList.add(hotelReviewMapper.hotelReviewConvertToHotelReviewDto(hotelReview));
            }
        }

        hotelDetailDto.setHotelReviewDtoList(hotelReviewDtoList);

        List<Room> roomList = hotel.getRoomList();
        List<RoomDto> roomDtoList = new ArrayList<>();
        if(roomList != null){
            for (Room room : roomList) {
                roomDtoList.add(roomMapper.roomConvertToRoomDto(room));
            }
        }
        hotelDetailDto.setRooms(roomDtoList);

        List<HotelAmenities> hotelAmenities = hotel.getHotelAmenitiesList();
        List<HotelAmenitiesDto> hotelAmenitiesDtoList = new ArrayList<>();
        if(hotelAmenities != null){
            for (HotelAmenities hotelAmenity : hotelAmenities) {
                HotelAmenitiesDto hotelAmenitiesDto = new HotelAmenitiesDto();
                hotelAmenitiesDto.setName(hotelAmenity.getAmenity().getName());
                hotelAmenitiesDto.setIcon(hotelAmenity.getAmenity().getIcon());
                hotelAmenitiesDtoList.add(hotelAmenitiesDto);
            }
        }
        hotelDetailDto.setAmenitiesOfHotel(hotelAmenitiesDtoList);


        return hotelDetailDto;
    }

    ;

}
