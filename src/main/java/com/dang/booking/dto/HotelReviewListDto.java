package com.dang.booking.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class HotelReviewListDto {
    private int id;
    private String comment;
    private LocalDateTime reviewDate;
    private UserDto user;
    private List<ReviewReplyDto> reviewReplies;
}
