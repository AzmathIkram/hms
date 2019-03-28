package com.azmath.hms.services;

import com.azmath.hms.common.exceptions.ResourceAlreadyExistsException;
import com.azmath.hms.common.exceptions.ResourceNotFoundException;
import com.azmath.hms.models.Hotel;
import com.azmath.hms.repositories.HotelRepository;
import org.apache.commons.collections.IteratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Optional;


@Service
public class HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    public Page<Hotel> search(Pageable page) {
        return hotelRepository.findAll(page);
    }

    public Hotel findById(Integer id) {

        Optional<Hotel> optionalHotel = hotelRepository.findById(id);
        if(!optionalHotel.isPresent()) {
            throw  new ResourceNotFoundException("hotel.not.found", String.valueOf(id));
        }
        return optionalHotel.get();
    }

    public Hotel findByName(String name) {

        Hotel hotel = hotelRepository.findByName(name);
        if(ObjectUtils.isEmpty(hotel)) {
            throw  new ResourceNotFoundException("hotel.not.found", String.valueOf(name));
        }
        return hotel;
    }

    public Hotel save(Hotel hotel) {

        if(hotelRepository.findOneByName(hotel.getName()).isPresent()){
            throw  new ResourceAlreadyExistsException("hotel.create.name.already.exist", hotel.getName());
        }
        return hotelRepository.save(hotel);
    }

    public Hotel update(Hotel hotel) {

        if(hotelRepository.findOneByNameAndIdNot(hotel.getName(), hotel.getId()).isPresent()){
            throw  new ResourceAlreadyExistsException("hotel.update.name.already.exist", hotel.getName());
        }
        return hotelRepository.save(hotel);
    }

    public void saveAll(List<Hotel> hotelList) {
        hotelRepository.saveAll(hotelList);
    }

    public void delete(Integer id) {
        hotelRepository.delete(findById(id));
    }

    public List<Hotel> findAll(){
        return IteratorUtils.toList(hotelRepository.findAll().iterator());
    }

}
