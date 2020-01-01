package com.framework.learning.mysql.mysharding.controller;

import com.framework.learning.mysql.mysharding.annotation.ShardingRouter;
import com.framework.learning.mysql.mysharding.bean.Order;
import com.framework.learning.mysql.mysharding.service.impl.OrderServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 启动类
 * @author wanglu
 * @date 2019/12/31
 */
@Slf4j
@RestController
@RequestMapping("/mysql/sharding")
public class ShardingController {

    @Autowired
    private OrderServiceImpl orderService;

    /**
     * ShardingRouter自定义注解，用于获取根据分库分表指定的字段的值，如：orderId的值
     *      需要使用spring aop来做成切面
     */
    @PostMapping("/order")
    @ShardingRouter
    public Map<String, Object> insertOrder(@RequestBody Order order) {
        log.info("start to invoke insertOrder with {}", order);
        //插入订单
        orderService.insertOrder(order);
        log.info("insert order success...");

        Map<String, Object> data = new HashMap<>();
        data.put("return_code", 200);
        data.put("msg", "插入数据成功！");
        return data;
    }
}
