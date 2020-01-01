package com.framework.learning.mysql.shardingjdbc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 使用sharding jdbc框架来实现分库分表的启动类
 * @author wanglu
 * @date 2020/01/01
 */
@SpringBootApplication
@MapperScan("com.framework.learning.mysql.shardingjdbc.dao")
public class ShardingJdbcApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShardingJdbcApplication.class, args);
    }
}
