package com.dang.booking.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity(name = "rooms")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "room_number")
    private int roomNumber;

    @ManyToOne
    @JoinColumn(name = "roomtype_id",referencedColumnName = "id")
    private RoomType roomType;

    @Lob
    @Column(name="description",columnDefinition = "TEXT")
    private String description;

    @Column(name = "price")
    private double price;

    @ManyToOne
    @JoinColumn(name = "hotel_id",referencedColumnName = "id")
    private Hotel hotel;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", columnDefinition = "ENUM('AVAILABLE','BOOKED','OCCUPIED','MAINTENANCE','CLEANING') DEFAULT 'AVAILABLE'")
    private ROOM_STATUS status;

    @OneToMany(mappedBy = "room")
    private List<RoomImage> roomImageList;

    @OneToMany(mappedBy = "room")
    private List<BookingRoom> bookingRoomList;


    @OneToMany(mappedBy = "room")
    private List<RoomAmenities> roomAmenitiesList;

}
