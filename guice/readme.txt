Google Guice 是一个轻量级的依赖注入框架
        1. 取消xml
        2. 取消bean的概念
        3. 使用constructor来注入
        4. 泛型支持
        5. 一个专注于Dependency Injection的框架


优点：
    1. 代码量少
    2. 性能优异
    3. 支持泛型
    4. Constructor绑定： Immutable objects， 不在需要getter/setter
    5. 强类型 如：区分List<Integer> 和 List<String>
    6. 易于Refactor（重构）

缺点：
    1. Module和绑定规则不易理解
    2. 文档教程少，社区资源少
    3. 无妨方便搭出特殊结构： 如循环依赖， 需要通过其他方式解决，如：解除循环依赖等
    4. Guice仅仅是Dependency injection framework， 而Spring涵盖较多

spring和guice也可以互相整合：
    通过SpringApplicationContext来获取spring中的bean，然后注入到guice中
        将applicationContext对象通过Module的构造函数传入到guice中，然后通过@Providers注解写一个方法，
        通过applicationContext.getBean("**")方法来获取，从而绑定到guice中
    通过Injector来获取guice中的对象，然后注入到spring中
        将Injector对象通过Spring的Bean注解注入到Spring容器中，然后通过injector.getInstances(***)方法来注入到spring容器中
