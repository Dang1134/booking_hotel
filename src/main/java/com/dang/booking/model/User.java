package com.dang.booking.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="email",length = 100,unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name="fullname",length = 100)
    private String fullName;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "address")
    private String address;

    @Column(name = "phone",length = 15)
    private String phone;

    @Enumerated(EnumType.STRING)
    @Column(name = "sex")
    private USER_SEX sex;

    @Column(name = "is_deleted",columnDefinition = "TINYINT(1) DEFAULT 0")
    private boolean isDeleted;

    @ManyToOne
    @JoinColumn(name="role_id",referencedColumnName = "id")
    private Role role;

    @OneToMany(mappedBy = "user")
    private List<Hotel> hotels;

    @OneToMany(mappedBy = "user")
    private List<HotelReviews> hotelReviews;

    @OneToMany(mappedBy = "user")
    private List<ReviewReplies> reviewReplies;

    @OneToMany(mappedBy = "user")
    private List<Booking> bookingList;

}
