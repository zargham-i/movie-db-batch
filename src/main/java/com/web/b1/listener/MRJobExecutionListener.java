package com.web.b1.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

@Component
public class MRJobExecutionListener implements JobExecutionListener {

    private static final Logger logger = LoggerFactory.getLogger(MRJobExecutionListener.class);

    public void beforeJob(JobExecution jobExecution) {
        try {
            logger.info("job is about to begin");
        } catch(Exception e) {
            logger.error("job failed");
        }
    }

    public void afterJob(JobExecution jobExecution) {
        try {
            logger.info("job finished");
        } catch(Exception e) {
            logger.error("job error");
            System.exit(1);
        }
    }

}
