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
public class BookingRoomId implements Serializable {
    @Column(name = "booking_id")
    private long bookingId;

    @Column(name="room_id")
    private int roomId;
}
