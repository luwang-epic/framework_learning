package com.framework.learning.security.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wanglu
 * @date 2020/01/12
 */
@RestController
public class ErrorController {

    @RequestMapping("/error/403")
    public Map<String, Object> error403() {
        Map<String, Object> data = new HashMap<>();
        data.put("code", 403);
        data.put("error_msg", "权限不足");
        return data;
    }

    @RequestMapping("/error/401")
    public Map<String, Object> error401() {
        Map<String, Object> data = new HashMap<>();
        data.put("code", 401);
        data.put("error_msg", "没有授权");
        return data;
    }

}
