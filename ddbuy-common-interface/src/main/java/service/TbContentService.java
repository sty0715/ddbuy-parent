package service;

import com.ddbuy.entity.TbContent;

import java.util.List;

public interface TbContentService {
    int addContent(TbContent tbContent);

    //查询所有广告
    public List<TbContent> getAllContent();
}
