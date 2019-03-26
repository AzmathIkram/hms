package com.azmath.hms.config;

import com.azmath.hms.api.v1.model.vo.HotelVO;
import com.azmath.hms.models.Hotel;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@EnableBatchProcessing
@Configuration
public class BatchProcessingConfig {

    @Bean
    public FlatFileItemReader<HotelVO> csvHotelReader() {

        FlatFileItemReader<HotelVO> reader = new FlatFileItemReader<>();
        reader.setResource(new ClassPathResource("hotels.csv"));
        reader.setLineMapper(new DefaultLineMapper<HotelVO>() {{
            setLineTokenizer(new DelimitedLineTokenizer() {{
                setNames(new String[] { "id", "title", "description" });
            }});
            setFieldSetMapper(new BeanWrapperFieldSetMapper<HotelVO>() {{
                setTargetType(HotelVO.class);
            }});
        }});
        return reader;

    }


    @Bean
    public ItemProcessor<HotelVO, Hotel> hotelProcessor() {

    }
}
