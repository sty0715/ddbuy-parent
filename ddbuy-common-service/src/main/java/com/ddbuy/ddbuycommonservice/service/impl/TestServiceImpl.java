package com.ddbuy.ddbuycommonservice.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;
import service.TestService;
@Service(interfaceClass = TestService.class)
@Component
public class TestServiceImpl implements TestService {
    @Override
    public String getHello() {
        return "hello";
    }
}
