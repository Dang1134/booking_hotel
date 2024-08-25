package com.dang.booking.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "booking")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name="hotel_id", referencedColumnName = "id")
    private Hotel hotel;

    @Column(name = "booking_date", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime bookingDate;

    @Column(name = "total_price")
    private double totalPrice;

    @Column(name = "payment_amount")
    private double paymentAmount;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_status", columnDefinition = "ENUM('TRANSFERRED','NOTTRANSFERRED') DEFAULT 'NOTTRANSFERRED'")
    private PAYMENT_STATUS paymentStatus;

    @Column(name = "payment_date", columnDefinition = "TIMESTAMP")
    private LocalDateTime paymentDate;

    @OneToMany(mappedBy = "booking")
    private List<BookingRoom> bookingRoomList;

}
