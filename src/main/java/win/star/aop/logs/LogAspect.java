package win.star.aop.logs;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * 访问日志
 *
 * Created by liumian on 2016/11/19.
 */

//@Component
//@Aspect
public class LogAspect {

    private Log log = LogFactory.getLog(LogAspect.class);

    @Pointcut("execution(* win.star.business.controller..*(..))")
    public void controller(){
    }

    @Before("controller()")
    public void beforeController(JoinPoint joinPoint){
        log.info("前置通知...");
        return;
    }

    @After("controller()")
    public void afterController(){
        log.info("后置通知...");
    }



}
