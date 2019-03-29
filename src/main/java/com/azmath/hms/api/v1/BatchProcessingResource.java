package com.azmath.hms.api.v1;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/batch")
public class BatchProcessingResource {

    @GetMapping("/upload/hotels")
    public ResponseEntity uploadHotels(){
      return ResponseEntity.ok().build();
    }
}
