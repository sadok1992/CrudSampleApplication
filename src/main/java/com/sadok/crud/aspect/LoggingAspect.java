package com.sadok.crud.aspect;

import java.util.Calendar;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;


@Aspect
@Configuration
public class LoggingAspect {

	@Around("@annotation(com.sadok.crud.util.LogAround)")
	public Object logMethod(ProceedingJoinPoint joinPoint) throws Throwable {
		System.out.println("Enter method: " + joinPoint.getSignature().getName());
		Long startTime = Calendar.getInstance().getTimeInMillis();
		Object o = null;
		try {
			o = joinPoint.proceed();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Long endTime = Calendar.getInstance().getTimeInMillis();
		System.out.println("Quit method : " + joinPoint.getSignature().getName() + " with execution time :"
				+ String.valueOf(endTime - startTime));
		return o;
	}


}
