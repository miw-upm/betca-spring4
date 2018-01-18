package miw.injection;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
// por defecto, se busca en el raiz de src/main/resources/application.properties
// @PropertySource("classpath:miw.properties")
// @PropertySource("file:///C:/JBB/miw.properties") // En la ruta indicada
// @PropertySource("http://myserver/application.properties"). En la URL
// @ConfigurationProperties(prefix = "miw"), para no escribir el prefijo
public class InjectionConfig {

    @Bean
    @Scope("prototype") // Por defecto singleton
    public PrototypeInjectoOnlyMessageService prototypeInjectoOnlyMessageService() {
        Logger.getLogger("BETCA-spring." + this.getClass().getSimpleName())
                .info(">>>> se crea el Bean: PrototypeInjectoOnlyMessageService");
        return new PrototypeInjectoOnlyMessageService();
    }

}
