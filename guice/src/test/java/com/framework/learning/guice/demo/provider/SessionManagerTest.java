package com.framework.learning.guice.demo.provider;

import com.google.inject.Guice;
import com.google.inject.Inject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * 测试SessionManager
 *
 * @author wanglu
 * @date 2020/06/06
 */
public class SessionManagerTest {

    @Inject
    private SessionManager sessionManager;

    @Before
    public void before() {
        Guice.createInjector(new ProviderModule()).injectMembers(this);
    }

    @Test
    public void testGetSessionIdInProvider() throws Exception {
        Long sessionId1 = sessionManager.getSessionIdInProvider();
        // provider中的对象有生命周期的概念，调用get的时候才会去获取，将会拿到不同的对象
        Thread.sleep(1000);
        Long sessionId2 = sessionManager.getSessionIdInProvider();
        Assert.assertNotEquals(sessionId1.longValue(), sessionId2.longValue());
    }

    @Test
    public void testGetSessionId() throws Exception {
        Long sessionId1 = sessionManager.getSessionId();
        Thread.sleep(1000);
        Long sessionId2 = sessionManager.getSessionId();
        Assert.assertEquals(sessionId1.longValue(), sessionId2.longValue());
    }

    @Test
    public void testGetConfigureSessionId() throws Exception {
        Double sessionId1 = sessionManager.getConfigureSessionId();
        Thread.sleep(1000);
        Double sessionId2 = sessionManager.getConfigureSessionId();
        Assert.assertEquals(sessionId1, sessionId2, 0.0000000001);
    }
}
