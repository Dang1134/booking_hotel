package com.dang.booking.mapper;

import com.dang.booking.dto.HotelReviewDto;
import com.dang.booking.dto.HotelReviewListDto;
import com.dang.booking.dto.ReviewReplyDto;
import com.dang.booking.dto.UserDto;
import com.dang.booking.model.HotelReviews;
import com.dang.booking.model.ReviewReplies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HotelReviewMapper {

    @Autowired
    private ReviewReplyMapper reviewReplyMapper;

    public HotelReviewDto hotelReviewConvertToHotelReviewDto(HotelReviews hotelReviews){
        HotelReviewDto hotelReviewDto = new HotelReviewDto();
        hotelReviewDto.setId(hotelReviews.getId());
        hotelReviewDto.setComment(hotelReviews.getComment());
        hotelReviewDto.setReviewDate(hotelReviews.getReviewDate());
        hotelReviewDto.setNameUser(hotelReviews.getUser().getFullName());
        return hotelReviewDto;
    }
    public HotelReviewListDto hotelReviewConvertToHotelReviewListDto(HotelReviews hotelReviews){
        HotelReviewListDto hotelReviewListDto = new HotelReviewListDto();
        hotelReviewListDto.setId(hotelReviews.getId());
        hotelReviewListDto.setComment(hotelReviews.getComment());
        hotelReviewListDto.setReviewDate(hotelReviews.getReviewDate());

        UserDto userDto = new UserDto();
        userDto.setId(hotelReviews.getUser().getId());
        userDto.setFullName(hotelReviews.getUser().getFullName());

        List<ReviewReplies> reviewReplies = hotelReviews.getReviewReplies();
        List<ReviewReplyDto> reviewReplyDtoList = new ArrayList<>();
        reviewReplies.forEach(item->{
            reviewReplyDtoList.add(reviewReplyMapper.convertToReviewReplyDto(item));
        });

        hotelReviewListDto.setUser(userDto);
        hotelReviewListDto.setReviewReplies(reviewReplyDtoList);

        return hotelReviewListDto;
    }

}
