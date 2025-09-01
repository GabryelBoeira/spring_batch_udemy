package com.udemy.primeiroprojetospringbatch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableBatchProcessing
@Configuration
public class PrimeiroJobSpringBatchConfig {

	private final JobBuilderFactory jobBuilderFactory;
	private final StepBuilderFactory stepBuilderFactory;

	@Autowired
	public PrimeiroJobSpringBatchConfig(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) {
		this.jobBuilderFactory = jobBuilderFactory;
		this.stepBuilderFactory = stepBuilderFactory;
	}

    @Bean
	public Job imprimeOlaJob() {
		return jobBuilderFactory
				.get("imprimeOlaJob")
				.start(imprimeOlaStep())
				.build();
	}
	
	public Step imprimeOlaStep() {
		return stepBuilderFactory
				.get("imprimeOlaStep")
				.tasklet((contribution, chunkContext) -> {
                    System.out.println("Olá, mundo!");
                    return RepeatStatus.FINISHED;
                })
				.build();
	}

}
