package com.space.server.common.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class TimerAop {

  private final Logger logger = Logger.getLogger(getClass().getName());

  @Around("execution(* com.space.server..service..*(..))")
  public Object measureMethodExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
    long start = System.nanoTime();
    Object proceed = joinPoint.proceed();
    long executionTime = System.nanoTime() - start;
    logger.info("\n실행된 메서드: " + joinPoint.getSignature() + "\n"+
                     "실행 시간: " + (executionTime / 1_000_000) + " ms");
    return proceed;
  }
}
