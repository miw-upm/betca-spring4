package miw.injection;

import static org.junit.Assert.assertEquals;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:test.properties")
public class MainInjectonMessageServiceMockTest {

    @MockBean
    private SingletonMessageService singletonMessageService;

    @Autowired
    private InjectionMain mainInjection;

    @Test
    public void testGetMessage() {
        given(this.singletonMessageService.getMessage()).willReturn("ok");
        assertEquals("ok", this.mainInjection.getMessage());
        Logger.getLogger(this.getClass().getName()).debug(">>>>>> message: " + this.mainInjection.getMessage());
    }
}
