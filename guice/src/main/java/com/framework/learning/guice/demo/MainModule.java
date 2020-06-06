package com.framework.learning.guice.demo;

import com.framework.learning.guice.demo.provider.ProviderModule;
import com.framework.learning.guice.demo.service.HelloWorldModule;
import com.google.inject.AbstractModule;


/**
 * 用来管理依赖注入
 *
 * @author wanglu
 * @date 2020/06/06
 */
public class MainModule extends AbstractModule {
    @Override
    protected void configure() {
        /*
         * 当有很多模块的时候将会导致臃肿，因此需要建立子module，然后在主module中引入
         */

        // 引入子module
        install(new HelloWorldModule());
        install(new ProviderModule());
    }


}
