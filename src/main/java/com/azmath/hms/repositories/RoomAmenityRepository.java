package com.azmath.hms.repositories;

import com.azmath.hms.models.Amenity;
import com.azmath.hms.models.Room;
import com.azmath.hms.models.RoomAmenity;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface RoomAmenityRepository extends PagingAndSortingRepository<RoomAmenity, Integer> {

    Optional<RoomAmenity> findByRoomAndAmenity(Room room, Amenity amenity);
    Optional<RoomAmenity> findByRoomAndAmenityAndIdNot(Room room, Amenity amenity, Integer id);

}
