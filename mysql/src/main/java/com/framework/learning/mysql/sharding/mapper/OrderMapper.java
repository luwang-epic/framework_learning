package com.framework.learning.mysql.sharding.mapper;

import com.framework.learning.mysql.sharding.bean.Order;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

/**
 * @author wanglu
 * @date 2020/01/01
 */
public interface OrderMapper {
    
    /**
     * Order类继承BaseDomain，因此可以取到tableSuffix的值
     */
    @Insert("insert into order${order.tableSuffix}(order_id, user_id, money) values(#{order.orderId}, #{order.userId}, #{order.money})")
    void insertOrder(@Param("order") Order order);
}
