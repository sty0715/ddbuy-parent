package com.ddbuy.ddbuymanageweb.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ddbuy.entity.TbContentCategory;
import com.ddbuy.utils.DataGridResult;
import com.ddbuy.utils.PageUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.TbContentCategoryService;

import java.util.List;

@Controller
public class TbContentCategoryController {
    @Reference(interfaceClass = TbContentCategoryService.class)
    private TbContentCategoryService tbContentCategoryService;
    @RequestMapping("/getContentCategory")
    @ResponseBody
    public DataGridResult<TbContentCategory> getContentCategory(PageUtils pageUtils){
        return tbContentCategoryService.getAllContentCategory(pageUtils);
    }


    @RequestMapping("/getCategory")
    @ResponseBody
    public List<TbContentCategory> getCategory(){
        return tbContentCategoryService.getAllContentCategory();
    }
}
