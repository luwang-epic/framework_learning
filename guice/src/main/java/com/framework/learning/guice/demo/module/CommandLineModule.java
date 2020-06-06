package com.framework.learning.guice.demo.module;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.name.Named;

import java.util.Arrays;
import java.util.List;

/**
 * 结束命令行参数
 *
 * @author wanglu
 * @date 2020/06/06
 */
public class CommandLineModule extends AbstractModule {
    private final String[] args;

    public CommandLineModule(String[] args) {
        this.args = args;
    }

    @Override
    protected void configure() {

    }

    @Provides @Named("args") List<String> getCommandLineArgs() {
        return Arrays.asList(args);
    }
}
