package com.azmath.hms.services;

import com.azmath.hms.models.Hotel;
import com.azmath.hms.repositories.HotelRepository;
import org.apache.commons.collections.IteratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
        return optionalHotel.isPresent() ? optionalHotel.get() : null;
    }

    public Hotel findByName(String name) {
        return hotelRepository.findByName(name);
    }

    public Hotel save(Hotel hotel) {
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

    public void  insetBatch() {

    }

}
