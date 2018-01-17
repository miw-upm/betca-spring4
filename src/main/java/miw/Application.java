package miw;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import miw.aspect.AspectMain;
import miw.injection.InjectionMain;

@SpringBootApplication
public class Application implements CommandLineRunner {

    @Autowired
    private InjectionMain mainInjection;

    @Autowired
    private AspectMain aspectMain;

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedMethods("*").allowedOrigins("*").maxAge(3600);
            }
        };
    }
   
    
    //Nos permite testear aspectos puntualesr+
    @Override
    public void run(String... args) {
        // mvn clean spring-boot:run -Drun.arguments="injection"
        // http://localhost:8080/h2-console...JDBC URL: jdbc:h2:mem:testdb
        if (args.length > 0) {
            if (args[0].equals("injection")) {
                this.mainInjection.debugAndClose();
            } else if (args[0].equals("aspect")) {
                this.aspectMain.debugAndClose();
            }
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
