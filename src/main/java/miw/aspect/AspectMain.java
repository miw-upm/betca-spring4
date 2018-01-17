package miw.aspect;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import miw.aspectTarget.ServiceOne;

@Component
public class AspectMain {

    @Autowired
    private ConfigurableApplicationContext configurableApplicationContext;

    @Autowired
    private ServiceOne serviceOne;

    public void debugAndClose() {
        System.out.println("------------------------- ooo -----------------------------------------------");
        serviceOne.method();
        serviceOne.argString("cadena");
        serviceOne.returnInt();
        try {
            serviceOne.exception();
        } catch (Exception e) {
            System.out.println(">>>Se procesa la exception");
        }
        serviceOne.annotation();
        System.out.println("------------------------- ooo -----------------------------------------------");
        configurableApplicationContext.close();
    }
}
