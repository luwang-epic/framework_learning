package com.framework.learning.mysql.sharding.datasource;

import lombok.extern.slf4j.Slf4j;

/**
 * 多数据源key， 缓存类
 * @author wanglu
 * @date 2019/12/31
 */
@Slf4j
public class MultiDataSourceHolder {
    /** 请求是多线程了，为了线程安全，定义为线程本地化的 **/
    private final static ThreadLocal<String> dataSourceKeyHolder = new ThreadLocal<>();
    private final static ThreadLocal<String> tableIndexHolder = new ThreadLocal<>();

    /**
     * 保存数据源的key
     */
    public static void setDataSourceKey(String dataSourceKey) {
        dataSourceKeyHolder.set(dataSourceKey);
    }

    /**
     * 从ThreadLocal中取出key
     */
    public static String getDataSourceKey() {
        return dataSourceKeyHolder.get();
    }

    /**
     * 清楚key，防止内存泄漏
     */
    public static void clearDataSourceKey() {
        dataSourceKeyHolder.remove();
    }

    public static void setTableIndex(String tableIndex) {
        tableIndexHolder.set(tableIndex);
    }
    public static String getTableIndex() {
        return tableIndexHolder.get();
    }
    public static void clearTableIndex() {
        tableIndexHolder.remove();
    }

}
