package com.azmath.hms.repositories;

import com.azmath.hms.models.Hotel;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HotelRepository extends PagingAndSortingRepository<Hotel, Integer> {

    Hotel findByName(String name);

    Optional<Hotel> findOneByName(String name);
    Optional<Hotel> findOneByNameAndIdNot(String name, Integer id);
}
