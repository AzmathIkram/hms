package com.azmath.hms.models;

import javax.persistence.*;

@Entity
@Table(name = "room_amenities")
public class RoomAmenity {

    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "room_id")
    private Room room;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "amenity_id")
    private Amenity amenity;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "chargable")
    private Boolean chargable;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Amenity getAmenity() {
        return amenity;
    }

    public void setAmenity(Amenity amenity) {
        this.amenity = amenity;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Boolean getChargable() {
        return chargable;
    }

    public void setChargable(Boolean chargable) {
        this.chargable = chargable;
    }
}
