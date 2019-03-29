package com.azmath.hms.api.v1.model.vo;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

public class HotelAmenityVO {

    @PositiveOrZero(message = "HotelAmenity id must be positive or zero.")
    private int id;
    @Positive(message = "Amenity id for the HotelAmenity should be a positive value.")
    private int amenityId;

    private String amenityName;

    @Positive(message = "Hotel id for the HotelAmenity should be a positive value.")
    private int hotelId;

    private String hotelName;

    @DecimalMin(value = "0.0", message = "HotelAmenity amount must be positive or zero")
    private double amount;

    private boolean chargable;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAmenityId() {
        return amenityId;
    }

    public void setAmenityId(int amenityId) {
        this.amenityId = amenityId;
    }

    public String getAmenityName() {
        return amenityName;
    }

    public void setAmenityName(String amenityName) {
        this.amenityName = amenityName;
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
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
