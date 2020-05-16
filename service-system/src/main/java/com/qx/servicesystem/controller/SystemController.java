package com.qx.servicesystem.controller;

import com.alibaba.fastjson.JSON;
import com.qx.common.entity.User;
import com.qx.servicesystem.config.RedisUtil;
import com.qx.servicesystem.service.SystemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

@Api(value = "系统")
@RestController
@Slf4j
public class SystemController {
    @Value("${server.port}")
    private String port;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private SystemService systemService;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @ApiOperation(value = "登陆",httpMethod = "POST")
    @RequestMapping("login")
    public String login(HttpServletRequest request){
        String userName="zhangsan";
        String password="zhangsan123";
        //查询数据库，登录
        User user = systemService.login(userName, password);
        request.getSession().setAttribute("user",user);
        return "sucess";
    }
    @ApiOperation(value = "从session中获取用户对象",httpMethod = "GET")
    @RequestMapping("getUser")
    public String getSessionUser(HttpServletRequest request){
        System.out.println("请求了system服务，port:"+port);
        Object userObject = request.getSession().getAttribute("user");
        if(userObject==null){
            return "获取失败";
        }
        return JSON.toJSONString(userObject);
    }
    @ApiOperation(value = "获取分布式锁",httpMethod = "POST")
    @RequestMapping("getLock")
    public synchronized String getLock(){
        synchronized (port){
            log.info("成功请求:{},当前事件：{}",Thread.currentThread().getName(),sdf.format(new Date()));
            return String.valueOf(redisUtil.tryLock("aa")+sdf.format(new Date()));
        }

    }
}
