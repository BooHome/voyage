package com.ihere.voyage.aspect;

import com.ihere.voyage.annotation.Paging;
import com.ihere.voyage.entity.Mouse;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author: fengshibo
 * @date: 2018/9/26 15:14
 * @description:
 */
@Aspect
@Component
public class PagingAspect{

    protected static org.slf4j.Logger logger = LoggerFactory.getLogger(PagingAspect.class);

    @Pointcut("execution(public * com.ihere.voyage.controller..*.*(..))")
    public void pagingExecution() {

    }

    @Pointcut("@annotation(com.ihere.voyage.annotation.Paging)")
    public void pagingAnnotation() {

    }

    @Before("pagingAnnotation()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        logger.info("JOINPOINT : " + joinPoint.getArgs());
    }

    @Around("pagingAnnotation()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Paging annotation = ((MethodSignature) proceedingJoinPoint.getSignature()).getMethod().getAnnotation(Paging.class);
        //获取注解值，反射其class
        Class aClass = Class.forName(annotation.value());
        Object[] args = proceedingJoinPoint.getArgs();
        for (Object arg :args) {
            if (arg instanceof Mouse){
                ((Mouse) arg).setName("语言");
                break;
            }
        }
        Object result=proceedingJoinPoint.proceed();
        return result;
    }

    @AfterReturning(returning = "ret", pointcut = "pagingAnnotation()")
    public void doAfterReturning(Object ret) throws Throwable {
        if (ret instanceof Mouse){
            ((Mouse) ret).setName("言");
        }
    }
}
