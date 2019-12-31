package com.framework.learning.mysql.sharding.aspect;

import com.framework.learning.mysql.sharding.annotation.ShardingRouter;
import com.framework.learning.mysql.sharding.core.ITulingRouting;
import com.framework.learning.mysql.sharding.datasource.MultiDataSourceHolder;
import com.framework.learning.mysql.sharding.exception.ParamsNotContainsRoutingFieldException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * ShadingRouting注解的切面
 * @author wanglu
 * @date 2020/01/01
 */
@Component
@Aspect
@Slf4j
public class ShadingRoutingAspect {

    @Autowired
    private ITulingRouting routing;

    @Pointcut("@annotation(com.framework.learning.mysql.sharding.annotation.ShardingRouter)")
    public void pointCut() {}

    @Before("pointCut()")
    public void before(JoinPoint joinPoint) throws Exception {
        //获取方法调用名称
        Method method = getInvokeMethod(joinPoint);

        //获取方法指定的注解
        ShardingRouter shardingRouter = method.getAnnotation(ShardingRouter.class);
        //获取指定的路由key
        String routingField = shardingRouter.routingField();

        //获取方法入参
        Object[] args = joinPoint.getArgs();

        boolean havingRoutingField = false;
        if(null != args && args.length > 0) {
            for(int index=0; index<args.length; index++) {
                String routingFieldValue = BeanUtils.getProperty(args[index], routingField);
                if(!StringUtils.isEmpty(routingFieldValue)) {
                    String dbKey = routing.calDataSourceKey(routingFieldValue);
                    String tableIndex = routing.calTableKey(routingFieldValue);
                    log.info("选择的DbKey: {}, tableKey: {}", dbKey, tableIndex);
                    havingRoutingField = true;
                    break;
                }
            }
        }

        //判断入参中没有路由字段
        if(!havingRoutingField) {
            log.warn("入参: {} 中没有包含路由字段: {}", args, routingField);
            throw new ParamsNotContainsRoutingFieldException("参数没有包含分库分表路由字段异常!");
        }
    }

    private Method getInvokeMethod(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        return methodSignature.getMethod();
    }

    /**
     * 清除线程缓存
     */
    @After("pointCut()")
    public void methodAfter(JoinPoint joinPoint) {
        MultiDataSourceHolder.clearDataSourceKey();
        MultiDataSourceHolder.clearTableIndex();
        log.info("清除线程缓存的DataSourceKey和TableIndex，防止内存泄漏...");
    }
}
