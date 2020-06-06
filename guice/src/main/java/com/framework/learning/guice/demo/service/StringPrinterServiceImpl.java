package com.framework.learning.guice.demo.service;

import com.framework.learning.guice.demo.annotation.HelloWorldAnnotation;
import com.framework.learning.guice.demo.annotation.TestAnnotation;
import com.google.inject.Inject;

/**
 * 打印hello world 服务
 *
 * @author wanglu
 * @date 2020/06/06
 */
public class StringPrinterServiceImpl implements StringPrinterService {

    private PrintStreamWriterService printStreamWriterService;
    private StringProviderService stringProviderService;

    /**
     * 构造函数注入
     */
    @Inject
    public StringPrinterServiceImpl(PrintStreamWriterService printStreamWriterService,
                                    @TestAnnotation StringProviderService stringProviderService) {
        this.printStreamWriterService = printStreamWriterService;
        this.stringProviderService = stringProviderService;
    }

    private void printHelloWorld() {
        printStreamWriterService.print(stringProviderService.get());
    }

    @Override
    public void run() {
        printHelloWorld();
    }
}
