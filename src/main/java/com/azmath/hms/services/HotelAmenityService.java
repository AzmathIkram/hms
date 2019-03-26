package com.azmath.hms.services;

import com.azmath.hms.models.HotelAmenity;
import com.azmath.hms.models.RoomAmenity;
import com.azmath.hms.repositories.HotelAmenityRepository;
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
    private HotelAmenityRepository hotelAmenityRepository;

    @GetMapping
    public Page<HotelAmenity> search(Pageable page) {
        return hotelAmenityRepository.findAll(page);
    }

    public HotelAmenity save(HotelAmenity hotelAmenity) {
        return hotelAmenityRepository.save(hotelAmenity);

    }

    public HotelAmenity findById(Integer id) {
        Optional<HotelAmenity> optionalHotelAmenity = hotelAmenityRepository.findById(id);
        return optionalHotelAmenity.isPresent() ? optionalHotelAmenity.get() : null;
    }

    public void delete(Integer id) {
        hotelAmenityRepository.delete(findById(id));
    }



}
