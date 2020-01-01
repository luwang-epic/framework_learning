package com.framework.learning.mysql.mysharding.service.impl;

import com.framework.learning.mysql.mysharding.bean.Order;
import com.framework.learning.mysql.mysharding.mapper.OrderMapper;
import com.framework.learning.mysql.mysharding.service.OrderService;
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
