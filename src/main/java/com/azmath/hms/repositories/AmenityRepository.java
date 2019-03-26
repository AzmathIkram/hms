package com.azmath.hms.repositories;

import com.azmath.hms.models.Amenity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AmenityRepository extends PagingAndSortingRepository<Amenity, Integer> {
}
