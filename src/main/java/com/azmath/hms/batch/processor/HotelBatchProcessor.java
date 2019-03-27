package com.azmath.hms.batch.processor;

import com.azmath.hms.api.v1.model.vo.HotelVO;
import com.azmath.hms.models.Hotel;
import com.azmath.hms.services.HotelService;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

@Component
public class HotelBatchProcessor implements ItemProcessor<HotelVO, Hotel> {

    @Autowired
    private HotelService hotelService;

    @Override
    public Hotel process(HotelVO source) throws Exception {

        Hotel target = null;

        //TODO
        //check if the source is valid, if not valid throw exception

        if(!StringUtils.isEmpty(source.getName())){
            target = hotelService.findByName(source.getName());

            if(ObjectUtils.isEmpty(target)) {
                target = new Hotel();
            }

            target.setName(source.getName());
            target.setDescription(source.getDescription());
            target.setCityCode(source.getCityCode());
        }

        return target;
    }
}
