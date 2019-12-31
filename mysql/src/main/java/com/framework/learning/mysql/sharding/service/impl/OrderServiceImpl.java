package com.framework.learning.mysql.sharding.service.impl;

import com.framework.learning.mysql.sharding.bean.Order;
import com.framework.learning.mysql.sharding.mapper.OrderMapper;
import com.framework.learning.mysql.sharding.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wanglu
 * @date 2020/01/01
 */
@Service("orderService")
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public void insertOrder(Order order) {
        orderMapper.insertOrder(order);
    }
}
