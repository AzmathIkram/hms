package com.azmath.hms.api.v1.model.vo;


import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

public class RoomAmenityVO {

    @PositiveOrZero(message = "RoomAmenity id must be a valid one.")
    private int id;

    @Positive(message = "Room id must be a valid one.")
    private int roomId;

    @Positive(message = "Amenity id must be a valid one.")
    private int amenityId;

    private String amenity;

    private String hotelName;

    @DecimalMin(value = "0.0", message = "RoomAmenity amount must be positive or zero")
    private double amount;

    private boolean chargable;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public int getAmenityId() {
        return amenityId;
    }

    public void setAmenityId(int amenityId) {
        this.amenityId = amenityId;
    }

    public String getAmenity() {
        return amenity;
    }

    public void setAmenity(String amenity) {
        this.amenity = amenity;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public boolean isChargable() {
        return chargable;
    }

    public void setChargable(boolean chargable) {
        this.chargable = chargable;
    }
}
