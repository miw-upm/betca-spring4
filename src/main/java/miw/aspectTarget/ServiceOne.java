package miw.aspectTarget;

import org.springframework.stereotype.Service;

import miw.aspect.MyClassAnnotation;
import miw.aspect.MyMethodAnnotation;

@Service
@MyClassAnnotation
public class ServiceOne {

    public void method() {
        System.out.println("-----------------> ServiceOne:method");
    }

    public void argString(String name) {
        System.out.println("-----------------> ServiceOne:argOneString(name=" + name + ")");
    }

    public int returnInt() {
        int result = 666;
        System.out.println("-----------------> ServiceOne:returnInt return= " + result);
        return result;
    }

    public void exception() throws Exception {
        System.out.println("-----------------> ServiceOne:exception");
        throw new Exception(">:o(");
    }

    @MyMethodAnnotation
    public void annotation() {
        System.out.println("-----------------> ServiceOne:annotation");
    }

}
