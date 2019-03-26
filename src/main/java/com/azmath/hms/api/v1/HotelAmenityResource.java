package com.azmath.hms.api.v1;

import com.azmath.hms.api.v1.model.vo.HotelAmenityVO;
import com.azmath.hms.models.*;
import com.azmath.hms.services.AmenityService;
import com.azmath.hms.services.HotelAmenityService;
import com.azmath.hms.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/hotel-amenities")
public class HotelAmenityResource {

    @Autowired
    private HotelAmenityService hotelAmenityService;
    @Autowired
    private HotelService hotelService;
    @Autowired
    private AmenityService amenityService;


    @GetMapping
    public Page<HotelAmenityVO> search(Pageable page) {
        Page<HotelAmenity> hotelAmenityPage = hotelAmenityService.search(page);
        Page<HotelAmenityVO> roomAmenityVOPage = hotelAmenityPage.map(this::buildVO);
        return roomAmenityVOPage;
    }

    @PostMapping
    public ResponseEntity save(@RequestBody HotelAmenityVO hotelAmenityVO) {
        HotelAmenity hotelAmenity = build(new HotelAmenity(), hotelAmenityVO);
        hotelAmenity = hotelAmenityService.save(hotelAmenity);
        return ResponseEntity.ok(hotelAmenity);
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable(name = "id") String id,@RequestBody HotelAmenityVO hotelAmenityVO) {
        HotelAmenity hotelAmenity = hotelAmenityService.findById(Integer.parseInt(id));
        hotelAmenity = build(hotelAmenity, hotelAmenityVO);
        hotelAmenity = hotelAmenityService.save(hotelAmenity);
        return ResponseEntity.ok(hotelAmenity);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable(name = "id") String id) {
        try{
            hotelAmenityService.delete(Integer.parseInt(id));
            return  ResponseEntity.noContent().build();

        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    private HotelAmenity build(HotelAmenity target, HotelAmenityVO source) {
        target.setId(source.getId());

        Hotel hotel = hotelService.findById(source.getHotelId());
        target.setHotel(hotel);

        Amenity amenity = amenityService.findById(source.getAmenityId());
        target.setAmenity(amenity);
        target.setAmount(source.getAmount());
        target.setChargable(source.isChargable());

        return target;
    }

    private HotelAmenityVO buildVO(HotelAmenity source) {

        HotelAmenityVO hotelAmenityVO = new HotelAmenityVO();
        hotelAmenityVO.setId(source.getId());
        hotelAmenityVO.setAmenityId(source.getAmenity().getId());
        hotelAmenityVO.setAmenityName(source.getAmenity().getName());
        hotelAmenityVO.setHotelId(source.getHotel().getId());
        hotelAmenityVO.setHotelName(source.getHotel().getName());
        hotelAmenityVO.setAmount(source.getAmount());
        hotelAmenityVO.setChargable(source.getChargable());

        return hotelAmenityVO;
    }


}
