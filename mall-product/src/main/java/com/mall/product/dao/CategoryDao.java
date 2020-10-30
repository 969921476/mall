package com.mall.product.dao;

import com.mall.product.entity.CategoryEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品三级分类
 * 
 * @author ChenJunNan
 * @email 18460365733@163.com
 * @date 2020-10-30 14:12:27
 */
@Mapper
public interface CategoryDao extends BaseMapper<CategoryEntity> {
	
}
