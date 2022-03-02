package com.example.tenant.dataSource;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;

@Slf4j
@Aspect
@Component
@Order(1) // 请注意：这里order一定要小于tx:annotation-driven的order，即先执行DynamicDataSourceAspectAdvice切面，再执行事务切面，才能获取到最终的数据源
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class DynamicDataSourceAspect {
 
    @Around("execution(* com.example.*.controller.*.*(..))")
    public Object doAround(ProceedingJoinPoint jp) throws Throwable {
        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpSession session= sra.getRequest().getSession(true);
        String tenantId = (String)session.getAttribute("tenantId");
        
        log.info("当前租户Id:{}", tenantId);
        DynamicDataSourceContextHolder.setDataSourceKey(tenantId);
        Object result = jp.proceed();
        DynamicDataSourceContextHolder.clearDataSourceKey();
        return result;
    }
}
