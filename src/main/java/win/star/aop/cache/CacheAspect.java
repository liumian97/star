package win.star.aop.cache;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by liumian on 2016/11/20.
 */
//@Component
//@Aspect
public class CacheAspect {

    private Log log = LogFactory.getLog(CacheAspect.class);

    @Autowired
    @Qualifier("cacheMap")
    private Map<String,Object> cacheMap;

    @Pointcut("execution(* win.star.business.service..*(..))")
    public void service(){
    }

    @Around("service()")
    public Object cacheSupport(ProceedingJoinPoint joinPoint){
        String key = buildKey(joinPoint.toString(),joinPoint.getArgs());
        if (containsCache(key)){
            log.info("hit cache ....");
            return getCache(key);
        }else {
            log.info("not hit cache ...");
            Object result = null;
            try {
                result = joinPoint.proceed();
                setCache(key, result);
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
            return result;
        }
    }


    private boolean containsCache(String key){
        return cacheMap.containsKey(key);
    }

    private Object setCache(String key,Object result){
        return cacheMap.put(key,result);
    }

    private Object getCache(String key){

        return cacheMap.get(key);
    }

    private String buildKey(String methodName,Object[] args){
        StringBuilder key = new StringBuilder(methodName);
        for (Object arg:args){
            key.append(arg.toString());
        }
        return key.toString();
    }

}
