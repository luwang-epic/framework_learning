package com.framework.learning.guice.demo;

/**
 * 提供从抽象到具体的实现服务
 *
 * @author wanglu
 * @date 2020/06/06
 */
@Deprecated
public class Configuration {

    /**
     * 通过这种方式来管理类，需要自己维护，不方便，而且当类很多时，容易混乱 可以采用guice来管理依赖注入问题
     */
//    public static StringPrinterService getHelloWorldPrinter() {
//        return new StringPrinterServiceImpl(
//                new PrintStreamWriterServiceImpl(System.out),
//                new StringProviderService() {
//                    @Override
//                    public String get() {
//                        return "hello world";
//                    }
//                });
//    }

}
