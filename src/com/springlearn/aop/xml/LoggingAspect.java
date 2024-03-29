package com.springlearn.aop.xml;

import java.util.Arrays;
import java.util.List;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

public class LoggingAspect {
	
	public void declareJoinPointExpression() {}
	public void beforeMethod(JoinPoint joinPoint) {
		String methodName = joinPoint.getSignature().getName();
		List<Object> args=Arrays.asList(joinPoint.getArgs());
		System.out.println("The method "+methodName+" begins with "+ args);
	}
	
	public void afterMethod(JoinPoint joinPoint) {
		String methodName = joinPoint.getSignature().getName();
		System.out.println("The method "+methodName+" ends");
	}
	
	public void afterReturning(JoinPoint joinPoint,Object result) {
		String methodName = joinPoint.getSignature().getName();
		System.out.println("The method "+methodName+" ends with "+result);
	}
	
	public void afterThrowing(JoinPoint joinPoint,Exception ex) {
		String methodName = joinPoint.getSignature().getName();
		System.out.println("The method "+methodName+" occur exception "+ex);
	}
	public Object aroundMethod(ProceedingJoinPoint pjd) {
		Object result=null;
		String methodName = pjd.getSignature().getName();
		//执行目标方法
		try {
			//前置通知
			System.out.println("The method "+ methodName+" begins1 with "+ Arrays.asList(pjd.getArgs()));
			result = pjd.proceed();
			//返回通知
			System.out.println("The method "+ methodName+" ends1 with "+ result);
		} catch (Throwable e) {
			//异常通知
			System.out.println("The method "+ methodName+" occur1 exception "+ e);
			throw new RuntimeException(e);
		}
		//后置通知
		return result;
	}
}
