package com.dang.booking.model.key;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class RoomAmenitiesId implements Serializable {
    @Column(name = "room_id")
    private int roomId;

    @Column(name="amenity_id")
    private int amenityId;
}
