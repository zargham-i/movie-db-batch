package com.web.b1.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;

public class MRStepExecutionListener implements StepExecutionListener {
    private static final Logger logger = LoggerFactory.getLogger(MRStepExecutionListener.class);

    @Override
    public void beforeStep(StepExecution stepExecution) {
        logger.info("beforeStep().");
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        logger.info("Called afterStep().");
        return ExitStatus.COMPLETED;
    }
}
