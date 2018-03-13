package miw.aspect;

import org.apache.log4j.LogManager;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ApiLogs {

    @Pointcut("@within(org.springframework.web.bind.annotation.RestController)")
    public void allResources() {
    }

    @Before("allResources()")
    public void apiRequestLog(JoinPoint jp) {
        LogManager.getLogger(jp.getSignature().getDeclaringTypeName()).info("------------------------- o -------------------------");
        String log = jp.getSignature().getName() + " >>>";
        for (Object arg : jp.getArgs()) {
            log += "\n   ARG: " + arg;
        }
        LogManager.getLogger(jp.getSignature().getDeclaringTypeName()).info(log);
    }

    @AfterReturning(pointcut = "allResources()", returning = "result")
    public void apiResponseLog(JoinPoint jp, Object result) {
        String log = "<<< Return << " + jp.getSignature().getName() + ": " + result;
        LogManager.getLogger(jp.getSignature().getDeclaringTypeName()).info(log);
    }

    @AfterThrowing(pointcut = "allResources()", throwing = "exception")
    public void apiResponseExceptionLog(JoinPoint jp, Exception exception) {
        String log = "<<< Return --EXCEPTION-- << " + jp.getSignature().getName() + ": " + exception.getClass().getSimpleName();
        LogManager.getLogger(jp.getSignature().getDeclaringTypeName()).info(log);
    }

}
