package miw;

import java.util.concurrent.Future;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import miw.injection.InjectionMain;
import miw.scheduling_asynchronous.SchedulingAsync;

@SpringBootApplication
public class Application implements CommandLineRunner {
    // http://localhost:8080/h2-console...JDBC URL: jdbc:h2:mem:testdb

    @Autowired
    private InjectionMain mainInjection;

    @Autowired
    private SchedulingAsync async;

    // Nos permite testear aspectos puntualesr+
    @Override
    public void run(String... args) {
        if (args.length > 0) {
            if (args[0].equals("injection")) { // mvn clean spring-boot:run -Drun.arguments="injection"
                this.mainInjection.debugAndClose();
            } else if (args[0].equals("async")) { // mvn clean spring-boot:run -Drun.arguments="async"
                Logger.getLogger("miw.Asynchronous").info("Antes de llamada asincrona...");
                Future<String> future = this.async.asyncMethodWithReturnType();
                Logger.getLogger("miw.Asynchronous").info("... Despues de llamada asincrona, future:" + future.isDone());
            }
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
