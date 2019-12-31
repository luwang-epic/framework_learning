package com.framework.learning.mysql.sharding.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.lang.Nullable;

/**
 * 自定义的用于分库分表的数据源
 *  AbstractRoutingDataSource该类用于做路由的数据源类
 * @author wanglu
 * @date 2019/12/31
 */
public class TulingMultiDataSource extends AbstractRoutingDataSource {
    @Nullable
    @Override
    protected Object determineCurrentLookupKey() {
        //去除数据源的key，决定调用哪个数据源
        return MultiDataSourceHolder.getDataSourceKey();
    }
}
