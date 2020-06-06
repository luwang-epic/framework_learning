package com.framework.learning.guice.demo.bind;

import java.util.Arrays;
import java.util.List;

/**
 * PriceService的一个实现
 *
 * @author wanglu
 * @date 2020/06/06
 */
public class PriceServiceImpl implements PriceService {

    @Override
    public Long getPrice(Long orderId) {
        throw new RuntimeException("待实现");
    }

    @Override
    public List<Long> getSupportedPrices() {
        return Arrays.asList(1L, 2L);
    }
}
