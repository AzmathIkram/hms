package com.azmath.hms.models;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;

@Entity
@Table(name = "hotel_amenities")
public class HotelAmenity {

    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
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

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("hotel", hotel.getId())
                .append("amenity", amenity.getId())
                .append("amount", amount)
                .append("chargable", chargable)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        HotelAmenity that = (HotelAmenity) o;

        return new EqualsBuilder()
                .append(id, that.id)
                .append(hotel.getId(), that.hotel.getId())
                .append(amenity.getId(), that.amenity.getId())
                .append(amount, that.amount)
                .append(chargable, that.chargable)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(hotel.getId())
                .append(amenity.getId())
                .append(amount)
                .append(chargable)
                .toHashCode();
    }
}
