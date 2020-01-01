package com.framework.learning.mysql.mysharding.core;

import com.framework.learning.mysql.mysharding.datasource.MultiDataSourceHolder;
import lombok.extern.slf4j.Slf4j;

/**
 * 多库多表策略
 * @author wanglu
 * @date 2019/12/31
 */
@Slf4j
public class RoutingDsAndTbStrategy extends AbstractTulingRouting {
    @Override
    public String calDataSourceKey(String routingFieldValue) {
        String dataSourceKey = null;

        Integer routingFieldHashCode = getRoutingFileHashCode(routingFieldValue);
        //定位库的索引值
        Integer dsIndex = routingFieldHashCode % getTulingDsRoutingSetProperties().getDataSourceNum();
        //根据库的索引值定位数据源的key
        dataSourceKey = getTulingDsRoutingSetProperties().getDataSourceKeysMapping().get(dsIndex);

        //放入线程变量
        MultiDataSourceHolder.setDataSourceKey(dataSourceKey);
        log.info("根据路由字段: {} 的值: {}, 计算出数据库索引值: {}, 数据源key的值: {}", getTulingDsRoutingSetProperties().getRoutingField(), routingFieldValue, dsIndex, dataSourceKey);

        return dataSourceKey;
    }

    @Override
    public String calTableKey(String routingFieldValue) {

        Integer routingFieldHashCode = getRoutingFileHashCode(routingFieldValue);
        //定位表的索引值
        Integer tableIndex = routingFieldHashCode % getTulingDsRoutingSetProperties().getTableNum();

        String tableSuffix = getFormatTableSuffix(tableIndex);

        MultiDataSourceHolder.setTableIndex(tableSuffix);

        log.info("根据路由字段: {} 的值: {}, 计算出数据表索引值: {}, 数据表后缀的值: {}", getTulingDsRoutingSetProperties().getRoutingField(), routingFieldValue, tableIndex, tableSuffix);

        return tableSuffix;
    }
}
