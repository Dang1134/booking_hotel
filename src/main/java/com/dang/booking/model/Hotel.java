package com.dang.booking.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.List;

@Entity (name = "hotels")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", length = 100)
    private String name;

    @Column(name = "avatar", length = 600)
    private String avatar;

    @Lob
    @Column(name="description",columnDefinition = "TEXT")
    private String description;

    @Column(name="phone", length = 15)
    private String phone;

    @Column(name = "is_deleted", columnDefinition = "TINYINT(1) DEFAULT 0")
    private boolean isDeleted;

    @Column(name = "open_time")
    private LocalTime openTime;

    @Column(name = "close_time")
    private LocalTime closeTime;

    @Column(name = "checkin_time")
    private LocalTime checkinTime;

    @Column(name = "checkout_time")
    private LocalTime checkoutTime;

    @Column(name = "rating", columnDefinition = "DECIMAL(2,1)")
    private BigDecimal rating;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private User user;

    @OneToOne(mappedBy = "hotel", cascade = CascadeType.ALL,orphanRemoval = true)
    private HotelAddress HotelAddress;

    @OneToMany(mappedBy = "hotel")
    private List<HotelImage> hotelImages;

    @OneToMany(mappedBy = "hotel")
    private List<HotelReviews> hotelReviews;

    @OneToMany(mappedBy = "hotel")
    private List<Booking> bookingList;

    @JsonIgnore
    @OneToMany(mappedBy = "hotel")
    private List<Room> roomList;

    @OneToMany(mappedBy = "hotel")
    private List<HotelAmenities> hotelAmenitiesList;
}
