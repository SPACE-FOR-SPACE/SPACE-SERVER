package com.space.server.common.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class TimerAop {

  private final Logger logger = Logger.getLogger(getClass().getName());

  @Pointcut("execution(* com.space.server.core..service..*(..))")
  public void serviceLayer() {}

  @Pointcut("execution(* com.space.server.chat.service..*(..))")
  public void chatLayer() {}

  @Pointcut("execution(* com.space.server.state.service..*(..))")
  public void stateLayer() {}

  @Pointcut("execution(* com.space.server.ai..*(..))")
  public void aiLayer() {}

  @Around("serviceLayer() || chatLayer() || stateLayer() || aiLayer()")
  public Object measureMethodExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
    long start = System.nanoTime();
    Object proceed = joinPoint.proceed();
    long executionTime = System.nanoTime() - start;
    logger.info("\n실행된 메서드: " + joinPoint.getSignature() + "\n"+
                     "실행 시간: " + (executionTime / 1_000_000) + " ms");
    return proceed;
  }
}
