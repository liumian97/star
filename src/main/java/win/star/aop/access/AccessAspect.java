package win.star.aop.access;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.Semaphore;

/**
 * Created by liumian on 2016/11/20.
 */
@Component
@Aspect
public class AccessAspect {

    private Log log = LogFactory.getLog(AccessAspect.class);

    //进行流量控制
    @Autowired
    private Semaphore semaphore;

    @Pointcut("execution(* win.star.business.controller..*(..))")
    public void controller() {
    }

    @Around(value = "controller()")
    public Object around(ProceedingJoinPoint joinPoint) {
        long start = System.currentTimeMillis();
        Object result = null;
        try {
            semaphore.acquire();

            Object[] args = joinPoint.getArgs();
            HttpServletRequest request = (HttpServletRequest) args[0];
            log.info(request.getRemoteAddr());
            log.info(request.getMethod());
            log.info(request.getRemotePort());
            if (args != null && args.length > 0) {
                args[1] = "这是改变以后的参数";
            }
            result = joinPoint.proceed(args);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            long end = System.currentTimeMillis();
            log.info(joinPoint + " 耗时：" + (end - start));
            semaphore.release();
            return result;
        }

    }


    private void checkPermission(){

    }


}
