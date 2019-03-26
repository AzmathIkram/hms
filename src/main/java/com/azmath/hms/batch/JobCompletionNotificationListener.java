package com.azmath.hms.batch;


import com.azmath.hms.models.Hotel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {

    private static final Logger log = LoggerFactory.getLogger(JobCompletionNotificationListener.class);

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JobCompletionNotificationListener(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        if(jobExecution.getStatus() == BatchStatus.COMPLETED) {
            log.info("============ JOB FINISHED ============ Verifying the results....\n");

            List<Hotel> results = jdbcTemplate.query("SELECT id, name, description, city_code FROM hotels", new RowMapper<Hotel>() {
                @Override
                public Hotel mapRow(ResultSet rs, int row) throws SQLException {
                    return new Hotel(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getNString(4));
                }
            });

            for (Hotel hotel : results) {
                log.info("Discovered <" + hotel + "> in the database.");
            }

        }
    }
}
