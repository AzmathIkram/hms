package com.azmath.hms.services;

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
        return optionalRoom.isPresent() ? optionalRoom.get() : null;
    }

    public Room save(Room room) {
        return roomRepository.save(room);
    }

    public void delete(Integer id) {
        roomRepository.delete(findById(id));
    }

    public List<Room> findAll(){
        return IteratorUtils.toList(roomRepository.findAll().iterator());
    }

}
