package com.framework.learning.guice.demo.bind;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Provides;
import com.google.inject.name.Named;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 测试PriceServiceImpl类
 *
 * @author wanglu
 * @date 2020/06/06
 */
public class PriceServiceImplTest {
    @Inject
    private PriceServiceImpl priceService;

    @Inject
    private List<Long> supportedPrices;

    @Inject
    private Provider<List<Long>> supportedPricesProvider;

    @Inject
    private Provider<List<String>> listStringProvider;

    @Inject @Named("getMaxSupportedPrice") Long maxSupportedPrice;

    @Inject
    private Set<Integer> collectionBind;

    @Before
    public void before() {
        Guice.createInjector(new BindModule(),
                new AbstractModule() {
                    @Override
                    protected void configure() {
                        bind(PriceServiceImpl.class).toInstance(new PriceServiceImpl() {
                            @Override
                            public Long getPrice(Long orderId) {
                                return orderId;
                            }
                        });
                    }
                })
             .injectMembers(this);
    }

    @Test
    public void testGetPrice() {
        long orderId = 111L;
        Long price = priceService.getPrice(orderId);
        Assert.assertEquals(orderId, price.longValue());
    }


    @Test
    public void testGetSupportedPrices() {
        Assert.assertEquals(1, supportedPrices.get(0).longValue());
        Assert.assertEquals(2, supportedPrices.get(1).longValue());

        Assert.assertEquals(1, supportedPricesProvider.get().get(0).longValue());
        Assert.assertEquals(2, supportedPricesProvider.get().get(1).longValue());
    }

    @Test
    public void testMaxSupportPrice() {
        Assert.assertEquals(2, maxSupportedPrice.longValue());
    }

    @Test
    public void testListStringProvider() {
        Assert.assertEquals(Arrays.asList("a", "b"), listStringProvider.get());
    }

    @Test
    public void testCollectionBind() {
        Assert.assertEquals(2, collectionBind.size());
    }
}
