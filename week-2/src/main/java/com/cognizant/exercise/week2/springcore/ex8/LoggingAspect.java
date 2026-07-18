package com.cognizant.exercise.week2.springcore.ex8;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class LoggingAspect {

    @Before("execution(* com.cognizant.exercise.week2.springcore.ex8.*.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        System.out.println("[Ex 8 AOP Log - BEFORE] Entering: " + joinPoint.getSignature().toShortString());
    }

    @After("execution(* com.cognizant.exercise.week2.springcore.ex8.*.*(..))")
    public void logAfter(JoinPoint joinPoint) {
        System.out.println("[Ex 8 AOP Log - AFTER] Exiting: " + joinPoint.getSignature().toShortString());
    }
}
