package miw.resources;

import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import miw.persistence.entities.Gender;
import miw.resources.exceptions.MalformedHeaderException;
import miw.resources.exceptions.NotFoundUserIdException;
import miw.resources.exceptions.UnauthorizedException;

@RestController
@RequestMapping(AdminResource.ADMINS)
public class AdminResource {

    public static final String ADMINS = "/admins";

    public static final String STATE = "/state";

    public static final String OUT_OF_TIME = "/out-of-time";

    public static final String ECHO = "/echo";

    public static final String ID = "/{id}";

    public static final String BODY = "/body";

    public static final String STRING_LIST = "/string-list";

    public static final String DTO_LIST = "/dto-list";

    public static final String ERROR = "/error";

    // Se puede comprobar con un navegador
    @RequestMapping(value = STATE, method = RequestMethod.GET)
    public String state() {
        return "{\"state\":\"ok\"}";
    }

    @RequestMapping(value = OUT_OF_TIME, method = RequestMethod.GET)
    public String outOfTime() {
        return "{\"state\":\"off\"}";
    }

    // Intercambio de datos
    @RequestMapping(value = ECHO + ID, method = RequestMethod.GET)
    public String echo(@RequestHeader(value = "token", required = false) String token, @PathVariable(value = "id") int id,
            @RequestParam(defaultValue = "Non") String param) {
        String response = "{\"id\":%d,\"token\":\"%s\",\"param\":\"%s\"}";
        return String.format(response, id, token, param);
    }

    @RequestMapping(value = BODY, method = RequestMethod.POST)
    public Dto body(@RequestBody Dto wrapper) {
        return wrapper;
    }

    @RequestMapping(value = BODY + STRING_LIST, method = RequestMethod.GET)
    public List<String> bodyStringList() {
        return Arrays.asList("uno", "dos", "tres");
    }

    @RequestMapping(value = BODY + DTO_LIST, method = RequestMethod.GET)
    public List<Dto> bodyDtoList() {
        Dto wrapper1 = new Dto(666, "daemon", Gender.FEMALE, new GregorianCalendar(1979, 07, 22));
        Dto wrapper2 = new Dto(999, "last", Gender.MALE, new GregorianCalendar(1979, 07, 22));
        Dto wrapper3 = new Dto(000, "first", Gender.FEMALE, new GregorianCalendar(1979, 07, 22));
        return Arrays.asList(wrapper1, wrapper2, wrapper3);
    }

    // Excepciones
    @RequestMapping(value = ERROR + ID, method = RequestMethod.GET)
    public Dto error(@RequestHeader(value = "token") String token, @PathVariable(value = "id") int id)
            throws NotFoundUserIdException, UnauthorizedException, MalformedHeaderException {
        if (id == 0) {
            throw new NotFoundUserIdException("id:" + id);
        }
        if (token.equals("kk")) {
            throw new MalformedHeaderException("token:" + token);
        }
        if (token.equals("Basic kk")) {
            throw new UnauthorizedException("token:" + token);
        }
        return new Dto(666, "daemon", Gender.FEMALE, new GregorianCalendar(1979, 07, 22));
    }

}
