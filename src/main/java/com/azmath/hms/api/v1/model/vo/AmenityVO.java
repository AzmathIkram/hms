package com.azmath.hms.api.v1.model.vo;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AmenityVO {
    @Min(value = 0, message = "Amenity id for the room should be valid.")
    private int id;

    @NotNull(message = "Amenity description cannot be null")
    @Size(min = 1, max = 10, message = "Amenity name should not be greater than 10")
    private String name;

    @NotNull
    @Size(min = 1, max = 500, message = "Amenity description should not be greater than 500")
    private String description;

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
}
