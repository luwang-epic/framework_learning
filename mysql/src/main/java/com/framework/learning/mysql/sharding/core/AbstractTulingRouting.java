package com.framework.learning.mysql.sharding.core;

import com.framework.learning.mysql.sharding.config.TulingDsRoutingSetProperties;
import com.framework.learning.mysql.sharding.constant.ShardingConstant;
import com.framework.learning.mysql.sharding.exception.LoadRoutingStrategyUnMatchException;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * 路由规则抽象类，公共的实现放在这里
 * @author wanglu
 * @date 2019/12/31
 */
@Slf4j
@EnableConfigurationProperties
@Data
public abstract class AbstractTulingRouting implements ITulingRouting, InitializingBean {

    @Autowired
    private TulingDsRoutingSetProperties tulingDsRoutingSetProperties;

    @Override
    public Integer getRoutingFileHashCode(String routingFieldValue) {
        return Math.abs(routingFieldValue.hashCode());
    }

    @Override
    public String getFormatTableSuffix(Integer tableIndex) {
        StringBuffer stringBuffer = new StringBuffer(tulingDsRoutingSetProperties.getTableSuffixConnect());
        stringBuffer.append(String.format(tulingDsRoutingSetProperties.getTableSuffixStyle(), tableIndex));
        return stringBuffer.toString();
    }

    /**
     * 各个数据初始化后执行该方法，可以进行一些检查，例如：分库分表的配置是否正确
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws LoadRoutingStrategyUnMatchException {
        //一些检查的方法
        switch (tulingDsRoutingSetProperties.getRoutingStrategy()) {
            case ShardingConstant.ROUTING_DS_TABLE_STRATEGY:
                checkRoutingDsTableStrategyConfig();
                break;
            case ShardingConstant.ROUTING_DS_STRATEGY:
                checkRoutingDsStrategyConfig();
                break;
            case ShardingConstant.ROUTING_TABLE_STRATEGY:
                checkRoutingTableStrategyConfig();
                break;
            default:
                throw new LoadRoutingStrategyUnMatchException("不支持的路由策略");
        }
    }

    /**
     * 检查多库多表路由配置
     */
    private void checkRoutingDsTableStrategyConfig() {
        if(tulingDsRoutingSetProperties.getTableNum() <=1 || tulingDsRoutingSetProperties.getDataSourceNum() <= 1) {
            String errMsg = String.format("你配置的路由策略是多库多表的，但是数据库个数: %s 和表个数：%s 不匹配", tulingDsRoutingSetProperties.getDataSourceNum(), tulingDsRoutingSetProperties.getTableNum());
            log.error(errMsg);
            throw new LoadRoutingStrategyUnMatchException(errMsg);
        }
    }

    /**
     * 检查一库多表路由配置
     */
    private void checkRoutingDsStrategyConfig() {
        if(tulingDsRoutingSetProperties.getTableNum() <=1 || tulingDsRoutingSetProperties.getDataSourceNum() != 1) {
            String errMsg = String.format("你配置的路由策略是一库多表的，但是数据库个数: %s 和表个数：%s 不匹配", tulingDsRoutingSetProperties.getDataSourceNum(), tulingDsRoutingSetProperties.getTableNum());
            log.error(errMsg);
            throw new LoadRoutingStrategyUnMatchException(errMsg);
        }
    }

    /**
     * 检查多库一表路由配置
     */
    private void checkRoutingTableStrategyConfig() {
        if(tulingDsRoutingSetProperties.getTableNum() !=1 || tulingDsRoutingSetProperties.getDataSourceNum() <= 1) {
            String errMsg = String.format("你配置的路由策略是多库一表的，但是数据库个数: %s 和表个数：%s 不匹配", tulingDsRoutingSetProperties.getDataSourceNum(), tulingDsRoutingSetProperties.getTableNum());
            log.error(errMsg);
            throw new LoadRoutingStrategyUnMatchException(errMsg);
        }
    }
}
