package com.dang.booking.model;

import com.dang.booking.model.key.RoomAmenitiesId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "room_amenities")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomAmenities {

    @EmbeddedId
    private RoomAmenitiesId id;

    @ManyToOne
    @JoinColumn(name = "room_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Room room;


    @ManyToOne
    @JoinColumn(name = "amenity_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Amenities amenity;

}
