package com.framework.learning.guice.demo.scope;

import com.google.common.cache.Cache;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.name.Named;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * 测试GuiceDemoCache类
 *
 * @author wanglu
 * @date 2020/06/06
 */
public class GuiceDemoCacheTest {
    @Inject @Named("multi")
    private Cache<String, String> multiCache1;
    @Inject @Named("multi")
    private Cache<String, String> multiCache2;

    @Inject @Named("singleton")
    private Cache<String, String> singletonCache1;
    @Inject @Named("singleton")
    private Cache<String, String> singletonCache2;

    @Before
    public void before() {
        Guice.createInjector(new ScopeModule()).injectMembers(this);
    }

    @Test
    public void testMulti() {
        System.out.println(multiCache1);
        System.out.println(multiCache2);
        multiCache1.put("a", "aa");
        // 这里会调用两次aop的方法，不知道为什么？
        Assert.assertNull(multiCache2.getIfPresent("a"));
    }

    @Test
    public void testSingleton() {
        System.out.println(singletonCache1);
        System.out.println(singletonCache2);
        singletonCache1.put("a", "aa");
        Assert.assertEquals("aa", singletonCache2.getIfPresent("a"));
    }
}
