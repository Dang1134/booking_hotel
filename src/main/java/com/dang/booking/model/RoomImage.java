package com.dang.booking.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity(name = "room_image")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @ManyToOne
    @JoinColumn(name = "room_id",referencedColumnName = "id")
    private Room room;

    @Column(name = "image_title")
    private String imageTitle;

    @Column(name = "image_description",length = 500)
    private String imageDescription;

    @Column(name = "image_path",length = 600)
    private String imagePath;

    @Column(name ="upload_date", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime uploadDate;


}
