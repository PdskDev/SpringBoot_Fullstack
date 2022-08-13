package com.nadet.demo.aspect;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class LogAspect {
	
	/** Log output before executing the service
	 * Targer: [UserService] is included in the class name
	 */
	@Before("execution(* com.nadet.demo.domain.user.service.UserService.*(..))")
	public void startLog() {
		log.info("Method start: ");
	}
	
	/** Log output after executing the service
	 * Targer: [UserService] is included in the class name
	 */
	@After("execution(* com.nadet.demo.domain.user.service.UserService.*(..))")
	public void endLog() {
		log.info("Method end: ");
		
	}
	

}
