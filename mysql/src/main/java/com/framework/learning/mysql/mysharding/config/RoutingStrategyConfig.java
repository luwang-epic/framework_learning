package com.framework.learning.mysql.mysharding.config;

import com.framework.learning.mysql.mysharding.constant.ShardingConstant;
import com.framework.learning.mysql.mysharding.core.ITulingRouting;
import com.framework.learning.mysql.mysharding.core.RoutingDsAndTbStrategy;
import com.framework.learning.mysql.mysharding.core.RoutingDsStrategy;
import com.framework.learning.mysql.mysharding.core.RoutingTbStrategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 根据不同的配置，激活不同的bean
 * @author wanglu
 * @date 2020/01/01
 */
@Slf4j
@Configuration
public class RoutingStrategyConfig {
    /**
     * 当tuling.dsroutingset.routingStrategy = ROUTING_DS_TABLE_STRATEGY是，加载多库多表策略RoutingDsAndTbStrategy类
     */
    @Bean
    @ConditionalOnProperty(prefix = "tuling.dsroutingset", name = "routingStrategy", havingValue = ShardingConstant.ROUTING_DS_TABLE_STRATEGY)
    public ITulingRouting routingDsAndTbStrategy() {
        log.info("active RoutingDsAndTbStrategy...");
        return new RoutingDsAndTbStrategy();
    }

    /**
     * 当tuling.dsroutingset.routingStrategy = ROUTING_DS_STRATEGY，加载多库多表策略RoutingDsStrategy类
     */
    @Bean
    @ConditionalOnProperty(prefix = "tuling.dsroutingset", name = "routingStrategy", havingValue = ShardingConstant.ROUTING_DS_STRATEGY)
    public ITulingRouting routingDsStrategy() {
        log.info("active RoutingDsStrategy...");
        return new RoutingDsStrategy();
    }

    /**
     * 当tuling.dsroutingset.routingStrategy = ROUTING_TABLE_STRATEGY，加载多库多表策略RoutingTbStrategy类
     */
    @Bean
    @ConditionalOnProperty(prefix = "tuling.dsroutingset", name = "routingStrategy", havingValue = ShardingConstant.ROUTING_TABLE_STRATEGY)
    public ITulingRouting routingTbStrategy() {
        log.info("active RoutingTbStrategy...");
        return new RoutingTbStrategy();
    }
}
