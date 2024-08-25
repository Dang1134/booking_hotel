package com.dang.booking.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "hotel_address")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HotelAddress {

    @Id
    @Column(name = "hotel_id")
    private int hotelId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "hotel_id", referencedColumnName = "id")
    private Hotel hotel;

    @Column(name="street_number")
    private int streetNumber;

    @Column(name="street_name", length = 100)
    private String streetName;

    @Column(name = "district",length = 100)
    private String district;

    @Column(name = "city",length = 100)
    private String city;

    @Column(name = "province",length = 100)
    private String province;

    @Column(name = "country", length = 100)
    private String country;

}
