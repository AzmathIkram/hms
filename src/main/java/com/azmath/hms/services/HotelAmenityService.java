package com.azmath.hms.services;

import com.azmath.hms.models.RoomAmenity;
import com.azmath.hms.repositories.RoomAmenityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@Service
public class HotelAmenityService {

    @Autowired
    private RoomAmenityRepository  roomAmenityRepository;

    @GetMapping
    public Page<RoomAmenity> search(Pageable page) {
        return roomAmenityRepository.findAll(page);
    }

    public RoomAmenity save(RoomAmenity roomAmenity) {
        return roomAmenityRepository.save(roomAmenity);

    }

    public RoomAmenity findById(Integer id) {
        Optional<RoomAmenity> optionalRoomAmenity = roomAmenityRepository.findById(id);
        return optionalRoomAmenity.isPresent() ? optionalRoomAmenity.get() : null;
    }

    public void delete(Integer id) {
        roomAmenityRepository.delete(findById(id));
    }



}
