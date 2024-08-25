package com.dang.booking.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReviewReplyDto {
    private String reply;
    private LocalDateTime replyDate;
}
