package com.mall.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mall.common.utils.PageUtils;
import com.mall.ware.entity.UndoLogEntity;

import java.util.Map;

/**
 * 
 *
 * @author ChenJunNan
 * @email 18460365733@163.com
 * @date 2020-10-30 14:10:04
 */
public interface UndoLogService extends IService<UndoLogEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

