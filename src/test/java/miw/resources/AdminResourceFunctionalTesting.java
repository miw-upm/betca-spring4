package miw.resources;

import static org.junit.Assert.assertEquals;

import java.net.URI;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import miw.persistence.jpa.entities.Gender;

import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class AdminResourceFunctionalTesting {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Value("${local.server.port}")
    private int port;

    @Test
    public void testState() {
        URI uri = UriComponentsBuilder.fromHttpUrl("http://localhost:" + port + AdminResource.ADMINS + AdminResource.STATE).build().encode()
                .toUri();
        RequestEntity<Object> requestEntity = new RequestEntity<>(HttpMethod.GET, uri);

        ResponseEntity<String> responseEntity = new RestTemplate().exchange(requestEntity, String.class);
        String response = responseEntity.getBody();

        Logger.getLogger("BETCA-spring: /admins...").info("Response: " + response);
        assertEquals("{\"state\":\"ok\"}", response);
    }

    @Test
    public void testStateRestBuilder() {
        String json = new RestBuilder<String>(port).clazz(String.class).path(AdminResource.ADMINS).path(AdminResource.STATE).get().build();
        assertEquals("{\"state\":\"ok\"}", json);
    }

    @Test
    public void testOutOfTime() {
        thrown.expect(new HttpMatcher(HttpStatus.FORBIDDEN));
        new RestBuilder<>(port).path(AdminResource.ADMINS).path(AdminResource.OUT_OF_TIME).get().build();
    }

    // Parametros y cuerpo
    @Test
    public void testParamEcho() {
        String json = new RestBuilder<String>(port).clazz(String.class).path(AdminResource.ADMINS).path(AdminResource.ECHO)
                .path(AdminResource.ID).expand(666).param("param", "paaaaram").header("token", "toooken").get().build();
        assertEquals("{\"id\":666,\"token\":\"toooken\",\"param\":\"paaaaram\"}", json);
    }

    @Test
    public void testBodyEcho() {
        Dto dto = new Dto(666, "daemon", Gender.FEMALE, new GregorianCalendar(1979, 07, 22));
        Dto response = new RestBuilder<Dto>(port).clazz(Dto.class).path(AdminResource.ADMINS).path(AdminResource.BODY).body(dto).post()
                .build();
        System.out.println(response);
        assertEquals(dto, response);
    }

    @Test
    public void testBodyStringList() {
        List<String> response = Arrays.asList(new RestBuilder<String[]>(port).path(AdminResource.ADMINS).path(AdminResource.BODY)
                .path(AdminResource.STRING_LIST).clazz(String[].class).get().build());
        assertEquals(3, response.size());
    }

    @Test
    public void testBodyDtoList() {
        List<Dto> response = Arrays.asList(new RestBuilder<Dto[]>(port).path(AdminResource.ADMINS).path(AdminResource.BODY)
                .path(AdminResource.DTO_LIST).clazz(Dto[].class).get().build());
        assertEquals(3, response.size());
    }

    // Exceptions
    @Test
    public void testErrorNotToken() {
        thrown.expect(new HttpMatcher(HttpStatus.BAD_REQUEST));
        new RestBuilder<Dto>(port).path(AdminResource.ADMINS).path(AdminResource.ERROR).path(AdminResource.ID).expand(66).get().build();
    }

    @Test
    public void testErrorMalFormedToken() {
        thrown.expect(new HttpMatcher(HttpStatus.BAD_REQUEST));
        new RestBuilder<Dto>(port).path(AdminResource.ADMINS).path(AdminResource.ERROR).path(AdminResource.ID).expand(66)
                .header("token", "kk").get().build();
    }

    @Test
    public void testErrorNotExistToken() {
        thrown.expect(new HttpMatcher(HttpStatus.UNAUTHORIZED));
        new RestBuilder<Dto>(port).path(AdminResource.ADMINS).path(AdminResource.ERROR).path(AdminResource.ID).expand(66)
                .header("token", "Basic kk").get().build();
    }

    @Test
    public void testErrorNotExistId() {
        thrown.expect(new HttpMatcher(HttpStatus.NOT_FOUND));
        new RestBuilder<Dto>(port).path(AdminResource.ADMINS).path(AdminResource.ERROR).path(AdminResource.ID).expand(0)
                .header("token", "Basic good").get().build();
    }

    @Test
    public void testErrorOk() {
        Dto response = new RestBuilder<Dto>(port).path(AdminResource.ADMINS).path(AdminResource.ERROR).path(AdminResource.ID).expand(666)
                .header("token", "Basic good").clazz(Dto.class).get().build();
        assertEquals(666, response.getId());
    }
    //
    // @Test
    // public void testSecurityAnnotationOk() {
    // String response = new RestBuilder<String>(URL_API).path(Uris.ADMINS).path(Uris.SECURITY).basicAuth("admin", "123456")
    // .clazz(String.class).get().build();
    // System.out.println("INFO >>>>> " + response);
    // }
    //
    // @Test
    // public void testSecurityAnnotationForbidden() {
    // try {
    // new RestBuilder<String>(URL_API).path(Uris.ADMINS).path(Uris.SECURITY).basicAuth("user", "123456").clazz(String.class).get()
    // .build();
    // fail();
    // } catch (HttpClientErrorException httpError) {
    // assertEquals(HttpStatus.FORBIDDEN, httpError.getStatusCode());
    // System.out.println("ERROR >>>>> " + httpError.getMessage());
    // System.out.println("ERROR >>>>> " + httpError.getResponseBodyAsString());
    // }
    // }
    //
    // @Test
    // public void testSecurityAnnotationUnauthorized() {
    // try {
    // new RestBuilder<String>(URL_API).path(Uris.ADMINS).path(Uris.SECURITY).basicAuth("user", "kkk").clazz(String.class).get()
    // .build();
    // fail();
    // } catch (HttpClientErrorException httpError) {
    // assertEquals(HttpStatus.UNAUTHORIZED, httpError.getStatusCode());
    // System.out.println("ERROR >>>>> " + httpError.getMessage());
    // System.out.println("ERROR >>>>> " + httpError.getResponseBodyAsString());
    // }
    // }
}
