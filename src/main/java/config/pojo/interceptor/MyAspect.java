package config.pojo.interceptor;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.DeclareParents;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import config.pojo.AnimalValidatorImpl;
import config.pojo.definition.*;
// import com.springboot.chapter4.aspect.validator.UserValidator;
// import com.springboot.chapter4.aspect.validator.impl.UserValidatorImpl;
// import com.springboot.chapter4.pojo.User;

@Aspect
@Component
public class MyAspect {
	
	@DeclareParents(value= "config.pojo.Dog", defaultImpl=AnimalValidatorImpl.class)
	public AnimalValidator animalValidator;
	
	@Pointcut("execution(* config.pojo.Dog.eat(..))")
	public void pointCut() {
	}
	
	// @Before("pointCut() && args(user)")
	// public void beforeParam(User user) {
	// 	System.out.println("before ......");
	// } 
	
	@Before("pointCut()")
	public void before() {
		System.out.println("before ......");
	}
	
	@After("pointCut()")
	public void after() {
		System.out.println("after ......");
	}
	
	
	@AfterReturning("pointCut()")
	public void afterReturning() {
		System.out.println("afterReturning ......");
	}
	
	@AfterThrowing("pointCut()")
	public void afterThrowing() {
		System.out.println("afterThrowing ......");
	}
	

	@Around("pointCut()")
	public void around(ProceedingJoinPoint jp) throws Throwable {
		System.out.println("around before......");
		jp.proceed();
		System.out.println("around after......");
	}
}
