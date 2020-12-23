package vc.thinker.cabbage.sys.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import vc.thinker.cabbage.sys.bo.CouponBO;
import vc.thinker.cabbage.sys.model.Coupon;
import vc.thinker.cabbage.sys.model.CouponExample;

public interface CouponMapper {
    int countByExample(CouponExample example);

    int deleteByExample(CouponExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Coupon record);

    int insertSelective(Coupon record);

    List<CouponBO> selectByExample(CouponExample example);

    CouponBO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Coupon record, @Param("example") CouponExample example);

    int updateByExample(@Param("record") Coupon record, @Param("example") CouponExample example);

    int updateByPrimaryKeySelective(Coupon record);

    int updateByPrimaryKey(Coupon record);
}