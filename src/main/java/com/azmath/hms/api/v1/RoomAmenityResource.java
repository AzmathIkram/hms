package com.azmath.hms.api.v1;


import com.azmath.hms.api.v1.model.vo.RoomAmenityVO;
import com.azmath.hms.models.Amenity;
import com.azmath.hms.models.Room;
import com.azmath.hms.models.RoomAmenity;
import com.azmath.hms.services.AmenityService;
import com.azmath.hms.services.RoomAmenityService;
import com.azmath.hms.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/room-amenities")
public class HotelAmenityResource {

    @Autowired
    private RoomAmenityService roomAmenityService;
    @Autowired
    private RoomService roomService;
    @Autowired
    private AmenityService amenityService;

    @PostMapping
    public ResponseEntity save(@RequestBody RoomAmenityVO roomAmenityVO) {
        RoomAmenity roomAmenity = build(new RoomAmenity(), roomAmenityVO);
        roomAmenity = roomAmenityService.save(roomAmenity);
        return ResponseEntity.ok(roomAmenity);
    }

    private RoomAmenity build(RoomAmenity target, RoomAmenityVO source) {
        target.setId(source.getId());

        Room room = roomService.findById(source.getRoomId());
        target.setRoom(room);
        Amenity amenity = amenityService.findById(source.getAmenityId());
        target.setAmenity(amenity);
        target.setAmount(source.getAmount());
        target.setChargable(source.isChargable());

        return target;
    }
}
