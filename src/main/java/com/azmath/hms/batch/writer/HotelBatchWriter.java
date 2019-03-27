package com.azmath.hms.batch.writer;


import com.azmath.hms.models.Hotel;
import com.azmath.hms.services.HotelService;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class HotelBatchWriter implements ItemWriter<Hotel> {

    @Autowired
    private HotelService hotelService;

    @Override
    @Transactional
    public void write(List<? extends Hotel> items) throws Exception {
        hotelService.saveAll((List<Hotel>) items);
    }
}
