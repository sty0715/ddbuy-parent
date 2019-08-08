package service;

import com.ddbuy.entity.TbContentCategory;
import com.ddbuy.utils.DataGridResult;
import com.ddbuy.utils.PageUtils;

import java.util.List;

public interface TbContentCategoryService {
    DataGridResult<TbContentCategory> getAllContentCategory(PageUtils pageUtils);

    List<TbContentCategory> getAllContentCategory();
}
