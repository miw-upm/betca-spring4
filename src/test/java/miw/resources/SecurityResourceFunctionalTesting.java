package miw.resources;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.http.HttpStatus;

public class SecurityResourceFunctionalTesting {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private static final String RESOURCE = "http://localhost:8080/" + SecurityResource.SECURITY;

    @Test
    public void testUserOK() {
        new RestBuilder<String>(RESOURCE).path(SecurityResource.USER).basicAuth("user", "123456").clazz(String.class).get().build();
    }

    @Test
    public void testUserOtherUserOK() {
        new RestBuilder<String>(RESOURCE).path(SecurityResource.USER).basicAuth("manager", "123456").clazz(String.class).get().build();
    }

    @Test
    public void testManagerOK() {
        new RestBuilder<String>(RESOURCE).path(SecurityResource.MANAGER).basicAuth("manager", "123456").clazz(String.class).get().build();
    }

    @Test
    public void testAdminOK() {
        new RestBuilder<String>(RESOURCE).path(SecurityResource.ADMIN).basicAuth("admin", "123456").clazz(String.class).get().build();
    }

    @Test
    public void testAdminUnauthorizedPassError() {
        thrown.expect(new HttpMatcher(HttpStatus.UNAUTHORIZED));
        new RestBuilder<String>(RESOURCE).path(SecurityResource.ADMIN).basicAuth("admin", "kk").clazz(String.class).get().build();
    }

    @Test
    public void testAdminUnauthorizedUser() {
        thrown.expect(new HttpMatcher(HttpStatus.FORBIDDEN));
        new RestBuilder<String>(RESOURCE).path(SecurityResource.ADMIN).basicAuth("user", "123456").clazz(String.class).get().build();
    }

    @Test
    public void testUserUnauthorizedNonUser() {
        thrown.expect(new HttpMatcher(HttpStatus.UNAUTHORIZED));
        new RestBuilder<String>(RESOURCE).path(SecurityResource.USER).clazz(String.class).get().build();
    }
    
     @Test
     public void testManagerUnauthorizedUser() {
         thrown.expect(new HttpMatcher(HttpStatus.FORBIDDEN));
         new RestBuilder<String>(RESOURCE).path(SecurityResource.MANAGER).basicAuth("user", "123456").clazz(String.class).get().build();
     }

}
