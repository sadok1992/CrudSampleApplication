package com.sadok.crud.aspect;

import java.util.Calendar;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class LoggingAspect {

	Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

	@Around("@annotation(com.sadok.crud.util.LogAround)")
	public Object logMethod(ProceedingJoinPoint joinPoint) throws Throwable {

		logger.info("class : " + joinPoint.getSignature().getDeclaringTypeName() + " Enter method: "
				+ joinPoint.getSignature().getName());
		Long startTime = Calendar.getInstance().getTimeInMillis();
		Object o = null;
		try {
			o = joinPoint.proceed();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Long endTime = Calendar.getInstance().getTimeInMillis();

		logger.info("class : " + joinPoint.getSignature().getDeclaringTypeName() + " Quit method : "
				+ joinPoint.getSignature().getName() + " with execution time :" + String.valueOf(endTime - startTime));
		return o;
	}

}
