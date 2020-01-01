package com.framework.learning.mysql.mysharding.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashMap;
import java.util.Map;

/**
 * 分库分表属性设置
 * @author wanglu
 * @date 2019/12/31
 */
@ConfigurationProperties(prefix = "tuling.dsroutingset")
@Data
public class TulingDsRoutingSetProperties {

    private int dataSourceNum;
    private int tableNum;
    private String routingField;
    private String tableSuffixStyle;
    private String tableSuffixConnect;
    private String routingStrategy;

    private Map<Integer, String> dataSourceKeysMapping = new HashMap<>();

}
