package com.azmath.hms.api.v1;

import com.azmath.hms.api.v1.model.vo.HotelVO;
import com.azmath.hms.models.Hotel;
import com.azmath.hms.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/hotels")
public class HotelResource {

    @Autowired
    private HotelService hotelService;



    @GetMapping("/test")
    public ResponseEntity<String> test(){
        return ResponseEntity.ok("Hello");
    }

    @GetMapping
    public Page<HotelVO> search(Pageable page) {
        Page<Hotel> hotelPage = hotelService.search(page);
        Page<HotelVO>  hotelVOPage = hotelPage.map(this::buildVO);
        return hotelVOPage ;
    }

    @PostMapping
    public ResponseEntity<Hotel> save(@Valid @RequestBody HotelVO hotelVO) {
        try {
            Hotel hotel = build(new Hotel(), hotelVO);
            hotel = hotelService.save(hotel);
            return ResponseEntity.ok(hotel);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Hotel> update(@PathVariable(name = "id") String id, @RequestBody HotelVO hotelVO) {

        Hotel hotel = hotelService.findById(Integer.parseInt(id));
        hotel = build(hotel, hotelVO);
        hotelService.save(hotel);
        return ResponseEntity.ok(hotel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable(name = "id") String id) {
        hotelService.delete(Integer.parseInt(id));
        return ResponseEntity.noContent().build();
    }

    private HotelVO buildVO(Hotel source) {
        HotelVO target = new HotelVO();

        target.setId(source.getId());
        target.setName(source.getName());
        target.setDescription(source.getDescription());
        target.setCityCode(source.getCityCode());

        return target;

    }

    private Hotel build(Hotel target, HotelVO source) {
        target.setId(source.getId());
        target.setName(source.getName());
        target.setDescription(source.getDescription());
        target.setCityCode(source.getCityCode());
        return target;
    }
}
