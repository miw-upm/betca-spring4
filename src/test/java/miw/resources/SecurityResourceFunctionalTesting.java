package miw.resources;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:test.properties")
public class SecurityResourceFunctionalTesting {

    @Rule
    public ExpectedException thrown = ExpectedException.none();
    
    @Value("${local.server.port}")
    private int port;

    @Test
    public void testUserOK() {
        new RestBuilder<String>(port).path(SecurityResource.SECURITY).path(SecurityResource.USER).basicAuth("user", "123456").clazz(String.class).get().build();
    }

    @Test
    public void testUserOtherUserOK() {
        new RestBuilder<String>(port).path(SecurityResource.SECURITY).path(SecurityResource.USER).basicAuth("manager", "123456").clazz(String.class).get().build();
    }

    @Test
    public void testManagerOK() {
        new RestBuilder<String>(port).path(SecurityResource.SECURITY).path(SecurityResource.MANAGER).basicAuth("manager", "123456").clazz(String.class).get().build();
    }

    @Test
    public void testAdminOK() {
        new RestBuilder<String>(port).path(SecurityResource.SECURITY).path(SecurityResource.ADMIN).basicAuth("admin", "123456").clazz(String.class).get().build();
    }

    @Test
    public void testAdminUnauthorizedPassError() {
        thrown.expect(new HttpMatcher(HttpStatus.UNAUTHORIZED));
        new RestBuilder<String>(port).path(SecurityResource.SECURITY).path(SecurityResource.ADMIN).basicAuth("admin", "kk").clazz(String.class).get().build();
    }

    @Test
    public void testAdminUnauthorizedUser() {
        thrown.expect(new HttpMatcher(HttpStatus.FORBIDDEN));
        new RestBuilder<String>(port).path(SecurityResource.SECURITY).path(SecurityResource.ADMIN).basicAuth("user", "123456").clazz(String.class).get().build();
    }

    @Test
    public void testUserUnauthorizedNonUser() {
        thrown.expect(new HttpMatcher(HttpStatus.UNAUTHORIZED));
        new RestBuilder<String>(port).path(SecurityResource.SECURITY).path(SecurityResource.USER).clazz(String.class).get().build();
    }
    
     @Test
     public void testManagerUnauthorizedUser() {
         thrown.expect(new HttpMatcher(HttpStatus.FORBIDDEN));
         new RestBuilder<String>(port).path(SecurityResource.SECURITY).path(SecurityResource.MANAGER).basicAuth("user", "123456").clazz(String.class).get().build();
     }

}
