package com.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mall.common.utils.PageUtils;
import com.mall.product.entity.CategoryEntity;

import java.util.List;
import java.util.Map;

/**
 * 商品三级分类
 *
 * @author ChenJunNan
 * @email 18460365733@163.com
 * @date 2020-10-30 14:12:27
 */
public interface CategoryService extends IService<CategoryEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<CategoryEntity> getList();

    void removeMenuByIds(List<Long> asList);

    Long[] findCatelogPath(Long catelogId);
}

