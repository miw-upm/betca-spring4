package miw.batch;

import org.apache.log4j.Logger;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ServiceBatch {
    
    // Segundo, Minuto, Hora, Día del Mes, Mes, Día (s) de la semana (*) Significa que coincide con cualquier valor / X significa "cada X" ?
   public static final String EJECUCION_CADA_MINUTO = "0 * * * * *";

   public static final String EJECUCION_A_MEDIA_NOCHE = "0 0 0 * * *";

    @Autowired
    Job job;

    @Autowired
    JobLauncher jobLauncher;

    @Scheduled(cron = EJECUCION_A_MEDIA_NOCHE)
    public void launch() throws JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException,
            JobParametersInvalidException {

        JobParameters jobParameters = new JobParametersBuilder().addLong("time", System.currentTimeMillis()).toJobParameters();
        JobExecution execution = jobLauncher.run(job, jobParameters);
        Logger.getLogger("miw.batch").info("Exit status: " + execution.getStatus() + " ");
    }

}
