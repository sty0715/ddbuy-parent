package com.ddbuy.mapper;

import com.ddbuy.entity.TbItem;
import com.ddbuy.entity.TbItemExample;
import com.ddbuy.utils.SolrPruduct;

import java.util.List;

public interface TbItemMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TbItem record);

    int insertSelective(TbItem record);

    List<TbItem> selectByExample(TbItemExample example);

    TbItem selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TbItem record);

    int updateByPrimaryKey(TbItem record);
   //查询所有需要被导入到索引库的商品信息
    List<SolrPruduct> getAllSolrProduct();
}