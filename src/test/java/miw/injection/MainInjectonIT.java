package miw.injection;

import static org.junit.Assert.assertEquals;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:test.properties")
public class MainInjectonIT {

    @Autowired
    private InjectionMain mainInjection;

    @Test
    public void testGetMessage() {
        int i = Integer.parseInt(this.mainInjection.getMessage().split(":")[0]);
        assertEquals(i + 111, Integer.parseInt(this.mainInjection.getMessage().split(":")[0]));
        Logger.getLogger(this.getClass().getName()).debug(">>>>>> message: " + this.mainInjection.getMessage());
    }
}
