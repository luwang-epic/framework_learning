package com.framework.learning.mysql.shardingjdbc.dao;

import com.framework.learning.mysql.shardingjdbc.bean.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;


/**
 * 测试UserDao
 * @author wanglu
 * @date 2020/01/01
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDaoTest {

    @Resource
    private UserDao userDao;

    @Test
    public void testInsert() {
        userDao.insert(new User(7, "wang", 12));
        userDao.insert(new User(8, "wang", 13));
    }

    @Test
    public void testFindByAges() {
        //sharding jdbc会根据分片键来选择发送sql到哪些节点， 下面这个只会发送一个语句，因为在一个表中
        //但是如果where条件不是分片键，那么只能广播到各个表进行查询，然后再汇总结果
        List<User> users = userDao.findByAges(Arrays.asList(10, 12));
        System.out.println(users);

        users = userDao.findByAges(Arrays.asList(10, 11));
        System.out.println(users);
    }

}
