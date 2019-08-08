package com.ddbuy.ddbuycommonservice.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.ddbuy.entity.TbContent;
import com.ddbuy.entity.TbContentExample;
import com.ddbuy.mapper.TbContentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import service.TbContentService;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service(interfaceClass = TbContentService.class)
@Component
public class TbContentServiceImpl implements TbContentService {
    @Autowired(required = false)
    private TbContentMapper tbContentMapper;
    @Autowired
    private RedisTemplate redisTemplate;
    @Override
    public int addContent(TbContent tbContent) {
       int temp=-1;
        try {
            temp=tbContentMapper.insertSelective(tbContent);
            this.setBuffer();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return temp;
    }
    public List<TbContent> setBuffer(){
        ValueOperations<String,Object> valueOperations = redisTemplate.opsForValue();
        TbContentExample tbContentExample=new TbContentExample();
        TbContentExample.Criteria criteria = tbContentExample.createCriteria();
        criteria.andStatusEqualTo("1");
        List<TbContent> list = tbContentMapper.selectByExample(tbContentExample);
        valueOperations.set("contentkey",list,1,TimeUnit.DAYS);
        return list;

    }

    @Override
    public List<TbContent> getAllContent() {
        List<TbContent> list=null;
        //实现广告的缓存
        //思路:判断缓存中是否存在，存在就从缓存中取，不存在则查询数据库并保存
        ValueOperations<String,Object> option = redisTemplate.opsForValue();
        if (this.redisTemplate.hasKey("contentkey")){
            list=(List<TbContent>)option.get("contentkey");
        }else {
            list = this.setBuffer();
        }
        return list;
    }
}
