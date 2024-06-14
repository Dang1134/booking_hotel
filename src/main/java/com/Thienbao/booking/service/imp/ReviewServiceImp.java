package com.Thienbao.booking.service.imp;

import com.Thienbao.booking.dto.HotelReviewDto;
import com.Thienbao.booking.dto.ReviewReplyDto;
import com.Thienbao.booking.payload.request.CreateReplyRequest;
import com.Thienbao.booking.payload.request.CreateReviewRequest;
import org.apache.coyote.BadRequestException;

public interface ReviewServiceImp {
    HotelReviewDto createReview(CreateReviewRequest createReviewRequest, Long currentUserId);
    ReviewReplyDto createReply(CreateReplyRequest createReplyRequest, Long currentUserId);

}
