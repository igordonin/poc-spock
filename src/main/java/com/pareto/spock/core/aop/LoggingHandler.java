package com.pareto.spock.core.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingHandler {
	
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Pointcut("execution(* com.pareto.spock.services..*.*(..))")
	protected void allServices() {
	}
	
	@Pointcut("execution(* com.pareto.spock.controllers..*.*(..))")
	protected void allControllers() {
	}
	
	@Pointcut("execution(* com.pareto.spock.repositories..*.*(..))")
	protected void allRepositories() {
	}

	@Before("allServices() || allControllers() || allRepositories()")
	public void logBefore(JoinPoint joinPoint) {
		log.debug("Entering in Method :  " + joinPoint.getSignature().getName());
		log.debug("Class Name :  " + joinPoint.getSignature().getDeclaringTypeName());
		log.debug("Arguments :  " + Arrays.toString(joinPoint.getArgs()));
		log.debug("Target class : " + joinPoint.getTarget().getClass().getName());
	}	
	
	@AfterThrowing(pointcut = "allServices() || allControllers() || allRepositories()", throwing = "exception")
	public void logAfterThrowing(JoinPoint joinPoint, Throwable exception) {
		log.error("An exception has been thrown in " + joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint.getSignature().getName() + " ()");
		log.error("Cause : " + exception.getCause());
	}
	
	@Around("allServices() || allControllers() || allRepositories()")
	public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
		
		long start = System.currentTimeMillis();
		try {
			String className = joinPoint.getSignature().getDeclaringTypeName();
			String methodName = joinPoint.getSignature().getName();
			Object result = joinPoint.proceed();
			long elapsedTime = System.currentTimeMillis() - start;
			log.info("Method " + className + "." + methodName + "()" + " execution time : "
					+ elapsedTime + " ms");
		
			return result;
		} catch (IllegalArgumentException e) {
			log.error("Illegal argument " + Arrays.toString(joinPoint.getArgs()) + " in "
					+ joinPoint.getSignature().getName() + "()");
			throw e;
		}
	}
}
