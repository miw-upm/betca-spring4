package miw.aspectTarget;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import miw.aspect.MyClassAnnotation;
import miw.aspect.MyMethodAnnotation;

@Service
@MyClassAnnotation
public class ServiceOne {

    public void method() {
        Logger.getLogger("miw").debug("-----------------> ServiceOne:method");
    }

    public void argString(String name) {
        Logger.getLogger("miw").debug("-----------------> ServiceOne:argOneString(name=" + name + ")");
    }

    public int returnInt() {
        int result = 666;
        Logger.getLogger("miw").debug("-----------------> ServiceOne:returnInt return= " + result);
        return result;
    }

    public void exception() throws Exception {
        Logger.getLogger("miw").debug("-----------------> ServiceOne:exception");
        throw new Exception(">:o(");
    }

    @MyMethodAnnotation
    public void annotation() {
        Logger.getLogger("miw").debug("-----------------> ServiceOne:annotation");
    }

}
