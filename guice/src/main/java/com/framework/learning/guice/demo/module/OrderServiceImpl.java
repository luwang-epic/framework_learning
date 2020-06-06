package com.framework.learning.guice.demo.module;

/**
 * OrderService的一个实现
 *
 * @author wanglu
 * @date 2020/06/06
 */
public class OrderServiceImpl implements OrderService {

    @Override
    public Long generateOrderId() {
        throw new RuntimeException("还没有想好怎么实现");
    }
}
