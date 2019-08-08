package com.ddbuy.ddbuymanageweb.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ddbuy.ddbuymanageweb.utils.FastDfsUtil;
import com.ddbuy.entity.TbContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import service.TbContentService;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/")
public class TbContentController {
    @Reference(interfaceClass = TbContentService.class)
    private TbContentService tbContentService;

    @Autowired
    private FastDfsUtil fastDfsUtil;
    @RequestMapping("addTbContent")
    @ResponseBody
    private Map<String,String> addTbContent(@RequestParam(value = "pfile") MultipartFile file, TbContent tbContent){
        Map<String,String> map=new HashMap<>();
        try {
            String filename = file.getOriginalFilename();
            String expname = filename.substring(filename.lastIndexOf(".") + 1);
            String uploadfile = fastDfsUtil.uploadFile(file.getBytes(), expname);
            if (uploadfile!=null){
                 tbContent.setPic(uploadfile);
                 tbContentService.addContent(tbContent);
                 map.put("result","1");
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("result","0");
        }
        return map;


    }
}
