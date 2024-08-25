package com.dang.booking.service.imp;

import com.dang.booking.dto.HotelReviewDto;
import com.dang.booking.dto.HotelReviewListDto;
import com.dang.booking.dto.ReviewReplyDto;
import com.dang.booking.payload.request.CreateReplyRequest;
import com.dang.booking.payload.request.CreateReviewRequest;

import java.util.List;

public interface ReviewServiceImp {
    HotelReviewDto createReview(CreateReviewRequest createReviewRequest, Long currentUserId);
    ReviewReplyDto createReply(CreateReplyRequest createReplyRequest, Long currentUserId);

    List<HotelReviewListDto> getReviewsByHotelier(int hotelId, Long currentUserId);

}
