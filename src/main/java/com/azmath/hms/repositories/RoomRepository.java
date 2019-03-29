package com.azmath.hms.repositories;

import com.azmath.hms.models.Hotel;
import com.azmath.hms.models.Room;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoomRepository extends PagingAndSortingRepository<Room, Integer> {

    Optional<Room> findByDescription(String name);
    Optional<Room> findByDescriptionAndHotel(String description, Hotel hotel);
    Optional<Room> findByDescriptionAndIdNotAndHotel(String description, int id, Hotel hotel);

}
