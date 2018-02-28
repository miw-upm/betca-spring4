package miw.aspect;

import java.util.Date;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ProcessTime {

    @Pointcut("@annotation(miw.aspect.Time)")
    public void timeAnnotation() {
    }

    @Around("timeAnnotation()")
    public Object time(ProceedingJoinPoint pjp) throws Throwable {
        long initTime = new Date().getTime();
        Object obj = pjp.proceed();
        Logger.getLogger(this.getClass()).info("-----> ProcessTime (" + pjp.getSignature().getDeclaringTypeName() + ":"
                + pjp.getSignature().getName() + "): " + (new Date().getTime() - initTime) + "ms");
        return obj;
    }
}
