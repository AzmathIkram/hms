package com.azmath.hms.services;

import com.azmath.hms.common.exceptions.ResourceAlreadyExistsException;
import com.azmath.hms.common.exceptions.ResourceNotFoundException;
import com.azmath.hms.models.Room;
import com.azmath.hms.repositories.RoomRepository;
import org.apache.commons.collections.IteratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

    public Page<Room> search(Pageable page) {
        return roomRepository.findAll(page);
    }

    public Room findById(Integer id) {
        Optional<Room> optionalRoom = roomRepository.findById(id);
        if(!optionalRoom.isPresent()){
            throw new ResourceNotFoundException("room.not.found", String.valueOf(id));
        }
        return optionalRoom.get();
    }

    public Room save(Room room) {
        if(roomRepository.findByDescriptionAndHotel(room.getDescription(), room.getHotel()).isPresent()){
            throw  new ResourceAlreadyExistsException("room.create.name.already.exist", room.getDescription());
        }
        return roomRepository.save(room);
    }

    public Room update(Room room) {

        if(roomRepository.findByDescriptionAndIdNotAndHotel(room.getDescription(), room.getId(), room.getHotel()).isPresent()) {
            throw  new ResourceAlreadyExistsException("room.update.name.already.exist", room.getDescription());
        }
        return roomRepository.save(room);
    }


    public void delete(Integer id) {
        roomRepository.delete(findById(id));
    }

    public List<Room> findAll(){
        return IteratorUtils.toList(roomRepository.findAll().iterator());
    }

}
