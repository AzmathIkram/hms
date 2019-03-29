package com.azmath.hms.services;

import com.azmath.hms.common.exceptions.ResourceAlreadyExistsException;
import com.azmath.hms.common.exceptions.ResourceNotFoundException;
import com.azmath.hms.models.RoomAmenity;
import com.azmath.hms.repositories.RoomAmenityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@Service
public class RoomAmenityService {

    @Autowired
    private RoomAmenityRepository  roomAmenityRepository;

    @GetMapping
    public Page<RoomAmenity> search(Pageable page) {
        return roomAmenityRepository.findAll(page);
    }

    public RoomAmenity save(RoomAmenity roomAmenity) {

        if(roomAmenityRepository.findByRoomAndAmenity(roomAmenity.getRoom(), roomAmenity.getAmenity()).isPresent()){
            throw  new ResourceAlreadyExistsException("roomAmenity.create.already.exist.for.given.room.and.amenity", String.valueOf(roomAmenity.getId()));
        }
        return roomAmenityRepository.save(roomAmenity);

    }

    public RoomAmenity update(RoomAmenity roomAmenity) {

        if(roomAmenityRepository.findByRoomAndAmenityAndIdNot(roomAmenity.getRoom(), roomAmenity.getAmenity(), roomAmenity.getId()).isPresent()){
            throw  new ResourceAlreadyExistsException("roomAmenity.update.already.exist.for.given.room.and.amenity.on.a.different.id", String.valueOf(roomAmenity.getId()));
        }
        return roomAmenityRepository.save(roomAmenity);
    }

    public RoomAmenity findById(Integer id) {
        Optional<RoomAmenity> optionalRoomAmenity = roomAmenityRepository.findById(id);
        if(!optionalRoomAmenity.isPresent()){
            throw new ResourceNotFoundException("roomAmenity.not.found", String.valueOf(id));
        }
        return optionalRoomAmenity.get();
    }

    public void delete(Integer id) {
        roomAmenityRepository.delete(findById(id));
    }



}
