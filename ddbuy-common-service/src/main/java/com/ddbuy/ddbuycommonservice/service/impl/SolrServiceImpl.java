package com.ddbuy.ddbuycommonservice.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.ddbuy.entity.TbItem;
import com.ddbuy.mapper.TbItemMapper;
import com.ddbuy.utils.SolrPruduct;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import service.SolrSerivice;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Service(interfaceClass = SolrSerivice.class)
public class SolrServiceImpl implements SolrSerivice {
    @Autowired
    private SolrClient solrClient;
    @Autowired(required = false)
    private TbItemMapper tbItemMapper;
    @Value("${solr.pagesize}")
    private Integer pageSize;

    @Override
    public boolean importIndex() {
        try {
            List<SolrPruduct> list = tbItemMapper.getAllSolrProduct();
            solrClient.addBeans(list);
            solrClient.commit();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;

    }

    @Override
    public Map<String, Object> searchProduct(String condition, Integer page) {
        Map<String, Object> map=null;
        try {
            //通过条件查询
            SolrQuery query=new SolrQuery();  //封装查询条件
            //1.设置搜索条件
            if(condition==null||condition.equals(""))
                query.set("q","title:*");
            else
                query.set("q","title:"+condition);

            //2.分页
            query.setStart((page-1)*pageSize);  //起始位置
            query.setRows(pageSize);

            //3.排序
            query.setSort("price",SolrQuery.ORDER.asc);

            QueryResponse response=solrClient.query(query);  //执行查询
            //获取当前页的数据
            List<SolrPruduct> list = response.getBeans(SolrPruduct.class);
            //获取总页数
            long totalRecords=response.getResults().getNumFound();  //获取总行数
            //求总页数
            int totalPage=(int)(Math.ceil(totalRecords*1.0/pageSize));

            //创建返回的map对象
            map=new HashMap<>();
            map.put("rows",list);
            map.put("totalPage",totalPage);
            map.put("totalRecords",totalRecords);
            return  map;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
