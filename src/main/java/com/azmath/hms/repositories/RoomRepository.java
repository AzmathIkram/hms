package com.azmath.hms.repositories;

import com.azmath.hms.models.Hotel;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends PagingAndSortingRepository<Hotel, Integer> {
}
