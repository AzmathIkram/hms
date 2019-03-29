package com.azmath.hms.api.v1;

import com.azmath.hms.api.v1.model.vo.AmenityVO;
import com.azmath.hms.common.exceptions.InvalidRequestParameterException;
import com.azmath.hms.models.Amenity;
import com.azmath.hms.services.AmenityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/v1/amenities")
public class AmenityResource {

    @Autowired
    private AmenityService amenityService;

    @GetMapping
    public Page<AmenityVO> search(Pageable page) {
        Page<Amenity> amenityPage = amenityService.search(page);
        Page<AmenityVO> amenityVOPage = amenityPage.map(this::buildVO);
        return amenityVOPage;
    }

    @PostMapping
    public ResponseEntity save(@Valid @RequestBody AmenityVO amenityVO) {
        Amenity amenity = build(new Amenity(), amenityVO);
        amenity = amenityService.save(amenity);
        return ResponseEntity.ok(amenity);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Amenity> update(@PathVariable(name = "id") int id, @Valid @RequestBody AmenityVO amenityVO) {

        if(id != amenityVO.getId()) {
            throw new InvalidRequestParameterException("amenity.update.id.does.not.match", String.valueOf(id));
        }

        Amenity amenity = amenityService.findById(id);
        build(amenity, amenityVO);
        amenity = amenityService.save(amenity);
        return ResponseEntity.ok(amenity);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable(name = "id") String id) {
        try{
            amenityService.delete(Integer.parseInt(id));
            return ResponseEntity.ok().build();

        } catch (Exception e) {
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    public Amenity build(Amenity target, AmenityVO source) {
        target.setId(source.getId());
        target.setName(source.getName());
        target.setDescription(source.getDescription());
        return target;
    }

    private AmenityVO buildVO(Amenity source) {
        AmenityVO target = new AmenityVO();
        target.setId(source.getId());
        target.setName(source.getName());
        target.setDescription(source.getDescription());
        return target;
    }
}
