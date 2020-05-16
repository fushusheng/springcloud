package com.qx.servicefeign.controller;

import com.qx.servicefeign.service.FeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SystemController {
    @Autowired
    private FeignService feignService;
    @RequestMapping("getInfo")
    public String getInfo(){
        return feignService.getInfo();
    }
}
