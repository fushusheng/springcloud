package com.qx.servicefeign.service;

import com.qx.servicefeign.service.impl.FeignSericeImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "SERVICE-HI",fallback = FeignSericeImpl.class)
public interface FeignService {
    @RequestMapping("getInfo")
    String getInfo();
}
