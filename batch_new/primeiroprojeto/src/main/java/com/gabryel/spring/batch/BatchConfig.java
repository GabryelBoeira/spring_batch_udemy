package com.gabryel.spring.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.Collections;

@Configuration
public class BatchConfig {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;

    public BatchConfig(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        this.jobRepository = jobRepository;
        this.transactionManager = transactionManager;
    }

    @Bean
    Job arquivoDelimitadoJob(Step leituraArquivoDelimitadoStep) {
        return new JobBuilder("arquivoDelimitadoJob", jobRepository)
                .start(leituraArquivoDelimitadoStep)
                .build();
    }

    @Bean
    Step leituraArquivoDelimitadoStep() {
        return new StepBuilder("leituraArquivoDelimitadoStep", jobRepository)
                .<String, String>chunk(1, transactionManager)
                .reader(new ListItemReader<>(Collections.singletonList("Hello World!")))
                .processor(item -> item)
                .writer(items -> items.forEach(System.out::println))
                .build();
    }

}
