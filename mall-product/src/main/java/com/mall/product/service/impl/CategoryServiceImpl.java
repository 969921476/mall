package com.mall.product.service.impl;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mall.common.utils.PageUtils;
import com.mall.common.utils.Query;

import com.mall.product.dao.CategoryDao;
import com.mall.product.entity.CategoryEntity;
import com.mall.product.service.CategoryService;


@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryEntity> implements CategoryService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CategoryEntity> page = this.page(
                new Query<CategoryEntity>().getPage(params),
                new QueryWrapper<CategoryEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<CategoryEntity> getList() {
        // 获取所有分类
        List<CategoryEntity> categories = baseMapper.selectList(null);

        // 组成树形结构
        // 将获取的集合转换成stream流过滤ParentCid等于0的进行返回，并映射该项的children属性 并进行排序
        categories.stream().filter(item -> item.getParentCid() == 0).map(menu -> {
            menu.setChildren(getChildren(menu, categories));
            return menu;
        }).sorted((menu1, menu2) -> {
            return menu1.getSort() - menu2.getSort();
        }).collect(Collectors.toList());
        return null;
    }

    private List<CategoryEntity> getChildren(CategoryEntity root, List<CategoryEntity> list) {
        List<CategoryEntity> collect = list.stream().filter(item -> item.getParentCid() == root.getCatId()).map(menu -> {
            menu.setChildren(getChildren(menu, list));
            return menu;
        }).sorted((menu1, menu2) -> {
            return menu1.getSort() - menu2.getSort();
        }).collect(Collectors.toList());
        return collect;
    }

}