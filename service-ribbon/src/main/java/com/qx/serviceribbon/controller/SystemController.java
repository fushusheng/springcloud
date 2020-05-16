package com.qx.serviceribbon.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class SystemController {
    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("getInfo")
    @HystrixCommand(fallbackMethod = "getInfoError")
    public String getInfo(){
        String url="http://service-hi/getInfo";
//        url="http://localhost:8762/getInfo";
        return restTemplate.getForObject(url,String.class);
    }
    public String getInfoError(){
        return "http://service-hi/getInfo error 不可用";
    }
}
