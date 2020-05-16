package com.qx.servicefeign.service.impl;

import com.qx.servicefeign.service.FeignService;
import org.springframework.stereotype.Component;

@Component
public class FeignSericeImpl implements FeignService {
    @Override
    public String getInfo() {
        return "sorry,服务暂不可用";
    }
}
