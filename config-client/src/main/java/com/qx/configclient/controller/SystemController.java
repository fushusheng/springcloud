package com.qx.configclient.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class SystemController {
    //http://localhost:8881/actuator/bus-refresh
    @Value("${foo}")
    private String foo;

    @RequestMapping("hi")
    public String hi(){
        return foo;
    }
}
