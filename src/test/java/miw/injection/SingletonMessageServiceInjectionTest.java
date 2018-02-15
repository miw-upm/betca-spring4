package miw.injection;

import static org.junit.Assert.assertEquals;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:test.properties")
public class SingletonMessageServiceInjectionTest {

    @Autowired
    private SingletonMessageService singletonMessageService;

    @Test
    public void testGetMessage() {
        // OJO Test relativos
        int i = Integer.parseInt(this.singletonMessageService.getMessage().split(":")[0]);
        assertEquals(i+1, Integer.parseInt(this.singletonMessageService.getMessage().split(":")[0]));
        Logger.getLogger(this.getClass()).debug(">>>>>> message: " + this.singletonMessageService.getMessage());        
    }
}
