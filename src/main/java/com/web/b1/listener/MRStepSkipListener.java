package com.web.b1.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.SkipListener;
import org.springframework.stereotype.Component;

@Component
public class MRStepSkipListener implements SkipListener<String, Number> {

    private static final Logger logger = LoggerFactory.getLogger(MRStepSkipListener.class);

    @Override
    public void onSkipInRead(Throwable t) {
        logger.info("onSkipInRead");
    }

    @Override
    public void onSkipInWrite(Number item, Throwable t) {
        logger.info("onSkipInWrite");
    }

    @Override
    public void onSkipInProcess(String item, Throwable t) {
        logger.info("onWriteError");
    }
}
