package miw.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;
    @Bean
    public Step stepCleanTokens() {
        return stepBuilderFactory.get("stepCleanTokens").tasklet(tasklet()).allowStartIfComplete(true).build();
    }

    @Bean
    public Job job(Step stepCleanTokens) throws Exception {
        return jobBuilderFactory.get("jobToken").incrementer(new RunIdIncrementer()).start(stepCleanTokens).build();
    }

    @Bean
    public Tasklet tasklet() {
        // Metodo necesario para que Spring reconozca el NEW TokenControllerTasklet
        return new TokenBatchTasklet();
    }
}
