package com.azmath.hms.repositories;

import com.azmath.hms.models.Room;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends PagingAndSortingRepository<Room, Integer> {
}
