package com.ddbuy.ddbuyprotalweb.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.TbItemService;

@Controller
public class TbItemController {
    @Reference(interfaceClass = TbItemService.class,timeout = 5000)
    private TbItemService tbItemService;
    @RequestMapping("/gohtmls")
    @ResponseBody
    public boolean goHtmls(){
        return tbItemService.processHtml();
    }
}
