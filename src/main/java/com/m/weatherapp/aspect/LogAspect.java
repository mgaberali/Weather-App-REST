package com.m.weatherapp.aspect;


import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect {

    @Pointcut("execution(* com.m.weatherapp.model.repository.*.*(..))")
    public void modelMethodsPointcut() {}

    @Pointcut("execution(* com.m.weatherapp.service.service.*.*(..))")
    public void serviceMethodsPointcut() {}

    @Pointcut("execution(* com.m.weatherapp.rest.controller.*.*(..))")
    public void restMethodsPointcut() {}

    @Around(value="modelMethodsPointcut() || serviceMethodsPointcut() || restMethodsPointcut()")
    public Object logAroundMethod(ProceedingJoinPoint joinPoint) {

        // get logger
        Logger logger = Logger.getLogger(joinPoint.getTarget().getClass());

        Object result = null;
        try{

            // log parameters
            StringBuilder args = new StringBuilder();
            for (Object obj : joinPoint.getArgs())
                args.append(obj).append(", ");

            // log before method execution
            logger.debug("Method -> " + joinPoint.getSignature().getName()+" is called with parameters: " + args);

            // method execution
            result = joinPoint.proceed();

            // log after method execution
            logger.debug("Method -> " + joinPoint.getSignature().getName()+" result is: \n" + result.toString());

        }catch (Exception e) {

            logger.error(e.getClass()+" has ocurred at "+joinPoint.getSignature().getName()+"method: "+e.getMessage());
            e.printStackTrace();

        }catch (Throwable t) {
            logger.warn(t.getClass()+" has ocurred at "+joinPoint.getSignature().getName()+"method: "+t.getMessage());
        }

        return result;
    }

}
