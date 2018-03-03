package miw.resources;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import miw.resources.AdminResource;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class TimeBasedAccessInterceptorFunctionalTesting {

    @Rule
    public ExpectedException thrown = ExpectedException.none();
    
    @Value("${local.server.port}")
    private int port;

    @Test
    public void testOutOfTime() {
        thrown.expect(new HttpMatcher(HttpStatus.FORBIDDEN));
        new RestBuilder<String>(port).path(AdminResource.ADMINS).path(AdminResource.OUT_OF_TIME).clazz(String.class).get().build();
    }
    
}
