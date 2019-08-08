package com.ddbuy.ddbuycommonservice.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.ddbuy.entity.TbItem;
import com.ddbuy.entity.TbItemExample;
import com.ddbuy.mapper.TbItemMapper;
import freemarker.core.Configurable;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import service.TbItemService;

import java.io.FileWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(interfaceClass = TbItemService.class)
@Component
public class TbItemServiceImpl implements TbItemService {
    @Autowired
    private TbItemMapper tbItemMapper;
    @Autowired
    private Configuration configuration;
    @Override
    public boolean processHtml() {
        try {
            //1.查询所有的商品信息
            TbItemExample tbItemExample=new TbItemExample();
            List<TbItem> list=this.tbItemMapper.selectByExample(tbItemExample);
            //2.将每个商品其于freemarker生成静态页面
            for (TbItem p:list) {
                //加载模板
                Template template= null;
                template = configuration.getTemplate("Product.ftl");
                //创建模型数据
                Map<String,Object> maps=new HashMap<>();
                maps.put("p",p);
                //生成静态页面  注意:商品的名称即为静态网页名
                Writer w=new FileWriter("F:\\idea\\u4\\ddbuy-parent\\ddbuy-item-web\\src\\main\\webapp\\"+p.getId()+".html");
                template.process(maps,w);
                w.close();
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;

    }

    @Override
    public boolean processHtml(TbItem tbItem) {
        try {
            //加载模板
            Template template= null;
            template = configuration.getTemplate("Product.ftl");
            //创建模型数据
            Map<String,Object> maps=new HashMap<>();
            maps.put("p",tbItem);
            //生成静态页面  注意:商品的名称即为静态网页名
            Writer w=new FileWriter("F:\\idea\\u4\\ddbuy-parent\\ddbuy-item-web\\src\\main\\webapp\\"+tbItem.getId()+".html");
            template.process(maps,w);
            w.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
