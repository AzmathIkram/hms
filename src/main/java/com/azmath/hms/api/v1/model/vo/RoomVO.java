package com.azmath.hms.api.v1.model.vo;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class RoomVO {

    @Min(value = 0, message = "Room id for the room should be valid.")
    private int id;

    @NotNull(message = "Room description cannot be null")
    @Size(min = 1, max = 500, message = "Room name should not be greater than 500")
    private String description;

    @Min(value = 0, message = "Hotel id for the room should be valid.")
    private int hotelId;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }
}
