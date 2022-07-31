package com.example.graphql.logging;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

	@Pointcut("within(com.example..*)")
	public void pointCutAllMethods() {
		// Pointcut Method
	}

	@Around(value = "pointCutAllMethods()")
	public Object monitorRepositoryMethods(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

		MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();

		// Get intercepted method details
		String className = methodSignature.getDeclaringType().getSimpleName();
		String methodName = methodSignature.getName();

		log.info("Execution of " + className + "." + methodName);

		Object result = proceedingJoinPoint.proceed();

		StringBuilder methodParamsBuilder = new StringBuilder();
		for (String params : methodSignature.getParameterNames()) {
			methodParamsBuilder.append(params);
		}

		log.info("Execution of " + className + "." + methodName + " :: " + methodParamsBuilder.toString() + ":::"
				+ result);

		return result;
	}

}
