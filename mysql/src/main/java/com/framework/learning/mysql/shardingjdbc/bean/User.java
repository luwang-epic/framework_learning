package com.framework.learning.mysql.shardingjdbc.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 对应user表
 * @author wanglu
 * @date 2020/01/01
 */
@Data
@AllArgsConstructor
public class User {
    /**
     * 主键Id
     */
    private int id;

    /**
     * 名称
     */
    private String name;

    /**
     * 年龄
     */
    private int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
