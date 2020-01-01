package com.framework.learning.mysql.mysharding.core;

/**
 * @author wanglu
 * @date 2019/12/31
 */
public class RoutingDsStrategy extends AbstractTulingRouting {
    @Override
    public String calDataSourceKey(String routingFieldValue) {
        return null;
    }

    @Override
    public String calTableKey(String routingFieldValue) {
        return null;
    }
}
