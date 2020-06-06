package com.framework.learning.guice.demo.bind;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.TypeLiteral;
import com.google.inject.multibindings.MapBinder;
import com.google.inject.multibindings.Multibinder;
import com.google.inject.name.Named;
import com.google.inject.name.Names;

import java.util.Arrays;
import java.util.List;

/**
 * bind模块相关的依赖注入
 *
 * @author wanglu
 * @date 2020/06/06
 */
public class BindModule extends AbstractModule {

    /*
        guice的绑定方式包括：
            1. 类名绑定
            2. 实例绑定
            3. 连接绑定
            4. Provider绑定
            5. 命名绑定
            6. 泛型绑定
            7. 集合绑定
     */

    @Override
    protected void configure() {
        // 类名绑定
        bind(PriceService.class).to(PriceServiceImpl.class);

        //  实例绑定
        // bind(PriceService.class).toInstance(new PriceServiceImpl());

        // 连接绑定
        // 这样PriceService对应的对象就是下面我们new的PriceServiceImpl实例，
        // 这种方式多用于测试，一般会放到测试中， 具体见：PriceServiceImplTest
//        bind(PriceServiceImpl.class).toInstance(new PriceServiceImpl() {
//            @Override
//            public Long getPrice(Long orderId) {
//                return 1L;
//            }
//        });

        // bind也可以命名绑定
        // bind(PriceService.class).annotatedWith(Names.named("name_bind")).to(PriceServiceImpl.class);

        // 泛型绑定, 其实前面的getSupportedPrices例子中也是一种泛型绑定
        bind(new TypeLiteral<List<String>>(){}).toInstance(Arrays.asList("a", "b"));

        // 集合绑定, 其实上面的也是一种集合绑定, 获取的是一个Set<Integer>对象
        Multibinder<Integer> collectionBinder = Multibinder.newSetBinder(binder(), Integer.class);
        collectionBinder.addBinding().toInstance(1);
        collectionBinder.addBinding().toInstance(2);
        // map绑定，可以使用MapBinder来实现
        //MapBinder.newMapBinder()
    }

    /**
     * Provider绑定
     *      该绑定可以带有参数， 该参数guice会自动在上下文中查找并绑定上
     */
    @Provides
    List<Long> getSupportedPrices(PriceService priceService) {
        return priceService.getSupportedPrices();
    }

    /**
     * 命名绑定
     */
    @Provides @Named("getMaxSupportedPrice")
    Long getMaxSupportedPrice(PriceService priceService) {
        long maxPrice = 0;
        for (Long price : priceService.getSupportedPrices()) {
            if (maxPrice < price) {
                maxPrice = price;
            }
        }
        return maxPrice;
    }
}
