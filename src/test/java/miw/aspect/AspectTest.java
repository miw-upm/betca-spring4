package miw.aspect;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import miw.aspectTarget.ServiceOne;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AspectTest {

    @Autowired
    private ServiceOne serviceOne;

    @Test
    public void methodTest() {
        Logger.getLogger("miw").debug("------------------------- ooo -----------------------------------------------");
        serviceOne.method();
        Logger.getLogger("miw").debug("------------------------- ooo -----------------------------------------------");
    }

    @Test
    public void argStringTest() {
        Logger.getLogger("miw").debug("------------------------- ooo -----------------------------------------------");
        serviceOne.argString("cadena");
        Logger.getLogger("miw").debug("------------------------- ooo -----------------------------------------------");
    }

    @Test
    public void returnIntTest() {
        Logger.getLogger("miw").debug("------------------------- ooo -----------------------------------------------");
        serviceOne.returnInt();
        Logger.getLogger("miw").debug("------------------------- ooo -----------------------------------------------");
    }

    @Test
    public void exceptionTest() {
        Logger.getLogger("miw").debug("------------------------- ooo -----------------------------------------------");
        try {
            serviceOne.exception();
        } catch (Exception e) {
            Logger.getLogger("miw").debug(">>>>>>>>>> Se trata la exception");
        }
        Logger.getLogger("miw").debug("------------------------- ooo -----------------------------------------------");
    }

    @Test
    public void annotationTest() {
        Logger.getLogger("miw").debug("------------------------- ooo -----------------------------------------------");
        serviceOne.annotation();
        Logger.getLogger("miw").debug("------------------------- ooo -----------------------------------------------");
    }
}
