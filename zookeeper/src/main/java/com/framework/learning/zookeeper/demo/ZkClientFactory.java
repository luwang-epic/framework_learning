package com.framework.learning.zookeeper.demo;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

/**
 * 用于创建zk客户端
 *      关于zKClient和curator优劣和使用情况：https://www.cnblogs.com/crazymakercircle/p/10225739.html
 * @author wanglu
 * @date 2019/11/30
 */
@Slf4j
public class ZkClientFactory {

    /*
        可以供选择的java 客户端API 有：Zookeeper官方的java客户端API，第三方的java客户端API。

        Zookeeper官方的 客户端API提供了基本的操作，比如，创建会话、创建节点、读取节点、更新数据、删除节点和检查节点是否存在等。但对于开发人员来说，Zookeeper提供的基本操纵还是有一些不足之处。
        Zookeeper API不足之处如下：
            （1）Zookeeper的Watcher是一次性的，每次触发之后都需要重新进行注册；
            （2）Session超时之后没有实现重连机制；
            （3）异常处理繁琐，Zookeeper提供了很多异常，对于开发人员来说可能根本不知道该如何处理这些异常信息；
            （4）只提供了简单的byte[]数组的接口，没有提供针对对象级别的序列化；
            （5）创建节点时如果节点存在抛出异常，需要自行检查节点是否存在；
            （6）删除节点无法实现级联删除；
        第三方开源客户端主要有zkClient和Curator。

        ZkClient是一个开源客户端，在Zookeeper原生API接口的基础上进行了包装，更便于开发人员使用。zkClient客户端，在一些著名的互联网开源项目中，得到了应用，比如：阿里的分布式dubbo框架，对它进行了集成使用。

        zkClient解决了Zookeeper原生API接口的很多问题。比如，zkClient提供了更加简洁的api，实现了session会话超时重连、Watcher反复注册等问题。虽然ZkClient对原生API进行了封装，但也有它自身的不足之处。
        具体如下：
            （1）zkClient社区不活跃，文档不够完善，几乎没有参考文档；
            （2）异常处理简化（抛出RuntimeException）；
            （3）重试机制比较难用；
            （4）没有提供各种使用场景的参考实现；

        Curator是Netflix公司开源的一套Zookeeper客户端框架，和ZkClient一样，解决了非常底层的细节开发工作，包括连接重连、反复注册Watcher和NodeExistsException异常等。Curator是Apache基金会的顶级项目之一，Curator具有更加完善的文档，另外还提供了一套易用性和可读性更强的Fluent风格的客户端API框架。
        不止上面这些，Curator中还提供了Zookeeper各种应用场景（Recipe，如共享锁服务、Master选举机制和分布式计算器等）的抽象封装。
        另外，Curator供了一套非常优雅的链式调用api，对比ZkClient客户端 Api的使用，发现 Curator的api 优雅太多。
     */

    private final static String ZK_SERVER = "127.0.0.1:2181";

    /**
     *  创建节点
     *      可以使用inBackground()方法在后台线程中创建节点，这样就不会阻塞当前线程了
     */
    public static void createPath(String path) throws Exception {
        CuratorFramework client = getCuratorFramework();
        client.create()
                //如果父节点不存在，自动创建父节点，zk中父节点只能是持久节点，不能是临时借点
              .creatingParentsIfNeeded()
                //节点类型
              .withMode(CreateMode.PERSISTENT)
                //节点路径和数据
              .forPath(path, "test".getBytes());
    }

    /**
     * 获取节点数据
     */
    public static String getPath(String path) throws Exception {
        CuratorFramework client = getCuratorFramework();
        Stat stat = new Stat();
        //传入stat，让客户端存储最新的节点状态数据到该变量中
        return new String(client.getData().storingStatIn(stat).forPath(path));
    }

    /**
     * 更新节点数据
     */
    public static void updatePath(String path) throws Exception {
        CuratorFramework client = getCuratorFramework();
        Stat stat = new Stat();
        log.info("success get data: {}", new String(client.getData().storingStatIn(stat).forPath(path)));

        //该设置会成功，并返回新的版本号, 如果不带data参数，也会设置成功，此时版本号也会增加
        Stat newStat = client.setData().withVersion(stat.getVersion()).forPath(path, "new_data".getBytes());
        log.info("old version is {}, new version is {}", stat.getVersion(), newStat.getVersion());

        try {
            //这个设置会失败，因为使用了旧的版本号
            client.setData().withVersion(stat.getVersion()).forPath(path);
        }catch (Exception e) {
            log.error("Fail set node due to {}", e.getMessage());
        }
    }


    /**
     * 删除节点
     */
    public static void deletePath(String path) throws Exception {
        CuratorFramework client = getCuratorFramework();
        Stat stat = new Stat();
        client.delete()
                // 某些场景下节点是一定要删除的，那么可以使用guaranteed方法，该方法保证当客户端遇到网络异常等时候，会记录下这次失败的删除操作，只要会话有效，那么其就会在后台反复重试，直到节点删除成功。
              .guaranteed()
                //删除一个节点，并递归删除其所有子节点
              .deletingChildrenIfNeeded()
                // 强制指定版本进行删除
              .withVersion(stat.getVersion())
                //删除的节点路径
              .forPath(path);
    }

    /**
     * 获取客户端，应该做出单例模式
     */
    private static CuratorFramework getCuratorFramework() {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        //一种创建形式
        //CuratorFramework client = CuratorFrameworkFactory.newClient(ZK_SERVER, 5000, 3000, retryPolicy);
        // 也可以通过fluent形式
        CuratorFramework client = CuratorFrameworkFactory.builder()
                                                        //连接字符串，即zk服务器ip:port
                                                         .connectString(ZK_SERVER)
                                                        // 会话超时时间
                                                         .sessionTimeoutMs(5000)
                                                        // 重试策略
                                                         .retryPolicy(retryPolicy)
                                                        // 为了实现不同的zk业务之间的隔离，往往会为每个业务分配一个独立的命名空间，即：指定一个zk根路径，这样该客户端只能对zk上在此根路径以及其子路径进行操作
                                                        // 使用了该方法， 在操作节点时，无效带上/curator前缀，默认都在/curator下面操作，也只能在该路径下面操作
                                                         .namespace("curator")
                                                         .build();
        //启动客户端后操作才会生效
        client.start();
        return client;
    }

}
