package com.azmath.hms.api.v1;

import com.azmath.hms.api.v1.model.vo.HotelVO;
import com.azmath.hms.common.exceptions.ResourceConflictException;
import com.azmath.hms.models.Hotel;
import com.azmath.hms.services.HotelService;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.Instant;

@RestController
@RequestMapping("/api/v1/hotels")
public class HotelResource {

    @Autowired
    private HotelService hotelService;


    @GetMapping
    public Page<HotelVO> search(Pageable page) {
        Page<Hotel> hotelPage = hotelService.search(page);
        Page<HotelVO>  hotelVOPage = hotelPage.map(this::buildVO);
        return hotelVOPage ;
    }

    @PostMapping
    public ResponseEntity<Hotel> save(@Valid @RequestBody HotelVO hotelVO) {
        Hotel hotel = build(new Hotel(), hotelVO);
        hotel = hotelService.save(hotel);
        return ResponseEntity.ok(hotel);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Hotel> update(@PathVariable(name = "id") int id, @RequestBody HotelVO hotelVO) {

        if(id != hotelVO.getId()) {
            throw new ResourceConflictException("hotel.update.id.does.not.match", String.valueOf(id));
        }
        Hotel hotel = hotelService.findById(id);
        hotel = build(hotel, hotelVO);
        hotel = hotelService.update(hotel);
        return ResponseEntity.ok(hotel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable(name = "id") String id) {
        hotelService.delete(Integer.parseInt(id));
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity findById (@PathVariable(name="id") int id) {
        Hotel hotel = hotelService.findById(id);
        if(ObjectUtils.isEmpty(hotel)){

        }
        return null;
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
