package miw.injection;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

// @Service("messageService") o  @Service(value="messageService") por defecto
// @Scope("prototype") //Por defecto singleton
// @Scope(org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE)

@Service
public class SingletonMessageService {

    @Value("${miw.name}")
    private String name;

    private int counter = 1;

    @PostConstruct
    public void constructor() {
        Logger.getLogger("BETCA-spring." + this.getClass().getSimpleName()).info(">>>>>> SingletonMessageService:constructor()");
    }

    public String getMessage() {
        return this.counter++ + ": SingletonMessageService!!! " + this.name;
    }

    @PreDestroy
    public void destroy() {
        Logger.getLogger("BETCA-spring." + this.getClass().getSimpleName()).info(">>>>>> SingletonMessageService:destroy()");
    }

}
