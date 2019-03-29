package com.azmath.hms.services;

import com.azmath.hms.common.exceptions.ResourceAlreadyExistsException;
import com.azmath.hms.common.exceptions.ResourceConflictException;
import com.azmath.hms.common.exceptions.ResourceNotFoundException;
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

        if(amenityRepository.findByName(amenity.getName()).isPresent()){
            throw new ResourceAlreadyExistsException("amenity.create.name.already.exist", amenity.getName());
        }

        return amenityRepository.save(amenity);
    }

    public Amenity update(Amenity amenity) {

        if(amenityRepository.findByNameAndIdNot(amenity.getName(), amenity.getId()).isPresent()) {
         throw new ResourceConflictException("amenity.update.name.exist.for.different.id", amenity.getName());
        }

        return amenityRepository.save(amenity);
    }

    public Amenity findById(Integer id){
        Optional<Amenity> optionalAmenity = amenityRepository.findById(id);

        if(!optionalAmenity.isPresent()) {
            throw new ResourceNotFoundException("amenity.not.found", String.valueOf(id));
        }

        return optionalAmenity.get();
    }

    public void delete(Integer id){
        amenityRepository.delete(findById(id));
    }



}
