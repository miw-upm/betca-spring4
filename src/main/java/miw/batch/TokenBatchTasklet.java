package miw.batch;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

public class TokenBatchTasklet implements Tasklet {

    @Override
    public RepeatStatus execute(StepContribution arg0, ChunkContext arg1) throws Exception {
        // LogManager.getLogger().info("Ejecutando task de limpieza de tokens");

        // ACCIÓN A EJECUTAR SEGÚN LA PROGRAMACIÓN

        return RepeatStatus.FINISHED;
    }

}
