package com.dang.booking.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity(name = "amenities")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Amenities {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "icon",length = 1900)
    private String icon;

    @OneToMany(mappedBy = "amenity")
    private List<HotelAmenities> hotelAmenitiesList;

    @OneToMany(mappedBy = "amenity")
    private List<RoomAmenities> roomAmenitiesList;
}
