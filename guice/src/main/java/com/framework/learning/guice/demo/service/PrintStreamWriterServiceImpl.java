package com.framework.learning.guice.demo.service;

import com.google.inject.Inject;

import java.io.PrintStream;

/**
 * 实现PrintStreamWriterService类
 *
 * @author wanglu
 * @date 2020/06/06
 */
public class PrintStreamWriterServiceImpl implements PrintStreamWriterService {

    /**
     * 成员变量注入， 不推荐使用这种方式注入，成员变量无法设置为final类型
     *      final类型只能在构造函数中赋值或者直接赋值，不能在set方法中更改，因此注入不了
     *      这种方式注入一般用于单元测试
     *  Inject 即可以是com.google.inject.Inject， 也可以是javax.Inject, guice实现了兼容
     */
    @Inject
    private PrintStream printStream;

    /**
     * 构造函数注入，推荐使用这种方式注入，然后将成员变量变成final类型，防止被修改
     */
//    @Inject
//    public PrintStreamWriterServiceImpl(PrintStream printStream) {
//        this.printStream = printStream;
//    }

    @Override
    public void print(String string) {
        printStream.println(string);
    }
}
