package com.azmath.hms.api.v1.model.vo;

import javax.validation.constraints.*;

public class HotelVO {

    @PositiveOrZero(message = "Hotel id cannot be negative")
    private int id;

    @NotNull(message = "Hotel name cannot be null")
    @Size(min = 1, max = 50, message = "Hotel name should not be greater than 50")
    private String name;

    @NotNull(message = "Hotel description cannot be null")
    @Size(max = 500, message = "Hotel description should not be greater than 500")
    private String description;

    @NotNull(message = "Hotel city code cannot be null")
    @Size(min = 3, max = 3, message = "Hotel city code must be 3")
    private String cityCode;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }
}
