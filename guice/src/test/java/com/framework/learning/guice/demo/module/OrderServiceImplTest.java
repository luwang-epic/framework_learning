package com.framework.learning.guice.demo.module;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.google.inject.util.Modules;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * 测试 OrderServiceImpl
 *
 * @author wanglu
 * @date 2020/06/06
 */
public class OrderServiceImplTest {

    static class MockModuleModule extends AbstractModule {
        @Override
        protected void configure() {
            bind(OrderService.class).toInstance(new OrderServiceImpl() {
                @Override
                public Long generateOrderId() {
                    return 1L;
                }
            });
        }
    }

    @Inject
    private OrderService orderService;

    @Inject @Named("args")
    private List<String> args;

    @Before
    public void before() {
        // 模拟命令行参数
        String[] args = new String[] {"a", "b"};
        // 通过覆盖的方式，让我们可以测试
        Guice.createInjector(Modules.override(new ModuleModule(), new CommandLineModule(args)).with(new MockModuleModule())).injectMembers(this);
    }

    @Test
    public void testGenerateOrderId() {
        Long orderId = orderService.generateOrderId();
        Assert.assertEquals(1, orderId.longValue());
    }

    @Test
    public void testArgs() {
        Assert.assertEquals(Arrays.asList("a", "b"), args);
    }

}
