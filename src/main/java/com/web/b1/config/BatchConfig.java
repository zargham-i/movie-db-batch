package com.web.b1.config;


import com.web.b1.items.MovieRowReader;
import com.web.b1.items.MovieRowWriter;
import com.web.b1.listener.MRJobExecutionListener;
import com.web.b1.listener.MRStepSkipListener;
import com.web.b1.model.MovieRow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableBatchProcessing
public class BatchConfig {
    private static final Logger logger = LoggerFactory.getLogger(BatchConfig.class);

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    PlatformTransactionManager transactionManager;

    @Autowired
    MRJobExecutionListener mrJobExecutionListener;

    //@Autowired
    //InterceptingStepExecution interceptingStep;

    @Autowired
    JobLauncher jobLauncher;

    @Bean
    public ItemReader<MovieRow> movieRowReader() {
        return new MovieRowReader();
    }

    @Bean
    public ItemWriter<MovieRow> movieRowWriter() {
        return new MovieRowWriter();
    }

    @Bean Step step1() {
        return stepBuilderFactory.get("step1").<MovieRow, MovieRow>chunk(1000)
                .reader(movieRowReader())
                .writer(movieRowWriter())
                .faultTolerant()
                .skip(Exception.class)
                .noRetry(Exception.class)
                .noRollback(Exception.class)
                .skipLimit(10000)
                .listener(new MRStepSkipListener())
                .transactionManager(transactionManager)
                .build();
    }

    @Bean
    public Job movieRowParseJob() {
        return jobBuilderFactory
                .get("movieRowParseJob")
                .incrementer(new RunIdIncrementer())
                .flow(step1())
                .end()
                .listener(mrJobExecutionListener)
                .build();
    }

    @Bean(name = "movieRowParse-JobLauncher")
    public void run() {
        try {
            JobParameters jobParameters = new JobParametersBuilder()
                    .addLong("time", System.currentTimeMillis())
                    .toJobParameters();
            JobExecution execution = jobLauncher.run(movieRowParseJob(), jobParameters);
            logger.info("JobExecution Status : " + execution.getStatus());

        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }
}