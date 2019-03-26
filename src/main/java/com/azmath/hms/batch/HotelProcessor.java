package com.azmath.hms.processor;

import com.azmath.hms.api.v1.model.vo.HotelVO;
import com.azmath.hms.models.Hotel;
import org.springframework.batch.item.ItemProcessor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HotelProcessor implements ItemProcessor<HotelVO, Hotel> {

    private static final Logger log = LoggerFactory.getLogger(HotelProcessor.class);

    @Override
    public Hotel process(HotelVO item) throws Exception {

        Hotel target = new Hotel();

        target.setId(item.getId());
        target.setName(item.getName());
        target.setDescription(item.getDescription());
        target.setCityCode(item.getCityCode());

        log.info("building hotel entity");

        return target;
    }
}
