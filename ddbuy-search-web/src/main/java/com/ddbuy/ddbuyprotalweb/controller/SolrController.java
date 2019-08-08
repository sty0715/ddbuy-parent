package com.ddbuy.ddbuyprotalweb.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ddbuy.ddbuyprotalweb.utils.PageUitl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.SolrSerivice;

import java.util.Map;

@Controller
public class SolrController {
    @Reference(interfaceClass = SolrSerivice.class,timeout = 5000)
    private SolrSerivice solrSerivice;
    @RequestMapping("/import")
    @ResponseBody
    public boolean importIndex(){
        boolean b = solrSerivice.importIndex();
        return b;
    }

    @RequestMapping("/search")
    public String searchProduct(String title, Integer pageNum, Model model){
        System.out.println("查询条件是:"+title+"=页码是:"+pageNum);
        pageNum=pageNum==null?1:pageNum;
        System.out.println("pageNum:"+pageNum);
        Map<String, Object> map = this.solrSerivice.searchProduct(title, pageNum);
        model.addAttribute("map",map);
        /*String navigateStr=PageUitl.build(pageNum,(int)map.get("totalPage"),15);
        model.addAttribute("navigateStr",navigateStr);*/
        model.addAttribute("title",title);
        model.addAttribute("pageNum",pageNum);
        return "SearchList";
    }
}
