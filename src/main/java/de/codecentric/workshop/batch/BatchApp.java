package de.codecentric.workshop.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.task.configuration.EnableTask;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableTask
@EnableBatchProcessing
public class BatchApp {

    public static void main(String[] args) {
        SpringApplication.run(BatchApp.class, args);
    }

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private String[] input = new String[] {"I", "am", "Batch", "program", "!"};
    private int pos = 0;

    @Autowired
    public BatchApp(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean
    public Job testJob() {
        return jobBuilderFactory.get("testJob")
                .start(testStep()).build();
    }

    @Bean
    public ItemReader<String> dummyReader() {
        return ()->pos < input.length ? input[pos++] : null;
    }

    private Step testStep() {
        return stepBuilderFactory
                .get("testStep")
                .<String, String>chunk(4)
                .reader(dummyReader())
                .writer(dummyWriter())
                .build();
    }

    private ItemWriter<String> dummyWriter() {
        return System.out::println;
    }
}
