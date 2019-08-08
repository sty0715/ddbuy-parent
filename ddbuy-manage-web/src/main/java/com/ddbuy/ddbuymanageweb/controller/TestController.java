package com.ddbuy.ddbuymanageweb.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.TestService;

@Controller
public class TestController {
    @Reference(interfaceClass = TestService.class)
    private TestService testService;
    @RequestMapping("/getHw")
    @ResponseBody
    public String getHw(){
        return testService.getHello();
    }
}
