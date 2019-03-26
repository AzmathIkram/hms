package com.azmath.hms.repositories;

import com.azmath.hms.models.HotelAmenity;
import com.azmath.hms.models.RoomAmenity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface HotelAmenityRepository extends PagingAndSortingRepository<HotelAmenity, Integer> {
}
