package com.dang.booking.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "hotel_reviews")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HotelReviews {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @ManyToOne
    @JoinColumn(name = "hotel_id",referencedColumnName = "id")
    private Hotel hotel;

    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private User user;

    @Column(name = "comment", length = 500)
    private String comment;

    @Column(name = "review_date", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime reviewDate;

    @OneToMany(mappedBy = "hotelReview")
    private List<ReviewReplies> reviewReplies;
    @PrePersist
    protected void onCreate() {
        reviewDate = LocalDateTime.now();
    }

}
