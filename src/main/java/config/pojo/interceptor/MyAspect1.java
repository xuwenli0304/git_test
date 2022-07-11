package config.pojo.interceptor;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;

@Aspect
@Order(1)
public class MyAspect1 {

    @Pointcut("execution(* config.pojo.Dog.setThis(..))")
	public void pointCut() {
	}
	
    @Before("pointCut()")
	public void before() {
		System.out.println("1_BEFORE....before ......");
	}

    
    @After("pointCut()")
	public void after() {
		System.out.println("1_AFTER after ......");
	}
	
}
