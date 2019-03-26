package com.azmath.hms.services;

import com.azmath.hms.models.Amenity;
import com.azmath.hms.repositories.AmenityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AmenityService {

    @Autowired
    private AmenityRepository amenityRepository;

    public Page<Amenity> search(Pageable page) {
        return amenityRepository.findAll(page);
    }

    public Amenity save(Amenity amenity) {
        return amenityRepository.save(amenity);
    }

    public Amenity findById(Integer id){
        Optional<Amenity> optionalAmenity = amenityRepository.findById(id);
        return optionalAmenity.isPresent() ? optionalAmenity.get() : null;
    }

    public void delete(Integer id){
        amenityRepository.delete(findById(id));
    }



}
