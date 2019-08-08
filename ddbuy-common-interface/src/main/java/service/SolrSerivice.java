package service;

import java.util.Map;

public interface SolrSerivice {
    boolean importIndex();

    /**
     *  实现搜索业务
     * @param condition  搜索条件
     * @param page 页码
     * @return 键值对集合
     *       包含当前页的数据 键rows=当前页的数据
     *       包含总页数 键totalPage=当页数
     */
    Map<String,Object> searchProduct(String condition,Integer page);
}
