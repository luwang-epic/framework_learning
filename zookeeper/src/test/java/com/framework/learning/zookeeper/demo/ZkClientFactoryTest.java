package com.framework.learning.zookeeper.demo;

import org.junit.Test;

/**
 * 演示具体的使用
 * @author wanglu
 * @date 2019/11/30
 */
public class ZkClientFactoryTest {

    @Test
    public void testCreatePath() throws Exception {
        String path = "/create_client";
        ZkClientFactory.createPath(path);
    }

    @Test
    public void testUpdatePath() throws Exception {
        String path = "/create_client";
        ZkClientFactory.updatePath(path);
    }
}
