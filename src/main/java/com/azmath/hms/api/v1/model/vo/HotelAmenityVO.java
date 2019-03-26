package com.azmath.hms.api.v1.model.vo;

public class HotelAmenityVO {

    private int id;
    private int amenityId;
    private String amenityName;
    private int hotelId;
    private String hotelName;
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
