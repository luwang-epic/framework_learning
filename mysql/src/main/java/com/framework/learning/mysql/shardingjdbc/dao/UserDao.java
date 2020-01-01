package com.framework.learning.mysql.shardingjdbc.dao;

import com.framework.learning.mysql.shardingjdbc.bean.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author wanglu
 * @date 2020/01/01
 */
@Component
@Mapper
public interface UserDao {

    @Insert("insert into user(id, name, age) values(#{user.id}, #{user.name}, #{user.age})")
    void insert(@Param("user")User user);

    @Select("<script>select * from user where age in " +
            "<foreach collection='ages' open='(' close=')' item='age' separator=','>" +
            "#{age}" +
            "</foreach>" +
            "</script>")
    List<User> findByAges(@Param("ages") List<Integer> ages);
}
