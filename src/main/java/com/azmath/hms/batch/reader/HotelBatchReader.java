package com.azmath.hms.batch.reader;

import com.azmath.hms.api.v1.model.vo.HotelVO;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.core.io.Resource;

public class HotelBatchReader extends FlatFileItemReader<HotelVO> {

    public HotelBatchReader(Resource resource) {
        super();

        setResource(resource);

        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setNames(new String[] { "name", "description", "cityCode" });
        lineTokenizer.setDelimiter(",");
        lineTokenizer.setStrict(false);

        BeanWrapperFieldSetMapper<HotelVO> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(HotelVO.class);

        DefaultLineMapper<HotelVO> defaultLineMapper = new DefaultLineMapper<>();
        defaultLineMapper.setLineTokenizer(lineTokenizer);
        defaultLineMapper.setFieldSetMapper(fieldSetMapper);
        setLineMapper(defaultLineMapper);
    }
}
