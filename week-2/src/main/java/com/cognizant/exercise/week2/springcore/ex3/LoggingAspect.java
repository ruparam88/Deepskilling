package com.cognizant.exercise.week2.springcore.ex3;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class LoggingAspect {

    @Around("execution(* com.cognizant.exercise.week2.springcore.ex3.*.*(..))")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        
        System.out.println("[Ex 3 AOP Log] Entering method: " + joinPoint.getSignature().toShortString());
        
        Object proceed = joinPoint.proceed();
        
        long executionTime = System.currentTimeMillis() - start;
        System.out.println("[Ex 3 AOP Log] Method " + joinPoint.getSignature().toShortString() + " finished in " + executionTime + "ms");
        
        return proceed;
    }
}
