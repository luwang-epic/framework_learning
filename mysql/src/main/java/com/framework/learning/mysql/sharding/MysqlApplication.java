package com.framework.learning.mysql.sharding;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动类
 * @author wanglu
 * @date 2019/12/31
 */
@SpringBootApplication
@MapperScan("com.framework.learning.mysql.sharding.mapper")
public class MysqlApplication {

    /**
     分库分表
        1. 多库多表  ROUTING_DS_TABLE_STRATEGY ---> RoutingDsAndTbStrategy
        2. 多库一表  ROUTING_DS_STRATEGY ---> RoutingDsStrategy
        3. 一库多表  ROUTING_TABLE_STRATEGY ---> RoutingTbStrategy

        这些都应该有一些通用的规则， 接口类 ITulingRouting
            1. 计算到底数据落入到哪个库的方法
            2. 计算数据到底落入到具体库中的哪个表
            3. 计算后缀的拼接方式
     */

    public static void main(String[] args) {
        SpringApplication.run(MysqlApplication.class, args);
    }
}
