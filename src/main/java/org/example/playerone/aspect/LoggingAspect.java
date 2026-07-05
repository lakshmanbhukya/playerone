package org.example.playerone.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Around("execution(* org.example.playerone.controller..*(..)) || " +
            "execution(* org.example.playerone.service..*(..)) || " +
            "execution(* org.example.playerone.repository..*(..))")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = joinPoint.getSignature().getName();

        logger.info("[AOP Entry] Executing {}.{}() with arguments: {}", className, methodName, Arrays.toString(joinPoint.getArgs()));

        Object result;
        try {
            result = joinPoint.proceed();
        } catch (Throwable throwable) {
            long executionTime = System.currentTimeMillis() - start;
            logger.error("[AOP Error] Exception in {}.{}() after {}ms: {}", className, methodName, executionTime, throwable.getMessage());
            throw throwable;
        }

        long executionTime = System.currentTimeMillis() - start;
        logger.info("[AOP Exit] Completed {}.{}() in {}ms.", className, methodName, executionTime);
        return result;
    }
}
