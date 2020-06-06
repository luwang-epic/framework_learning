package com.framework.learning.guice.demo.scope;

import com.framework.learning.guice.demo.annotation.LoggedAnnotation;
import com.google.common.cache.AbstractCache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 缓存类
 *
 * @author wanglu
 * @date 2020/06/06
 */
public class GuiceDemoCache extends AbstractCache<String, String> {

    private final Map<String, String> keyValues = new ConcurrentHashMap<>();

    @Override
    public void put(String key, String value) {
        keyValues.put(key, value);
    }

    @Override @LoggedAnnotation
    public String getIfPresent(Object key) {
        return keyValues.get(key);
    }
}
