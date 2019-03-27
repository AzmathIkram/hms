package com.azmath.hms.repositories;

import com.azmath.hms.models.Hotel;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRepository extends PagingAndSortingRepository<Hotel, Integer> {
    Hotel findByName(String name);
}
