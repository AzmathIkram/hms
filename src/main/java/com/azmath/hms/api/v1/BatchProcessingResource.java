package com.azmath.hms.api.v1;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RestController
@RequestMapping("/api/v1/batch")
public class BatchProcessingResource {

    @Autowired
    private JobLauncher jobLauncher;


    @Autowired
    @Qualifier("hotelBatchUploadJob")
    Job hotelBatchUploadJob;

    @GetMapping("/upload/hotels")
    public ResponseEntity uploadHotels() throws Exception {
        JobParameters jobParameters = new JobParametersBuilder()
                .addString("source", Long.toString(Instant.now().toEpochMilli()))
                .toJobParameters();
        jobLauncher.run(hotelBatchUploadJob, jobParameters);
      return ResponseEntity.ok().build();
    }
}
