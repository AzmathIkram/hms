package com.azmath.hms.models;


import javax.persistence.*;

@Entity
@Table(name = "amenities_master")
public class Amenity {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "short_desc")
    private String name;

    @Column(name = "description")
    private String description;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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