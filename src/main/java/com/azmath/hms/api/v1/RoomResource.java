package com.azmath.hms.api.v1;

import com.azmath.hms.api.v1.model.vo.RoomVO;
import com.azmath.hms.common.exceptions.ResourceConflictException;
import com.azmath.hms.models.Hotel;
import com.azmath.hms.models.Room;
import com.azmath.hms.services.HotelService;
import com.azmath.hms.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/v1/rooms")
public class RoomResource {

    @Autowired
    private RoomService roomService;
    @Autowired
    private HotelService hotelService;

    @GetMapping
    public Page<RoomVO> search(Pageable page) {
        Page<Room> roomPage = roomService.search(page);
        Page<RoomVO> roomVOPage = roomPage.map(this::buildVO);
        return roomVOPage;
    }

    @PostMapping
    public ResponseEntity<Room> save(@Valid @RequestBody RoomVO roomVO) {
        Room room = build(new Room(), roomVO);
        room = roomService.save(room);
        return ResponseEntity.ok(room);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Room> update(@PathVariable(name = "id") int id, @Valid @RequestBody RoomVO roomVO) {

        if(id != roomVO.getId()) {
            throw new ResourceConflictException("room.update.id.does.not.match", String.valueOf(id));
        }

        Room room = roomService.findById(id);
        room = build(room, roomVO);
        room = roomService.save(room);
        return ResponseEntity.ok(room);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable(name = "id") String id) {
        try {
            roomService.delete(Integer.parseInt(id));
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    private RoomVO buildVO(Room source){
        RoomVO target = new RoomVO();
        target.setId(source.getId());
        target.setDescription(source.getDescription());
        Hotel hotel = source.getHotel();
        target.setHotelId(hotel.getId());
        return target;
    }

    private Room build(Room target, RoomVO source) {
        target.setId(source.getId());
        target.setDescription(source.getDescription());

        Hotel hotel = hotelService.findById(source.getHotelId());
        target.setHotel(hotel);
        return target;
    }
}
