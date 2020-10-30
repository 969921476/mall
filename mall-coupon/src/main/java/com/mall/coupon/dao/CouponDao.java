package com.mall.coupon.dao;

import com.mall.coupon.entity.CouponEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 优惠券信息
 * 
 * @author ChenJunNan
 * @email 18460365733@163.com
 * @date 2020-10-30 14:21:37
 */
@Mapper
public interface CouponDao extends BaseMapper<CouponEntity> {
	
}
