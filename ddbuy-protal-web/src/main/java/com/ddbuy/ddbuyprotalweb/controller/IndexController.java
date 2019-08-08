package com.ddbuy.ddbuyprotalweb.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.ddbuy.entity.TbContent;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import service.TbContentService;

import java.util.List;

@Controller
public class IndexController {
    @Reference(interfaceClass = TbContentService.class)
    private TbContentService tbContentService;
    @RequestMapping("/index")
    public String Goindex(Model model){
        List<TbContent> list = tbContentService.getAllContent();
        model.addAttribute("list",list);
        return "Index";
    }
}
