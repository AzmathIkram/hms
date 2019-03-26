package com.azmath.hms.api.v1;


import com.azmath.hms.api.v1.model.vo.HotelAmenityVO;
import com.azmath.hms.api.v1.model.vo.RoomAmenityVO;
import com.azmath.hms.models.Amenity;
import com.azmath.hms.models.HotelAmenity;
import com.azmath.hms.models.Room;
import com.azmath.hms.models.RoomAmenity;
import com.azmath.hms.services.AmenityService;
import com.azmath.hms.services.RoomAmenityService;
import com.azmath.hms.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/room-amenities")
public class RoomAmenityResource {

    @Autowired
    private RoomAmenityService roomAmenityService;
    @Autowired
    private RoomService roomService;
    @Autowired
    private AmenityService amenityService;

    @GetMapping
    public Page<RoomAmenityVO> search(Pageable page) {
        Page<RoomAmenity> roomAmenityPage = roomAmenityService.search(page);
        Page<RoomAmenityVO> roomAmenityVOPage = roomAmenityPage.map(this::buildVO);
        return roomAmenityVOPage;
    }

    @PostMapping
    public ResponseEntity save(@RequestBody RoomAmenityVO roomAmenityVO) {
        RoomAmenity roomAmenity = build(new RoomAmenity(), roomAmenityVO);
        roomAmenity = roomAmenityService.save(roomAmenity);
        return ResponseEntity.ok(roomAmenity);
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable(name = "id") String id,@RequestBody RoomAmenityVO roomAmenityVO) {
        RoomAmenity roomAmenity = roomAmenityService.findById(Integer.parseInt(id));
        roomAmenity = build(roomAmenity, roomAmenityVO);
        roomAmenity = roomAmenityService.save(roomAmenity);
        return ResponseEntity.ok(roomAmenity);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable(name = "id") String id) {
        try{
            roomAmenityService.delete(Integer.parseInt(id));
            return  ResponseEntity.noContent().build();

        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
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

    private RoomAmenityVO buildVO(RoomAmenity source) {

        RoomAmenityVO roomAmenityVO = new RoomAmenityVO();
        roomAmenityVO.setId(source.getId());
        roomAmenityVO.setAmenityId(source.getAmenity().getId());
        roomAmenityVO.setAmenity(source.getAmenity().getName());
        roomAmenityVO.setRoomId(source.getRoom().getId());
        roomAmenityVO.setHotelName(source.getRoom().getHotel().getName());
        roomAmenityVO.setAmount(source.getAmount());
        roomAmenityVO.setChargable(source.getChargable());

        return roomAmenityVO;
    }
}
