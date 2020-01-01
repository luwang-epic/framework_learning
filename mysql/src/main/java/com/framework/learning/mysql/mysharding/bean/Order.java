package com.framework.learning.mysql.mysharding.bean;

import lombok.Data;

/**
 * 订单do，对应order_XXXX表
 *      通过继承BaseDomain获得应该操作表的下标
 * @author wanglu
 * @date 2020/01/01
 */
@Data
public class Order extends BaseDomain {
    private int userId;
    private double money;
    private String orderId;
}
