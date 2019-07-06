package com.iscolt.aop.aspect;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * 切面
 *
 * @Auther: MaWenyi
 * @Date: 2019/6/25
 * @Description: com.iscolt.aop.aspect
 * @version: 1.0
 */
@Component
@Aspect
public class LogAspect {

    @Pointcut("execution(* com.iscolt.aop.service.*.*(..))")
    public void pc1() {
    }

    @Before(value = "pc1()")
    public void before(JoinPoint joinPoint) {
        String name = joinPoint.getSignature().getName();
        System.out.println(name + "开始执行");
    }

    @After(value = "pc1()")
    public void after(JoinPoint joinPoint) {
        String name = joinPoint.getSignature().getName();
        System.out.println(name + "结束执行");
    }

    @AfterReturning(value = "pc1()", returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result) {
        String name = joinPoint.getSignature().getName();
        System.out.println(name + "返回" + result);
    }

    @AfterThrowing(value = "pc1()", throwing = "e")
    public void afterThrowing(JoinPoint joinPoint, Exception e) {
        String name = joinPoint.getSignature().getName();
        System.out.println(name + "抛出异常" + e.getMessage());
    }

    @Around("pc1()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        return proceedingJoinPoint.proceed();
    }
}
