package com.azmath.hms.repositories;

import com.azmath.hms.models.Amenity;
import com.azmath.hms.models.Hotel;
import com.azmath.hms.models.HotelAmenity;
import com.azmath.hms.models.RoomAmenity;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface HotelAmenityRepository extends PagingAndSortingRepository<HotelAmenity, Integer> {
    Optional<HotelAmenity> findByHotelAndAmenity(Hotel hotel, Amenity amenity);
    Optional<HotelAmenity> findByHotelAndAmenityAndIdNot(Hotel hotel, Amenity amenity, int id);
}
