package com.framework.learning.guice.demo.service;

import com.google.inject.Guice;
import com.google.inject.Inject;
import org.junit.Before;
import org.junit.Test;

/**
 * 测试PrintStreamWriterServiceImpl
 *
 * @author wanglu
 * @date 2020/06/06
 */
public class PrintStreamWriterServiceImplTest2 {

    @Inject
    private PrintStreamWriterService printStreamWriterService;

    @Inject
    private PrintStreamWriterServiceImpl printStreamWriterServiceImpl;

    @Before
    public void before() {
        Guice.createInjector(new HelloWorldModule())
             // 注入所有成员到该类中
             .injectMembers(this);
    }

    @Test
    public void testPrint1() {
        printStreamWriterService.print("hi");
    }

    @Test
    public void testPrint2() {
        printStreamWriterServiceImpl.print("hello");
    }
}
