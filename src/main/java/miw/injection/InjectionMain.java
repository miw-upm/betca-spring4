package miw.injection;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class InjectionMain {

    @Autowired
    private ConfigurableApplicationContext configurableApplicationContext;

    @Autowired
    private SingletonMessageService singletonMessageService;

    @Autowired
    private SingletonMessageService singletonMessageService2;

    @Autowired
    private PrototypeInjectoOnlyMessageService prototypeInjectoOnlyMessageService;

    @Autowired
    private PrototypeInjectoOnlyMessageService prototypeInjectoOnlyMessageService2;

    public String getMessage() {
        return this.singletonMessageService.getMessage();
    }
    
    public void debugAndClose() {
        Logger.getLogger("BETCA-spring."+ this.getClass().getSimpleName()).info(">>>>>> message: " + this.singletonMessageService.getMessage());
        Logger.getLogger("BETCA-spring."+ this.getClass().getSimpleName()).info(">>>>>> message: " + this.singletonMessageService2.getMessage());
        Logger.getLogger("BETCA-spring."+ this.getClass().getSimpleName()).info(">>>>>> message: " + this.prototypeInjectoOnlyMessageService.getMessage());
        Logger.getLogger("BETCA-spring."+ this.getClass().getSimpleName()).info(">>>>>> message: " + this.prototypeInjectoOnlyMessageService2.getMessage());
        configurableApplicationContext.close();
    }

}
