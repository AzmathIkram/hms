package com.azmath.hms.repositories;

import com.azmath.hms.models.Amenity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AmenityRepository extends PagingAndSortingRepository<Amenity, Integer> {

    Optional<Amenity> findByName(String name);
    Optional<Amenity> findByNameAndIdNot(String name, int id);
}
