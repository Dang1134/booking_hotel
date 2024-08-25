package com.dang.booking.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity(name = "review_replies")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewReplies {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "review_id", referencedColumnName = "id")
    private HotelReviews hotelReview;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Column(name = "reply_text", length = 500)
    private String replyText;

    @Column(name = "reply_date",columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime replyDate;

    @PrePersist
    protected void onCreate() {
        replyDate = LocalDateTime.now();
    }
}
