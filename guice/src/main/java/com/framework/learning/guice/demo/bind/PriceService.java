package com.framework.learning.guice.demo.bind;

import java.util.List;

/**
 * 价格服务， 用于演示绑定方式
 *
 * @author wanglu
 * @date 2020/06/06
 */
public interface PriceService {

    Long getPrice(Long orderId);

    List<Long> getSupportedPrices();
}
