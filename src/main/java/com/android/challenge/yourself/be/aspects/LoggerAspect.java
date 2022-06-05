package com.android.challenge.yourself.be.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;



@Slf4j
@Aspect
@Component
public class LoggerAspect {

    @AfterThrowing(value = "execution(* com.android.challenge.yourself.be..*.*(..))", throwing = "ex")
    public void logException(JoinPoint joinPoint, Exception ex) {
        log.error(joinPoint.getSignature() + " An exception happened due to : " + ex.getMessage());
    }
}
