package com.azmath.hms.config;

import com.azmath.hms.api.v1.model.vo.HotelVO;
import com.azmath.hms.batch.JobCompletionNotificationListener;
import com.azmath.hms.models.Hotel;
import com.azmath.hms.batch.HotelProcessor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;


import javax.sql.DataSource;

@EnableBatchProcessing
@Configuration
public class BatchProcessingConfig {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    public DataSource dataSource;

    @Bean
    public FlatFileItemReader<HotelVO> hotelReader() {

        FlatFileItemReader<HotelVO> reader = new FlatFileItemReader<>();
        reader.setResource(new FileSystemResource("input/hotels.csv"));
        reader.setLineMapper(new DefaultLineMapper<HotelVO>() {{
            setLineTokenizer(new DelimitedLineTokenizer() {{
                setNames(new String[] { "id", "name", "description", "cityCode" });
            }});
            setFieldSetMapper(new BeanWrapperFieldSetMapper<HotelVO>() {{
                setTargetType(HotelVO.class);
            }});
        }});
        return reader;

    }

    @Bean
    public ItemProcessor<HotelVO, Hotel> hotelProcessor() {
        return new HotelProcessor();
    }

    @Bean
    public JdbcBatchItemWriter<Hotel> hotelWriter() {
        JdbcBatchItemWriter<Hotel> csvHotelWriter = new JdbcBatchItemWriter<>();
        csvHotelWriter.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
        csvHotelWriter.setSql("INSERT INTO hotels ( name, description, city_code) VALUES (:name, :description, :cityCode)");
        csvHotelWriter.setDataSource(dataSource);
        return csvHotelWriter;
    }

    @Bean
    public Step csvFileToDatabaseStep() {
        return stepBuilderFactory.get("csvFileToDatabaseStep")
                .<HotelVO, Hotel>chunk(1)
                .reader(hotelReader())
                .processor(hotelProcessor())
                .writer(hotelWriter())
                .build();
    }

    @Bean
    Job csvFileToDatabaseJob(JobCompletionNotificationListener listener) {
        return jobBuilderFactory.get("csvFileToDatabaseJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(csvFileToDatabaseStep())
                .end()
                .build();
    }
}
