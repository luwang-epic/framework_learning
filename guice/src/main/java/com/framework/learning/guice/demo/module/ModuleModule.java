package com.framework.learning.guice.demo.module;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;

/**
 * module模块相关的依赖注入
 *
 * @author wanglu
 * @date 2020/06/06
 */
public class ModuleModule extends AbstractModule {

    /*
        module的相互关系
        1. 并列 当出现绑定冲突时，报错
            Guice.createInjector(module1, module2, ...)
        2. 嵌套 当出现绑定冲突时，报错
            install(module)
        3. 覆盖 当出现绑定冲突时，覆盖
            Modules.override(module1, module2, ...).with(module11, module22, ...)

        注：不管哪种方式，一个module中同时绑定一个类都会由于冲出而报错，如：
                bind(a.class).to(new A()); // 1
                bind(a.class).to(new AA()); // 2
            但是如果1在module1中，2在module11中，采用覆盖绑定，module11会覆盖module1中的绑定
     */

    @Override
    protected void configure() {
        bind(OrderService.class).to(OrderServiceImpl.class);
    }

}
