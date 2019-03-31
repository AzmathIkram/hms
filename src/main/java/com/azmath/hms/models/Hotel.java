package com.azmath.hms.models;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "hotels")
public class Hotel {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "city_code")
    private String cityCode;

    @OneToMany(mappedBy = "hotel", fetch = FetchType.LAZY)
    private Set<HotelAmenity> hotelAmenitySet = new HashSet<>();

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

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public Hotel() {
    }

    public Hotel(Integer id, String name, String description, String cityCode) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.cityCode = cityCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Hotel hotel = (Hotel) o;

        return new EqualsBuilder()
                .append(id, hotel.id)
                .append(name, hotel.name)
                .append(description, hotel.description)
                .append(cityCode, hotel.cityCode)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(name)
                .append(description)
                .append(cityCode)
                .toHashCode();
    }
    public Set<HotelAmenity> getHotelAmenitySet() {
        return hotelAmenitySet;
    }

    public void setHotelAmenitySet(Set<HotelAmenity> hotelAmenitySet) {
        this.hotelAmenitySet = hotelAmenitySet;
    }
}
