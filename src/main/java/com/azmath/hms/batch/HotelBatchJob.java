package com.azmath.hms.batch;

import com.azmath.hms.api.v1.model.vo.HotelVO;
import com.azmath.hms.batch.processor.HotelBatchProcessor;
import com.azmath.hms.batch.reader.HotelBatchReader;
import com.azmath.hms.batch.writer.HotelBatchWriter;
import com.azmath.hms.models.Hotel;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Component;




@Component
public class HotelBatchJob extends JobExecutionListenerSupport {
    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Value("${input.file}")
    private Resource resource;

    @Value("${max-threads}")
    private int maxThreads;

    @Autowired
    private HotelBatchProcessor batchProcessor;

    @Autowired
    private HotelBatchWriter batchWriter;

    @Bean(name = "hotelBatchUploadJob")
    public Job hotelBatchJob() {

        Step step = stepBuilderFactory.get("step-1").<HotelVO, Hotel>chunk(2)
                .reader(new HotelBatchReader(resource))
                .processor(batchProcessor)
                .writer(batchWriter)
                .build();

        Job job = jobBuilderFactory.get("hotel-batch-job")
                .incrementer(new RunIdIncrementer())
                .listener(this)
                .start(step)
                .build();

        return job;
    }


    @Bean
    public TaskExecutor taskExecutor() {
        SimpleAsyncTaskExecutor taskExecutor = new SimpleAsyncTaskExecutor();
        taskExecutor.setConcurrencyLimit(maxThreads);
        return taskExecutor;
    }


    @Override
    public void afterJob(JobExecution jobExecution) {
        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            System.out.println("BATCH JOB COMPLETED SUCCESSFULLY");
        }
    }


}
