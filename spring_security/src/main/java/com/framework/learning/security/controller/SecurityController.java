package com.framework.learning.security.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * controller代码
 * @author wanglu
 * @date 2020/01/12
 */
@RestController
public class SecurityController {

    @RequestMapping("/queryOrder")
    public String queryOrder() {
        return "查询订单成功";
    }

    @RequestMapping("/addOrder")
    public String addOrder() {
        return "添加订单成功";
    }

    @RequestMapping("/updateOrder")
    public String updateOrder() {
        return "更新订单成功";
    }

    @RequestMapping("/deleteOrder")
    public String deleteOrder() {
        return "删除订单成功";
    }

}
