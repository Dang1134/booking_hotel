package com.dang.booking.model.key;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class HotelAmenitiesId implements Serializable {
    @Column(name = "hotel_id")
    private int hotelId;
    @Column(name = "amenity_id")
    private int amenityId;
}
