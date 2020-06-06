package com.framework.learning.guice.demo.service;

import com.google.inject.Guice;
import org.junit.Test;

/**
 * 测试PrintStreamWriterServiceImpl
 *
 * @author wanglu
 * @date 2020/06/06
 */
public class PrintStreamWriterServiceImplTest {

    /**
     * 这种方式单侧，每个方法都需要Guice.createInjector一下，很不方便
     * 可以通过在测试@Before方法中注入，具体见PrintStreamWriterServiceImplTest2
     */

    @Test
    public void testPrint1() {
        PrintStreamWriterService printStreamWriterService = Guice.createInjector(new HelloWorldModule())
                                                                     .getInstance(PrintStreamWriterService.class);
        printStreamWriterService.print("hi");
    }

    @Test
    public void testPrint2() {
        PrintStreamWriterServiceImpl printStreamWriterService = Guice.createInjector(new HelloWorldModule())
                .getInstance(PrintStreamWriterServiceImpl.class);
        printStreamWriterService.print("hello");
    }
}
