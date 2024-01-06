package com.kirana.transaction.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Configuration;

import com.kirana.transaction.custom.exception.EmptyInputException;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Aspect
@Slf4j
public class AspectConfig {

	
	@Before("execution(* com.kirana.transaction.controller.*.*(..))")
	public void logBeforeTransactionController(JoinPoint j)
	{
			log.info("Excuting :"+j.getSignature());
	}
	
	@After("execution(* com.kirana.transaction.controller.*.*(..))")
	public void logAfterTransactionController(JoinPoint j)
	{
			log.info("Completed Execution :"+j);
	}
	
	@Around("execution(* com.kirana.transaction.service.*.*(..))")
	public Object serviceHandler(ProceedingJoinPoint j)
	{
			try {
				return j.proceed();
			} catch (Throwable e) {
				log.error(e.getMessage());
				throw new EmptyInputException("","not found");

			}
			
	}
	
}
