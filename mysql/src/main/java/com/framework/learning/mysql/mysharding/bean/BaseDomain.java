package com.framework.learning.mysql.mysharding.bean;

import com.framework.learning.mysql.mysharding.datasource.MultiDataSourceHolder;

/**
 * 基础类，分库分表需要do需要基础该类
 * @author wanglu
 * @date 2020/01/01
 */
public class BaseDomain {
    private String tableSuffix;

    public String getTableSuffix() {
        this.tableSuffix = MultiDataSourceHolder.getTableIndex();
        return tableSuffix;
    }

    public void setTableSuffix(String tableSuffix) {
        this.tableSuffix = tableSuffix;
    }
}
