package com.qx.eureka_client_hi.controller;

import com.alibaba.fastjson.JSON;
import com.qx.common.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class SystemController {
    @Value("${spring.application.name}")
    private String applicationName;
    @Value("${server.port}")
    private String port;
    @RequestMapping("/getInfo")
    public String getInfo(){
        return "hi,eureka客户端"+applicationName+":"+port;
    }
    @RequestMapping("getUser")
    public String getSessionUser(HttpServletRequest request){
        System.out.println("请求了service-hi服务，port:"+port);
        Object userObject = request.getSession().getAttribute("user");
        if(userObject==null){
            return "获取失败";
        }
        return JSON.toJSONString(userObject);
    }
}
