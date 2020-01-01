package com.framework.learning.mysql.mysharding.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 接受配置文件中的多数据源的配置
 * @author wanglu
 * @date 2019/12/31
 */
@ConfigurationProperties(prefix = "spring.datasource")
@Data
public class TulingDruidProperties {
    private String druid00username;
    private String druid00password;
    private String druid00jdbcUrl;
    private String druid00driverClass;

    private String druid01username;
    private String druid01password;
    private String druid01jdbcUrl;
    private String druid01driverClass;

    private String druid02username;
    private String druid02password;
    private String druid02jdbcUrl;
    private String druid02driverClass;
}
