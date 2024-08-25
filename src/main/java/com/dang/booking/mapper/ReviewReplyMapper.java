package com.dang.booking.mapper;

import com.dang.booking.dto.ReviewReplyDto;
import com.dang.booking.model.ReviewReplies;
import org.springframework.stereotype.Service;

@Service
public class ReviewReplyMapper {
    public ReviewReplyDto convertToReviewReplyDto(ReviewReplies reviewReply){
        ReviewReplyDto reviewReplyDto = new ReviewReplyDto();
        reviewReplyDto.setReply(reviewReply.getReplyText());
        reviewReplyDto.setReplyDate(reviewReply.getReplyDate());
        return  reviewReplyDto;
    };
}
