package miw.injection;

import org.springframework.stereotype.Service;

@Service
public class PrototypeInjectoOnlyMessageService {

    private int counter = 1;

    public String getMessage() {
        return this.counter++ + ": PrototypeInjectoOnlyMessageService!!!";
    }
}
