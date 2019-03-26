package com.azmath.hms.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "rooms")
public class Room {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name="hotel_id", nullable=false)
    private Hotel hotel;

    @OneToMany(mappedBy = "room")
    private Set<RoomAmenity> roomAmenities = new HashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

//    public Set<RoomAmenity> getRoomAmenities() {
//        return roomAmenities;
//    }
//
//    public void setRoomAmenities(Set<RoomAmenity> roomAmenities) {
//        this.roomAmenities = roomAmenities;
//    }
}
