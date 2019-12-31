package com.framework.learning.mysql.sharding.service;

import com.framework.learning.mysql.sharding.bean.Order;

/**
 * 订单服务
 * @author wanglu
 * @date 2020/01/01
 */
public interface OrderService {
    void insertOrder(Order order);
}
