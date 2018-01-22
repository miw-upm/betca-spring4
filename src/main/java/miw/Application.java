package miw;

import java.util.concurrent.Future;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import miw.injection.InjectionMain;
import miw.persistence.mongo.Address;
import miw.persistence.mongo.Mobile;
import miw.persistence.mongo.MobileRepository;
import miw.persistence.mongo.Type;
import miw.persistence.mongo.User;
import miw.persistence.mongo.UserRepository;
import miw.persistence.mongo.Vehicle;
import miw.persistence.mongo.VehicleRepository;
import miw.scheduling_asynchronous.SchedulingAsync;

@SpringBootApplication
public class Application implements CommandLineRunner {
    // http://localhost:8080/h2-console...JDBC URL: jdbc:h2:mem:testdb

    @Autowired
    private InjectionMain mainInjection;

    @Autowired
    private SchedulingAsync async;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private MobileRepository mobileRepository;

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
            } else if (args[0].equals("mongo")) { // mvn clean spring-boot:run -Drun.arguments="mongo"
                this.userRepository.deleteAll();
                this.vehicleRepository.deleteAll();
                this.mobileRepository.deleteAll();
                Vehicle vehicle1 = new Vehicle("1234 mng");
                Vehicle vehicle2 = new Vehicle("4321 mng");
                this.vehicleRepository.save(vehicle1);
                this.vehicleRepository.save(vehicle2);
                User user1 = new User("uno", "unooossss", new Address("Madrid", "Gran Via"), vehicle1);
                Mobile mobile1 = new Mobile("666666666", Type.BASIC);
                Mobile mobile2 = new Mobile("111111111", Type.ADVANCE);
                user1.getMobileList().add(mobile1);
                user1.getMobileList().add(mobile2);
                mobileRepository.save(user1.getMobileList());
                this.userRepository.save(user1);
                this.userRepository.save(new User("dos", "doooossss", new Address("Madrid", "Gran Via"), vehicle2));
                Logger.getLogger("miw.mongo").info("... :userRepository.findAll()" + this.userRepository.findAll());
            }
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
