package com.ddbuy.ddbuycommonservice.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.ddbuy.entity.TbContentCategory;
import com.ddbuy.entity.TbContentCategoryExample;
import com.ddbuy.mapper.TbContentCategoryMapper;
import com.ddbuy.utils.DataGridResult;
import com.ddbuy.utils.PageUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import service.TbContentCategoryService;
import java.util.List;

@Service(interfaceClass = TbContentCategoryService.class)
@Component
public class TbContentCategoryServiceImpl implements TbContentCategoryService {
    @Autowired(required = false)
    private TbContentCategoryMapper tbContentCategoryMapper;
    @Override
    public DataGridResult<TbContentCategory> getAllContentCategory(PageUtils pageUtils) {
        PageHelper.startPage(pageUtils.getPage(),pageUtils.getRows());
        List<TbContentCategory> list = tbContentCategoryMapper.selectByExample(new TbContentCategoryExample());
        PageInfo<TbContentCategory> pageInfo=new PageInfo<>(list);
        return new DataGridResult<TbContentCategory>(pageInfo.getTotal(),pageInfo.getList());
    }

    @Override
    public List<TbContentCategory> getAllContentCategory() {
        return tbContentCategoryMapper.selectByExample(null);
    }
}
