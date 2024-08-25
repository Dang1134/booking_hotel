package com.dang.booking.model;

import com.dang.booking.model.key.HotelAmenitiesId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "hotel_amenities")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HotelAmenities {

    @EmbeddedId
    private HotelAmenitiesId id;

    @ManyToOne
    @JoinColumn(name = "hotel_id",referencedColumnName = "id", insertable = false, updatable = false)
    private Hotel hotel;


    @ManyToOne
    @JoinColumn(name = "amenity_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Amenities amenity;


}
