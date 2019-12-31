package com.framework.learning.mysql.sharding.core;

/**
 * 分库分表路由接口，定义一些通用的方法
 * @author wanglu
 * @date 2019/12/31
 */
public interface ITulingRouting {

    /**
     * 计算落入哪个数据库
     */
    String calDataSourceKey(String routingFieldValue);

    /**
     * 计算落入哪个表
     */
    String calTableKey(String routingFieldValue);

    /**
     * 计算routingField的hashcode值
     */
    Integer getRoutingFileHashCode(String routingFieldValue);

    /**
     * 计算表的后缀格式
     */
    String getFormatTableSuffix(Integer routingFieldValue);
}
